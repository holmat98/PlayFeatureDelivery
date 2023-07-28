package com.example.dynamicdelivery.ui.moduleinstallation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicdelivery.managers.DynamicDeliveryManager
import com.example.dynamicdelivery.managers.ModuleInstallationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ModuleInstallationViewModel @Inject constructor(
    moduleInstallationManager: ModuleInstallationManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val moduleName: String = savedStateHandle[MODULE_NAME_ARGUMENT] ?: ""

    val installationState: StateFlow<DynamicDeliveryManager.InstallationState> =
        moduleInstallationManager.startInstallation(moduleName)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = DynamicDeliveryManager.InstallationState.PENDING
            )

    companion object {
        const val MODULE_NAME_ARGUMENT = "moduleName"
    }
}