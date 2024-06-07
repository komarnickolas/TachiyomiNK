package tachiyomi.domain.library.model

import dev.icerock.moko.resources.StringResource
import tachiyomi.i18n.MR

object LibrarySplit {
    const val NONE = 0
    const val FILTER_FN_DOWNLOADED = 1
    const val FILTER_FN_ERROR = 2
    const val FILTER_FN_UNREAD = 3
    const val FILTER_FN_STARTED = 4
    const val FILTER_FN_BOOKMARKED = 5
    const val FILTER_FN_COMPLETED = 6

    fun splitTypeStringRes(type: Int): StringResource {
        return when (type) {
            NONE -> MR.strings.none
            FILTER_FN_DOWNLOADED -> MR.strings.label_downloaded
            FILTER_FN_ERROR -> MR.strings.error
            FILTER_FN_UNREAD -> MR.strings.action_filter_unread
            FILTER_FN_STARTED -> MR.strings.label_started
            FILTER_FN_BOOKMARKED -> MR.strings.action_filter_bookmarked
            FILTER_FN_COMPLETED -> MR.strings.completed
            else -> MR.strings.none
        }
    }
}
