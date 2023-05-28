package com.mahapp1397.codelearn2.navigation

sealed class Screen(val rout: String)
{
    object Splash: Screen("splash_screen")

    object Login: Screen("login_screen")

    object Register: Screen("register_screen")

    object Home: Screen("home_screen")

    object ProfileForm: Screen("profile_form_screen")

    object Details: Screen("details_screen/{profileId}"){
        fun passProfileId(profileId: Int): String{
            return "details_screen/$profileId"
        }
    }
}
