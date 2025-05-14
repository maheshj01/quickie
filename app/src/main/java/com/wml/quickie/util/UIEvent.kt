package com.wml.quickie.util

sealed class UIEvent {
    object popBackStack : UIEvent()
    data class Navigate(val route: String) : UIEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ) : UIEvent()
}