package ru.gubatenko.data_impl

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.dto.UserDto
import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.exception.UnknownUserException
import timber.log.Timber

class UserServiceImpl : UserService {

    override suspend fun user() = Firebase.auth.currentUser?.let {
        UserDto(
            uid = it.uid,
            name = it.displayName ?: "",
            avatar = it.photoUrl?.toString(),
        )
    }

    override suspend fun post(data: List<ActivityDto>) {
        try {
            Firebase.firestore.runTransaction { transaction ->
                val uid = Firebase.auth.currentUser?.uid ?: throw UnknownUserException()
                data.forEach { dto ->
                    transaction.set(
                        Firebase.firestore.collection("user_data")
                            .document(uid)
                            .collection("activity")
                            .document(),
                        dto
                    )
                }
            }.await()
        } catch (e: FirebaseFirestoreException) {
            Timber.d(e)
            when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> throw UnknownUserException()
                else -> throw  e
            }
        }
    }

    override suspend fun get(): List<ActivityDto> {
        val uid = Firebase.auth.currentUser?.uid ?: throw UnknownUserException()
        return Firebase.firestore.collection("user_data")
            .document(uid)
            .collection("activity")
            .get()
            .await()
            .toObjects(ActivityDto::class.java)
    }

    override suspend fun signOut() = Firebase.auth.signOut()
}