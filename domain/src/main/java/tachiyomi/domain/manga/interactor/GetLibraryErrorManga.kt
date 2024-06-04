package tachiyomi.domain.manga.interactor

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retry
import tachiyomi.domain.library.model.LibraryManga
import tachiyomi.domain.manga.repository.MangaRepository
import kotlin.time.Duration.Companion.seconds

class GetLibraryErrorManga(
    private val mangaRepository: MangaRepository,
) {

    suspend fun await(): List<LibraryManga> {
        return mangaRepository.getLibraryErrorManga()
    }
}
