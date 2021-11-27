package ru.gubatenko.feature_main_android

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import ru.gubatenko.common_android.MediumButton
import ru.gubatenko.common_android.MediumText
import ru.gubatenko.feature_main.IdeaStore

@Preview
@Composable
fun IdeaCompose(viewModel: IdeaViewModel? = null) {
    val state: IdeaStore.State by (viewModel?.state?.liveData
        ?: MutableLiveData(previewState()))
        .observeAsState(previewState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoadingProgressVisible) {
            CircularProgressIndicator()
        }
        MediumText(
            textVisible = state.isIdeaTextVisible,
            text = state.idea?.activity
        )
        MediumText(
            textVisible = state.isErrorTextVisible,
            text = state.errorText
        )
        MediumButton(
            buttonVisible = state.isRetryButtonVisible,
            buttonText = state.retryButtonText,
            click = { viewModel?.reload() }
        )
    }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        MediumButton(
            buttonVisible = state.isNextButtonVisible,
            buttonClickable = state.isNextButtonClickable,
            buttonText = state.nextButtonText,
            click = { viewModel?.next() }
        )
        MediumButton(
            buttonVisible = state.isSaveButtonVisible,
            buttonClickable = state.isSaveButtonClickable,
            buttonText = state.saveButtonText,
            click = { viewModel?.save() }
        )
    }
}