package mihon.feature.errors

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SwapCalls
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import eu.kanade.presentation.components.AppBar
import kotlinx.collections.immutable.ImmutableList
import mihon.feature.errors.components.ErrorItem
import tachiyomi.domain.manga.model.Manga
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.FastScrollLazyColumn
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource

@Composable
fun ErrorsScreenContent(
    state: ErrorsScreenModel.State,
    onClickError: (manga: Manga) -> Unit,
    onClickMigrate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    Scaffold(
        topBar = { ErrorsToolbar(onClickMigrate) },
        modifier = modifier,
    ) { paddingValues ->
        ErrorScreenImpl(
            listState = listState,
            items = state.items,
            paddingValues = paddingValues,
            onClickError = onClickError,
        )
    }
}

@Composable
private fun ErrorsToolbar(onClickMigrate: () -> Unit) {
    val navigator = LocalNavigator.currentOrThrow
    val uriHandler = LocalUriHandler.current

    AppBar(
        title = "Errors",
        navigateUp = navigator::pop,
        actions = {
            IconButton(onClick = onClickMigrate) {
                Icon(
                    imageVector = Icons.Outlined.SwapCalls,
                    contentDescription = stringResource(MR.strings.migrate),
                )
            }
        },
    )
}

@Composable
private fun ErrorScreenImpl(
    listState: LazyListState,
    items: ImmutableList<Manga>,
    paddingValues: PaddingValues,
    onClickError: (manga: Manga) -> Unit,
) {
    FastScrollLazyColumn(
        contentPadding = paddingValues,
        state = listState,
    ) {
        items(
            items = items,
            key = { "error-${it.hashCode()}" },
        ) { item ->
            ErrorItem(
                error = item,
                onClick = { onClickError(item) },
            )
        }
    }
}
