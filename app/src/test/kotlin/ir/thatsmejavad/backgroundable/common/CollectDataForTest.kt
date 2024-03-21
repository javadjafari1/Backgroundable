package ir.thatsmejavad.backgroundable.common

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> PagingData<T>.collectDataForTest(mainContext: CoroutineContext): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(
            position: Int,
            count: Int
        ) {
        }

        override fun onInserted(
            position: Int,
            count: Int
        ) {
        }

        override fun onRemoved(
            position: Int,
            count: Int
        ) {
        }
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, mainContext) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}
