package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.R

val JosefinSans = FontFamily(
    Font(R.font.josefinsansbold, FontWeight.Bold),
    Font(R.font.josefinsanssemibold, FontWeight.SemiBold),
    Font(R.font.josefinsansmedium, FontWeight.Medium),
    Font(R.font.josefinsansregular, FontWeight.Normal),
    Font(R.font.josefinsanslight, FontWeight.Light),
    Font(R.font.josefinsansextralight, FontWeight.ExtraLight)
)

val MyTypography = Typography(
    defaultFontFamily = JosefinSans
)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = DarkBlue700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

)

@Composable
fun SeniorProject2Theme(
    content: @Composable() () -> Unit
) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = MyTypography,
        shapes = Shapes,
        content = content
    )
}