package ir.thatsmejavad.backgroundable.viewmodels

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.thatsmejavad.backgroundable.common.CoroutineTest
import ir.thatsmejavad.backgroundable.core.Downloader
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.screens.downloadpicker.DownloadPickerViewModel
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

class DownloadPickerViewModelTest : CoroutineTest {

    override lateinit var testScope: TestScope
    override lateinit var dispatcher: TestDispatcher

    @RelaxedMockK
    lateinit var mediaRepository: MediaRepository

    @RelaxedMockK
    lateinit var downloader: Downloader

    private val id = 3

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
    fun `expect exception if id is null`() {
        shouldThrow<IllegalArgumentException> { createViewModel(null) }
    }

    @Test
    fun `should getMediaWithResourceCalled on viewModel init`() {
        createViewModel(id)

        coVerify { mediaRepository.getMediaWithResources(id) }
    }

    @Test
    fun `media flow should update by data of getMediaWithResources`() = runTest {
        coEvery { mediaRepository.getMediaWithResources(id) } returns testMediaWithResources
        val viewModel = createViewModel(id)

        viewModel.media.test {
            awaitItem() shouldBe AsyncJob.Success(testMediaWithResources)
        }
    }

    @Test
    fun `media flow should update by error of getMediaWithResources`() = runTest {
        val ioException = IOException()
        coEvery { mediaRepository.getMediaWithResources(id) } throws ioException
        val viewModel = createViewModel(id)
        viewModel.media.test {
            awaitItem() shouldBe AsyncJob.Fail(ioException)
        }
    }

    @Test
    fun `the downloader's download fun should called on viewModel's download call`() {
        coEvery { mediaRepository.getMediaWithResources(id) } returns testMediaWithResources
        val viewModel = createViewModel(id)

        viewModel.download(testMediaWithResources.resources.first())

        verify {
            downloader.download(
                url = testMediaWithResources.resources.first().url,
                alt = testMediaWithResources.media.alt,
                photographer = testMediaWithResources.media.photographer,
                size = testMediaWithResources.resources.first().size
            )
        }
    }

    private fun createViewModel(id: Int?): DownloadPickerViewModel {
        return DownloadPickerViewModel(
            mediaRepository = mediaRepository,
            downloader = downloader,
            savedStateHandle = id?.let { SavedStateHandle(mapOf("id" to id)) }
                ?: run { SavedStateHandle() }
        )
    }
}
