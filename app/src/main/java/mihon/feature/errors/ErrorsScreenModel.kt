package mihon.feature.errors

import androidx.compose.ui.util.fastMap
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import eu.kanade.core.util.insertSeparators
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mihon.domain.errors.interactor.GetErrorManga
import tachiyomi.domain.manga.model.Manga
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ErrorsScreenModel(
    private val getErrorManga: GetErrorManga = Injekt.get(),
) : StateScreenModel<ErrorsScreenModel.State>(State()) {
    init {
        screenModelScope.launch {
            getErrorManga.subscribe().collectLatest {
                mutableState.update { state ->
                    val upcomingItems = it.toErrorsUIModels()
                    state.copy(
                        items = upcomingItems,
                    )
                }
            }
        }
    }

    private fun List<Manga>.toErrorsUIModels(): ImmutableList<ErrorsUIModel> {
        return fastMap { ErrorsUIModel.Item(it) }.toImmutableList()
    }

    data class State(
        val items: ImmutableList<ErrorsUIModel> = persistentListOf(),
    )
}
