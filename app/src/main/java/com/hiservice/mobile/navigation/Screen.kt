package com.services.finalsubmissionjetpackcompose.ui.navigation

sealed class Screen(val route: String) {

    /* No Connection Screen */
    object NoConnection : Screen("no-connection")

    /* Init and Auth Screen */
    object OnBoard : Screen("on-board")
    object Login : Screen("login")
    object Register : Screen("register")

    //After Login Page
    object Dashboard : Screen("dashboard")

    /* Service Screen */
    object Service_Detail : Screen("service/detail")
}