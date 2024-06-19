package tachiyomi.presentation.core.components.material

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import tachiyomi.presentation.core.components.Badge
import tachiyomi.presentation.core.components.BadgeGroup
import tachiyomi.presentation.core.components.Pill

@Composable
fun TabText(text: String, badgeCount: Int? = null, errorCount: Int? = null) {
    val pillAlpha = if (isSystemInDarkTheme()) 0.5f else 0.4f

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        BadgeGroup {
            if (badgeCount != null) {
                Badge(
                    text = "$badgeCount",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = pillAlpha),
                )
            }
            if (errorCount != null) {
                Badge(
                    text = "$errorCount",
                    color = MaterialTheme.colorScheme.error.copy(alpha = pillAlpha),
                )
            }
        }
    }
}
