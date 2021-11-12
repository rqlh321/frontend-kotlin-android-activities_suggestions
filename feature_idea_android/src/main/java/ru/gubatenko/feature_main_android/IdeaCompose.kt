package ru.gubatenko.feature_main_android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import ru.gubatenko.feature_main.IdeaStore

private val typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.pinzelan_regular)),
    body1 = TextStyle(
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    ),
    body2 = TextStyle(
        fontSize = 18.sp,
    ),
    subtitle1 = TextStyle(
        fontSize = 12.sp,
    )
)

@Composable
fun ComposePlaygroundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors,
        typography = typography,
        shapes = MaterialTheme.shapes,
        content = content,
    )
}

@Preview
@Composable
fun IdeaScreen(viewModel: IdeaViewModel? = null) {
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

@Composable
private inline fun MediumButton(
    buttonVisible: Boolean = true,
    buttonClickable: Boolean = true,
    buttonText: String? = null,
    crossinline click: () -> Unit,
) {
    if (buttonVisible) Button(
        modifier = Modifier.padding(16.dp),
        onClick = { if (buttonClickable) click.invoke() }) {
        buttonText?.let { MediumText(text = it) }
    }
}

@Composable
private fun MediumText(
    textVisible: Boolean = true,
    text: String?,
) {
    if (textVisible) text?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.body1
        )
    }

}