package eu.kanade.presentation.library.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastAny
import eu.kanade.tachiyomi.ui.library.LibraryItem
import eu.kanade.tachiyomi.ui.library.SplitMap
import tachiyomi.domain.library.model.LibraryManga
import tachiyomi.domain.manga.model.MangaCover
import tachiyomi.presentation.core.components.HeadingItem
import tachiyomi.presentation.core.components.ListGroupHeader
import tachiyomi.presentation.core.components.TextItem

@Composable
internal fun LibraryCompactGrid(
    items: SplitMap,
    showTitle: Boolean,
    columns: Int,
    contentPadding: PaddingValues,
    selection: List<LibraryManga>,
    onClick: (LibraryManga) -> Unit,
    onLongClick: (LibraryManga) -> Unit,
    onClickContinueReading: ((LibraryManga) -> Unit)?,
    searchQuery: String?,
    onGlobalSearchClicked: () -> Unit,
) {
    LazyLibraryGrid(
        modifier = Modifier.fillMaxSize(),
        columns = columns,
        contentPadding = contentPadding,
    ) {
        globalSearchItem(searchQuery, onGlobalSearchClicked)
        items.forEach { (key, items) ->
            if (key.name.isNotBlank() && items.isNotEmpty()) {
                item(span = { GridItemSpan(columns) }) { ListGroupHeader(text = key.name, badgeCount = items.size) }
            }
            items(
                items = items,
                contentType = { "library_compact_grid_item" },
            ) { libraryItem ->
                val manga = libraryItem.libraryManga.manga
                MangaCompactGridItem(
                    isSelected = selection.fastAny { it.id == libraryItem.libraryManga.id },
                    title = manga.title.takeIf { showTitle },
                    coverData = MangaCover(
                        mangaId = manga.id,
                        sourceId = manga.source,
                        isMangaFavorite = manga.favorite,
                        ogUrl = manga.thumbnailUrl,
                        lastModified = manga.coverLastModified,
                    ),
                    coverBadgeStart = {
                        DownloadsBadge(count = libraryItem.downloadCount)
                        UnreadBadge(count = libraryItem.unreadCount)
                        ErrorBadge(hasError = !libraryItem.libraryManga.manga.errorString.isNullOrEmpty())
                    },
                    coverBadgeEnd = {
                        LanguageBadge(
                            isLocal = libraryItem.isLocal,
                            sourceLanguage = libraryItem.sourceLanguage,
                        )
                    },
                    onLongClick = { onLongClick(libraryItem.libraryManga) },
                    onClick = { onClick(libraryItem.libraryManga) },
                    onClickContinueReading = if (onClickContinueReading != null && libraryItem.unreadCount > 0) {
                        { onClickContinueReading(libraryItem.libraryManga) }
                    } else {
                        null
                    },
                )
            }
        }
    }
}
