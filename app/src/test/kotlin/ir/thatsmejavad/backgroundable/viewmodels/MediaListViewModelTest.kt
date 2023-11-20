package ir.thatsmejavad.backgroundable.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.common.CoroutineTest
import ir.thatsmejavad.backgroundable.common.collectDataForTest
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MediaListViewModelTest : CoroutineTest {

    override lateinit var testScope: TestScope
    override lateinit var dispatcher: TestDispatcher

    @RelaxedMockK
    lateinit var snackbarManager: SnackbarManager

    @RelaxedMockK
    lateinit var mediaRepository: MediaRepository

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    private val id = "4"

    private val testMediaWithResources = MediaWithResources(
        media = MediaEntity(
            id = 3,
            width = 230,
            height = 403,
            url = "www.google.com",
            alt = "wow",
            type = MediaType.Photo,
            collectionId = null,
            photographer = "jai",
            avgColor = "#000",
            photographerId = 23,
            photographerUrl = "www.google.com",
            orderId = 23,
        ),
        resources = listOf(
            ResourceEntity(
                mediaId = 23,
                size = ResourceSize.Tiny,
                url = "google.com"
            )
        )
    )

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `mediaColumns should update on viewModel init`() = runTest {
        every { mediaRepository.mediaColumnTypeFlow } returns flowOf(List.GridType)

        val viewModel = createViewModel(id)

        viewModel.mediaColumnTypeFlow.test {
            awaitItem() shouldBe List.GridType
        }
    }

    @Test
    fun `imageQuality should update on viewModel init`() = runTest {
        every { settingRepository.userPreferencesFlow } returns flowOf(
            UserPreferences(imageQuality = ImageQuality.Medium)
        )
        val viewModel = createViewModel(id)

        viewModel.imageQuality.test {
            awaitItem() shouldBe ImageQuality.Medium
        }
    }

    @Test
    fun `expect exception if id is null`() {
        shouldThrow<IllegalArgumentException> { createViewModel(null) }

        coVerify(exactly = 0) { mediaRepository.getMediasByCollectionId(any()) }
    }

    @Test
    fun `media flow should update by the getMediasByCollectionId`() = runTest {
        every { mediaRepository.getMediasByCollectionId(any()) } returns flowOf(
            PagingData.from(
                listOf(testMediaWithResources)
            )
        )
        val viewModel = createViewModel(id)

        coVerify(exactly = 1) { mediaRepository.getMediasByCollectionId(id) }

        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf(testMediaWithResources)
        }
    }

    @Test
    fun `setMediaColumnType should called when changeColumnType called`() = runTest {
        every { mediaRepository.mediaColumnTypeFlow } returns flowOf(List.GridType)
        val viewModel = createViewModel(id)

        viewModel.changeColumnCount()

        coVerify(exactly = 1) { mediaRepository.setMediaColumnType(any()) }
    }

    @Test
    fun `getNewColumnTypeByCurrentType should return ListType on GridType parameter`() {
        val viewModel = createViewModel(id)

        val result = viewModel.getNewColumnTypeByCurrentType(List.GridType)

        result shouldBe List.ListType
    }

    @Test
    fun `getNewColumnTypeByCurrentType should return StaggeredType on ListType parameter`() {
        val viewModel = createViewModel(id)

        val result = viewModel.getNewColumnTypeByCurrentType(List.ListType)

        result shouldBe List.StaggeredType
    }

    @Test
    fun `getNewColumnTypeByCurrentType should return GridType on StaggeredType parameter`() {
        val viewModel = createViewModel(id)

        val result = viewModel.getNewColumnTypeByCurrentType(List.StaggeredType)

        result shouldBe List.GridType
    }

    private fun createViewModel(id: String?): MediaListViewModel {
        return MediaListViewModel(
            snackbarManager = snackbarManager,
            mediaRepository = mediaRepository,
            settingRepository = settingRepository,
            savedStateHandle = id?.let { SavedStateHandle(mapOf("id" to id)) }
                ?: run { SavedStateHandle() }
        )
    }
}
