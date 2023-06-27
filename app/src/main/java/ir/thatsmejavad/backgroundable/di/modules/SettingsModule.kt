package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.data.repository.SettingRepositoryImpl

@Module
interface SettingsModule {
    @Binds
    fun bindSettingsRepository(impl: SettingRepositoryImpl): SettingRepository
}
