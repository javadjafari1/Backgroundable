package ir.thatsmejavad.backgroundable.viewmodels

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class ThemeSettingViewModelTest {

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
    }

    @Test
    fun `userPreferencesFlow should be updated with data of settingRepo's userPreferencesFlow`() =
        runTest {
            every { settingRepository.userPreferencesFlow } returns flowOf(UserPreferences())
            val viewModel = createViewModel()

            verify(exactly = 1) { settingRepository.userPreferencesFlow }

            viewModel.userPreferencesFlow.test {
                awaitItem() shouldBe UserPreferences()
            }
        }

    @Test
    fun `repo's setThemeMode should call when updateTheme called`() {
        val viewModel = createViewModel()
        viewModel.updateTheme(Theme.LightTheme)

        coVerify { settingRepository.setThemeMode(Theme.LightTheme) }
    }

    @Test
    fun `repo's setMaterialYouIsEnabled should call when updateIsMaterialYouEnabled called`() {
        val viewModel = createViewModel()
        viewModel.updateIsMaterialYouEnabled(true)

        coVerify { settingRepository.setMaterialYouIsEnabled(true) }
    }

    @Test
    fun `repo's setThemeColor should call when updateThemeColor called`() {
        val viewModel = createViewModel()
        viewModel.updateThemeColor(ThemeColor.Ao)

        coVerify { settingRepository.setThemeColor(ThemeColor.Ao) }
    }

    private fun createViewModel(): ThemeSettingViewModel {
        return ThemeSettingViewModel(
            settingRepository = settingRepository
        )
    }
}
