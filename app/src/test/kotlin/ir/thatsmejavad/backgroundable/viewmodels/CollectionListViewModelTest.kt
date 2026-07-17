package ir.thatsmejavad.backgroundable.viewmodels

import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.common.CoroutineTest
import ir.thatsmejavad.backgroundable.common.toPagingData
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.datastore.ColumnCountsPreferences
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CollectionListViewModelTest : CoroutineTest {
    override lateinit var testScope: TestScope
    override lateinit var dispatcher: TestDispatcher

    @RelaxedMockK
    lateinit var collectionRepository: CollectionRepository

    @RelaxedMockK
    lateinit var snackbarManager: SnackbarManager

    @RelaxedMockK
    lateinit var columnCountsPreferences: ColumnCountsPreferences
    private val testEntity = CollectionEntity(
        id = "wow",
        title = "jai",
        description = null,
        mediaCount = 23,
        photosCount = 32,
        videosCount = 434,
        isPrivate = false
    )

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `collection should not be empty at first`() = runTest {
        coEvery { collectionRepository.getCollections() } returns listOf(testEntity).toPagingData()
        val viewModel = createViewModel()

        coVerify(exactly = 1) { collectionRepository.getCollections() }
        viewModel.collection.asSnapshot() shouldBe listOf(testEntity)
    }

    @Test
    fun `setCollectionColumnCount should be called on setColumnCount`() {
        val viewModel = createViewModel()

        viewModel.setColumnCount(23)

        coVerify(exactly = 1) { columnCountsPreferences.setCollectionColumnCount(23) }
    }

    @Test
    fun `the default column count should be 1`() = runTest {
        val viewModel = createViewModel()
        viewModel.columnCount.test {
            awaitItem() shouldBe 1
        }
    }

    @Test
    fun `columnCount should be updated with changes of collectionColumnCountFlow`() = runTest {
        coEvery { columnCountsPreferences.collectionColumnCountFlow } returns flowOf(2)
        val viewModel = createViewModel()
        viewModel.columnCount.test {
            awaitItem() shouldBe 2
        }
    }

    private fun createViewModel(): CollectionListViewModel {
        return CollectionListViewModel(
            collectionRepository = collectionRepository,
            snackbarManager = snackbarManager,
            columnCountsPreferences = columnCountsPreferences
        )
    }
}
