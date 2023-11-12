package ir.thatsmejavad.backgroundable.viewmodels

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.main.MainViewModel
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    @Before
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

        viewModel.userPreferences.test {
            awaitItem() shouldBe UserPreferences(imageQuality = ImageQuality.Medium)
        }
    }

    private fun createViewModel(): MainViewModel {
        return MainViewModel(
            settingRepository = settingRepository
        )
    }
}
