package com.pedraza.sebastian.core.utils

import android.content.Context

sealed class UiText {
    data class DynamicString(val text: String) : UiText()
    data class ResourcesString(val resId: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> text
            is ResourcesString -> context.getString(resId)
        }
    }
}