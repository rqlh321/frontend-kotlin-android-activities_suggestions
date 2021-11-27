package ru.gubatenko.common_android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lightColors = lightColors()
private val darkColors = darkColors()
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
        colors = if (darkTheme) darkColors else lightColors,
        typography = typography,
        shapes = MaterialTheme.shapes,
        content = content,
    )
}

@Composable
inline fun MediumButton(
    buttonVisible: Boolean = true,
    buttonClickable: Boolean = true,
    buttonText: String? = null,
    crossinline click: () -> Unit,
) {
    if (buttonVisible) Button(
        modifier = Modifier.padding(16.dp),
        onClick = { if (buttonClickable) click.invoke() }) {
        buttonText?.let {
            Text(
                color = MaterialTheme.colors.onPrimary,
                text = it,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun MediumText(
    textVisible: Boolean = true,
    text: String?,
) {
    if (textVisible) text?.let {
        Text(
            color = MaterialTheme.colors.primary,
            text = it,
            style = MaterialTheme.typography.body1
        )
    }

}