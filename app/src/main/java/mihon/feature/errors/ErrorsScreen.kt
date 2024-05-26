package mihon.feature.errors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import eu.kanade.presentation.util.Screen
import eu.kanade.tachiyomi.ui.browse.migration.advanced.design.PreMigrationScreen
import eu.kanade.tachiyomi.ui.manga.MangaScreen
import mihon.feature.errors.components.ErrorItem
import tachiyomi.domain.UnsortedPreferences
import tachiyomi.domain.manga.model.Manga
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ErrorsScreen : Screen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { ErrorsScreenModel() }
        val state by screenModel.state.collectAsState()

        ErrorsScreenContent(
            state = state,
            onClickError = { navigator.push(MangaScreen(it.id)) },
            onClickMigrate = { migrateManga(navigator, state.items.map { it.id }) },
        )
    }
}

// SY -->
/**
 * Initiates source migration for the specific manga.
 */
private fun migrateManga(navigator: Navigator, selectedMangaIds: List<Long>) {
    // SY -->
    PreMigrationScreen.navigateToMigration(
        Injekt.get<UnsortedPreferences>().skipPreMigration().get(),
        navigator,
        selectedMangaIds,
    )
    // SY <--
}
