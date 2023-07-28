package com.example.dynamicdelivery.managers

import com.example.dynamicdelivery.extensions.toInstallationState
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import java.util.Locale

interface DynamicDeliveryManager<T> {

    fun startInstallation(param: T): Flow<InstallationState>

    enum class InstallationState {
        UNKNOWN,
        PENDING,
        REQUIRED_CONFIRMATION,
        DOWNLOADING,
        DOWNLOADED,
        INSTALLING,
        INSTALLED,
        FAILED,
        CANCELING,
        CANCELED
    }
}

interface LanguageInstallationManager : DynamicDeliveryManager<Locale>

class LanguageInstallationManagerImpl(
    private val splitInstallManager: SplitInstallManager,
) : LanguageInstallationManager {

    override fun startInstallation(param: Locale): Flow<DynamicDeliveryManager.InstallationState> {
        if (param.language in splitInstallManager.installedLanguages) {
            return flowOf(DynamicDeliveryManager.InstallationState.INSTALLED)
        }

        return callbackFlow {
            val listener = SplitInstallStateUpdatedListener { splitInstallSessionState ->

                val installationState = splitInstallSessionState.toInstallationState

                trySend(installationState)
            }

            val request = createRequest(param)

            splitInstallManager.startInstall(request)

            splitInstallManager.registerListener(listener)

            awaitClose { splitInstallManager.unregisterListener(listener) }
        }
    }

    private fun createRequest(language: Locale): SplitInstallRequest =
        SplitInstallRequest
            .newBuilder()
            .addLanguage(language)
            .build()

}

interface ModuleInstallationManager : DynamicDeliveryManager<String>

class ModuleInstallationManagerImpl(
    private val splitInstallManager: SplitInstallManager,
) : ModuleInstallationManager {

    override fun startInstallation(param: String): Flow<DynamicDeliveryManager.InstallationState> {
        if (param in splitInstallManager.installedModules) {
            return flowOf(DynamicDeliveryManager.InstallationState.INSTALLED)
        }

        return callbackFlow {
            val listener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
                val installationState = splitInstallSessionState.toInstallationState

                trySend(installationState)
            }

            val request = createRequest(param)

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {

                }
                .addOnCompleteListener {

                }
                .addOnFailureListener {

                }

            splitInstallManager.registerListener(listener)

            awaitClose { splitInstallManager.unregisterListener(listener) }
        }
    }

    private fun createRequest(moduleName: String): SplitInstallRequest =
        SplitInstallRequest
            .newBuilder()
            .addModule(moduleName)
            .build()

}
