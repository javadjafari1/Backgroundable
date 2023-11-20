package ir.thatsmejavad.backgroundable.viewmodels

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import ir.thatsmejavad.backgroundable.common.CoroutineTest
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.main.MainViewModel
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainViewModelTest : CoroutineTest {

    override lateinit var testScope: TestScope
    override lateinit var dispatcher: TestDispatcher

    @RelaxedMockK
    lateinit var settingRepository: SettingRepository

    @BeforeEach
    fun setUp() {
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
