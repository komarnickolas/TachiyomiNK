package tachiyomi.presentation.core.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import tachiyomi.presentation.core.components.material.padding

@Composable
fun ListGroupHeader(
    text: String,
    modifier: Modifier = Modifier,
    badgeCount: Int? = null,
    errorCount: Int? = null,
) {
    val pillAlpha = if (isSystemInDarkTheme()) 0.5f else 0.4f
    Row {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium,
        )
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
