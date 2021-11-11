package ru.gubatenko.feature_main_android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import ru.gubatenko.feature_main.IdeaStore

val body1 = TextStyle(
    color = Color(0xFF737373),
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(R.font.pinzelan_regular))
)

val typography = Typography(body1 = body1)

@Composable
fun ComposePlaygroundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        MaterialTheme.colors
    } else {
        MaterialTheme.colors
    }
    MaterialTheme(
        colors = colors,
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
        if (state.isIdeaTextVisible) {
            state.idea?.activity?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1
                )
            }
        }
        if (state.isErrorTextVisible) {
            state.errorText?.let { Text(text = it) }
        }
        if (state.isRetryButtonVisible) {
            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { viewModel?.reload() }
            ) {
                state.retryButtonText?.let { Text(text = it) }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        if (state.isNextButtonVisible) {
            Button(
                onClick = { if (state.isNextButtonClickable) viewModel?.next() }) {
                state.nextButtonText?.let { Text(text = it) }
            }
        }
        if (state.isSaveButtonVisible) {
            Button(
                onClick = { if (state.isSaveButtonClickable) viewModel?.save() }) {
                state.saveButtonText?.let { Text(text = it) }
            }
        }
    }
}