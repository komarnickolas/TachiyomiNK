package mihon.feature.errors

import tachiyomi.domain.manga.model.Manga

sealed interface ErrorsUIModel {
    data class Item(val manga: Manga) : ErrorsUIModel
}
