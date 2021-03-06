package ru.gubatenko.domain

object TextKey {
    object Common {
        const val ERROR = "error"
        const val RETRY = "retry"
    }

    object Main {
        const val SAVE = "save"
        const val NEXT = "next"
    }

    object Promise {
        const val EMPTY_LIST = "promise_empty_list"
    }

    object Auth {
        const val OFFER_AUTH_LABEL = "offer_auth_label"
        const val OFFER_AUTH_BUTTON = "offer_auth_button"
    }

    object Profile {
        const val PREF_NAME_THEME = "pref_name_theme"
        const val SIGN_IN = "sign_in"
        const val SIGN_OUT = "sign_out"
        const val DEFAULT_NAME = "default_name"
    }

    object Dynamic {
        const val ABOUT = "about"
    }
}
