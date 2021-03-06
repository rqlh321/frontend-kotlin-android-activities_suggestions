package com.example.feature_accepted_activities_android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.feature_accepted_activities.PromiseStore
import ru.gubatenko.common_android.MediumText

@Preview
@Composable
fun PromiseCompose(viewModel: PromiseViewModel? = null) {
    val state: PromiseStore.State by (viewModel?.state?.liveData
        ?: MutableLiveData(previewState()))
        .observeAsState(previewState())

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(state.promiseList) { MediumText(text = it.activity) }
    }
}