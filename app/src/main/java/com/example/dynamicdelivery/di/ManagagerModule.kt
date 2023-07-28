package com.example.dynamicdelivery.di

import android.content.Context
import com.example.dynamicdelivery.managers.LanguageInstallationManager
import com.example.dynamicdelivery.managers.LanguageInstallationManagerImpl
import com.example.dynamicdelivery.managers.ModuleInstallationManager
import com.example.dynamicdelivery.managers.ModuleInstallationManagerImpl
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ManagersModule {

    @ViewModelScoped
    @Provides
    fun providesSplitInstallManager(
        @ApplicationContext context: Context,
    ): SplitInstallManager =
        SplitInstallManagerFactory.create(context)

    @ViewModelScoped
    @Provides
    fun providesLanguageInstallationManager(
        splitInstallManager: SplitInstallManager,
    ): LanguageInstallationManager =
        LanguageInstallationManagerImpl(splitInstallManager = splitInstallManager)

    @ViewModelScoped
    @Provides
    fun providesDynamicModuleInstallationManager(
        splitInstallManager: SplitInstallManager,
    ): ModuleInstallationManager =
        ModuleInstallationManagerImpl(splitInstallManager = splitInstallManager)
}
