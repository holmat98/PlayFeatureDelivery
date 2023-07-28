package com.example.dynamicdelivery.extensions

import com.example.dynamicdelivery.managers.DynamicDeliveryManager.InstallationState
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

val SplitInstallSessionState.toInstallationState: InstallationState
    get() = when (status()) {
        SplitInstallSessionStatus.PENDING -> InstallationState.PENDING
        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> InstallationState.REQUIRED_CONFIRMATION
        SplitInstallSessionStatus.DOWNLOADING -> InstallationState.DOWNLOADING
        SplitInstallSessionStatus.DOWNLOADED -> InstallationState.DOWNLOADED
        SplitInstallSessionStatus.INSTALLING -> InstallationState.INSTALLING
        SplitInstallSessionStatus.INSTALLED -> InstallationState.INSTALLED
        SplitInstallSessionStatus.FAILED -> InstallationState.FAILED
        SplitInstallSessionStatus.CANCELING -> InstallationState.CANCELING
        SplitInstallSessionStatus.CANCELED -> InstallationState.CANCELED
        else -> InstallationState.UNKNOWN
    }

val Int.toInstallationState: InstallationState
get() = when (this) {
    SplitInstallSessionStatus.PENDING -> InstallationState.PENDING
    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> InstallationState.REQUIRED_CONFIRMATION
    SplitInstallSessionStatus.DOWNLOADING -> InstallationState.DOWNLOADING
    SplitInstallSessionStatus.DOWNLOADED -> InstallationState.DOWNLOADED
    SplitInstallSessionStatus.INSTALLING -> InstallationState.INSTALLING
    SplitInstallSessionStatus.INSTALLED -> InstallationState.INSTALLED
    SplitInstallSessionStatus.FAILED -> InstallationState.FAILED
    SplitInstallSessionStatus.CANCELING -> InstallationState.CANCELING
    SplitInstallSessionStatus.CANCELED -> InstallationState.CANCELED
    else -> InstallationState.UNKNOWN
}