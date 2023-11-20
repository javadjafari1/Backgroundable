package ir.thatsmejavad.backgroundable.viewmodels

import androidx.paging.PagingData
import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.thatsmejavad.backgroundable.common.collectDataForTest
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import ir.thatsmejavad.backgroundable.model.media.Media
import ir.thatsmejavad.backgroundable.model.media.Resources
import ir.thatsmejavad.backgroundable.screens.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchViewModelTest {

    @RelaxedMockK
    lateinit var snackbarManager: SnackbarManager

    @RelaxedMockK
    lateinit var mediaRepository: MediaRepository

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    private val dispatcher = UnconfinedTestDispatcher()

    private val testMedia = Media(
        id = 23,
        width = 340,
        url = "google.com",
        height = 4300,
        alt = "",
        type = MediaType.Photo,
        liked = false,
        photographer = "Jai",
        resources = Resources("", "", "", "", "", "", "", ""),
        avgColor = "#000",
        photographerId = 23,
        photographerUrl = ""
    )

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun `searchQuery should be empty at first`() = runTest {
        val viewModel = createViewModel()
        viewModel.searchQuery.test {
            awaitItem().shouldBeEmpty()
        }
    }

    @Test
    fun `medias should be empty at first`() = runTest {
        val viewModel = createViewModel()
        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf()
        }
    }

    @Test
    fun `mediaColumnTypeFlow should be update with data of mediaColumnTypeFlow`() = runTest {
        every { mediaRepository.mediaColumnTypeFlow } returns flowOf(List.StaggeredType)
        val viewModel = createViewModel()

        verify(exactly = 1) { mediaRepository.mediaColumnTypeFlow }
        viewModel.mediaColumnTypeFlow.test {
            awaitItem() shouldBe List.StaggeredType
        }
    }

    @Test
    fun `imageQuality should be update with data of userPreferencesFlow`() = runTest {
        every { settingRepository.userPreferencesFlow } returns flowOf(
            UserPreferences(
                imageQuality = ImageQuality.Ultra
            )
        )
        val viewModel = createViewModel()

        verify(exactly = 1) { settingRepository.userPreferencesFlow }
        viewModel.imageQuality.test {
            awaitItem() shouldBe ImageQuality.Ultra
        }
    }

    @Test
    fun `medias should be null after typing text with chars more than 3`() = runTest {
        val viewModel = createViewModel()
        viewModel.updateSearchText("test text")

        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf()
        }
    }

    @Test
    fun `medias should be null after typing text with chars less than 3`() = runTest {
        val viewModel = createViewModel()
        viewModel.updateSearchText("te")

        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf()
        }
    }

    @Test
    fun `searchPhoto should not be called on text with chars less than 3`() = runTest {
        val viewModel = createViewModel()
        viewModel.updateSearchText("te")

        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf()

            verify(exactly = 0) { mediaRepository.searchPhoto(any()) }
        }
    }

    @Test
    fun `searchPhoto should be called on text with chars more than 3 after 1 second debounce`() =
        runTest {
            val viewModel = createViewModel()
            viewModel.updateSearchText("text test")

            viewModel.medias.test {
                awaitItem().collectDataForTest(dispatcher) shouldBe listOf()
            }

            advanceTimeBy(1002)
            verify(exactly = 1) { mediaRepository.searchPhoto(any()) }
        }

    @Test
    fun `medias should be update with searchPhoto`() = runTest {
        coEvery { mediaRepository.searchPhoto(any()) } returns flowOf(
            PagingData.from(
                listOf(
                    testMedia
                )
            )
        )
        val viewModel = createViewModel()

        viewModel.updateSearchText("test")

        advanceTimeBy(1002)
        verify(exactly = 1) { mediaRepository.searchPhoto(any()) }
        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf(testMedia)
        }
    }

    @Test
    fun `medias should be empty on deleting all the searchQuery`() = runTest {
        coEvery { mediaRepository.searchPhoto(any()) } returns flowOf(
            PagingData.from(
                listOf(
                    testMedia
                )
            )
        )
        val viewModel = createViewModel()

        viewModel.updateSearchText("test")

        advanceTimeBy(1002)

        verify(exactly = 1) { mediaRepository.searchPhoto(any()) }
        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf(testMedia)
        }

        viewModel.updateSearchText("")
        viewModel.medias.test {
            awaitItem().collectDataForTest(dispatcher) shouldBe listOf()
        }
    }

    private fun createViewModel(): SearchViewModel {
        return SearchViewModel(
            snackbarManager = snackbarManager,
            mediaRepository = mediaRepository,
            settingRepository = settingRepository
        )
    }
}
