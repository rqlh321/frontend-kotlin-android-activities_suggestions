package ru.gubatenko.data_impl.service

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.exception.UnknownUserException

class UserServiceImpl : UserService {

    override suspend fun post(data: List<ActivityDto>) {
        try {
            Firebase.firestore.runTransaction { transaction ->
                data.forEach { dto ->
                    transaction.set(
                        Firebase.firestore.collection("user_data")
                            .document("user_id:123")
                            .collection("activity")
                            .document(),
                        dto
                    )
                }
            }.await()
        } catch (e: FirebaseFirestoreException) {
            when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> throw UnknownUserException()
                else                                              -> throw  e
            }
        }
    }
}