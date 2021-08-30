package ru.gubatenko.data_impl.service

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.service.UserService

class UserServiceImpl : UserService {

    override suspend fun post(data: List<ActivityDto>) {
        val set = data.map { "activity" to it.activity }

        Firebase.firestore.collection("user_data")
            .document("user_id:123")
            .collection("activity")
            .add(set)
            .await()
    }
}