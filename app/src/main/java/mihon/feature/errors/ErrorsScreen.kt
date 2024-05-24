package mihon.feature.errors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import eu.kanade.presentation.util.Screen
import eu.kanade.tachiyomi.ui.manga.MangaScreen

class ErrorsScreen : Screen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { ErrorsScreenModel() }
        val state by screenModel.state.collectAsState()

        ErrorsScreenContent(
            state = state,
            onClickError = { navigator.push(MangaScreen(it.id)) },
        )
    }
}
