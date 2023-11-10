package ir.thatsmejavad.backgroundable.screens.collectionlist


import androidx.paging.PagingData
import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.collectDataForTest
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.datastore.ColumnCountsPreferences
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class CollectionListViewModelTest {

    @RelaxedMockK
    lateinit var collectionRepository: CollectionRepository

    @RelaxedMockK
    lateinit var snackbarManager: SnackbarManager

    @RelaxedMockK
    lateinit var columnCountsPreferences: ColumnCountsPreferences

    private val dispatcher = UnconfinedTestDispatcher()

    private val testEntity = CollectionEntity(
        id = "wow",
        title = "jai",
        description = null,
        mediaCount = 23,
        photosCount = 32,
        videosCount = 434,
        isPrivate = false
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `columnCountPickerData should be not null after creating viewModel`() {
        val viewModel = createViewModel()

        viewModel.columnCountPickerData shouldNotBe ""
        viewModel.columnCountPickerData shouldBe Json.encodeToString(listOf(1, 2, 3))
    }

    @Test
    fun `collection should not be empty at first`() = runTest {
        coEvery { collectionRepository.getCollections() } returns flowOf(
            PagingData.from(listOf(testEntity))
        )
        val viewModel = createViewModel()

        coVerify(exactly = 1) { collectionRepository.getCollections() }
        viewModel.collection.test {
            awaitItem().collectDataForTest(testScheduler) shouldBe listOf(testEntity)
        }
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
