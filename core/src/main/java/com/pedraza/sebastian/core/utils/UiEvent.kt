package com.pedraza.sebastian.core.utils

sealed interface UiEvent{
    data class Navigate(val route: String): UiEvent
    object NavigateUp: UiEvent
    data class ShowSnackBar(val uiText: UiText): UiEvent
}