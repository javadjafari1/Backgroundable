package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MediaDetailViewModelTest {

    @RelaxedMockK
    lateinit var snackbarManager: SnackbarManager

    @RelaxedMockK
    lateinit var mediaRepository: MediaRepository

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    @RelaxedMockK
    lateinit var context: Context

    @RelaxedMockK
    lateinit var drawable: Drawable

    private val id = 4

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

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
    }

    @Test
    fun `expect exception if id is null`() {
        shouldThrow<IllegalArgumentException> { createViewModel(null) }

        coVerify(exactly = 0) { mediaRepository.getMediaWithResources(any()) }
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
    fun `savePurpose should update on saveFile called`() = runTest {
        val viewModel = createViewModel(id)

        viewModel.saveFile(
            purpose = SavePurpose.SettingWallpaper,
            drawable = drawable,
            context = context
        )

        viewModel.savePurpose.test {
            awaitItem() shouldBe SavePurpose.SettingWallpaper
        }
    }

    private fun createViewModel(id: Int?): MediaDetailViewModel {
        return MediaDetailViewModel(
            snackbarManager = snackbarManager,
            mediaRepository = mediaRepository,
            settingRepository = settingRepository,
            savedStateHandle = id?.let { SavedStateHandle(mapOf("id" to id)) }
                ?: run { SavedStateHandle() }
        )
    }
}
