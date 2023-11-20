package ir.thatsmejavad.backgroundable.viewmodels

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ImageQualitySettingViewModelTest {

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
    }

    @Test
    fun `imageQuality should update on viewModel init`() = runTest {
        every { settingRepository.userPreferencesFlow } returns flowOf(
            UserPreferences(imageQuality = ImageQuality.Medium)
        )
        val viewModel = createViewModel()

        viewModel.imageQuality.test {
            awaitItem() shouldBe ImageQuality.Medium
        }
    }

    @Test
    fun `repository's setImageQuality should call on viewModel's setImageQuality called`() {
        val viewModel = createViewModel()

        viewModel.setImageQuality(ImageQuality.Ultra)

        coVerify(exactly = 1) { settingRepository.setImageQuality(ImageQuality.Ultra) }
    }

    private fun createViewModel(): ImageQualitySettingViewModel {
        return ImageQualitySettingViewModel(
            settingRepository = settingRepository
        )
    }
}
