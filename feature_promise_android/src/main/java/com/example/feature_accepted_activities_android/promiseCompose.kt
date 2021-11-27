package com.example.feature_accepted_activities_android

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.feature_accepted_activities.PromiseStore
import ru.gubatenko.common_android.MediumText

@Preview
@Composable
fun PromiseCompose(viewModel: PromiseViewModel? = null) {
    val state: PromiseStore.State by (viewModel?.state?.liveData
        ?: MutableLiveData(previewState()))
        .observeAsState(previewState())

    Column {
        state.promiseList.map { MediumText(text = it.activity) }
    }
}