package com.example.dynamicdelivery.di

import com.example.dynamicdelivery.managers.LanguageAssistant
import com.example.dynamicdelivery.managers.LanguageAssistantImpl
import com.example.dynamicdelivery.managers.PreferenceAssistant
import com.example.dynamicdelivery.managers.PreferenceAssistantImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AssistantModule {

    @Singleton
    @Binds
    abstract fun bindsPreferenceAssistant(
        preferenceAssistantImpl: PreferenceAssistantImpl,
    ): PreferenceAssistant

    @Singleton
    @Binds
    abstract fun bindsLanguageManager(languageAssistantImpl: LanguageAssistantImpl): LanguageAssistant
}