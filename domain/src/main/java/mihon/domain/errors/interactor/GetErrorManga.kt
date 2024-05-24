package mihon.domain.errors.interactor

import kotlinx.coroutines.flow.Flow
import tachiyomi.domain.manga.model.Manga
import tachiyomi.domain.manga.repository.MangaRepository

class GetErrorManga(
    private val mangaRepository: MangaRepository,
) {
    suspend fun subscribe(): Flow<List<Manga>> {
        return mangaRepository.getErrorManga()
    }
}
