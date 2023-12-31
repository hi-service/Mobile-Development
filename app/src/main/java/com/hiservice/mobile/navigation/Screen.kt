package com.services.finalsubmissionjetpackcompose.ui.navigation

sealed class Screen(override val route: String) : NavigationDestination {
    /* Splash Screen */
    object Splash : Screen("splash")
    /* No Connection Screen */
    object NoConnection : Screen("no-connection")

    /* Init and Auth Screen */
    object OnBoard : Screen("on-board")
    object Login : Screen("login")
    object Register : Screen("register")

    //After Login Page
    object Dashboard : Screen("dashboard")

    object DetailArticle : Screen("article/{articleID}"){
        fun createRoute(articleID: Int) = "article/$articleID"
    }

    /*Navigation Drawer Screen*/
    object Profile : Screen("profile")
    object About : Screen("about")
    object Setting : Screen("settings")

    /* Service Screen */
    object Service_Detail : Screen("service/detail")
    object Service_Keluhan_User : Screen("service/keluhan")
    object Service_Daftar_Bengkel : Screen("service/daftar-bengkel")
    object Service_Konfirmasi_Order : Screen("service/konfirmasi-order/{idBengkel}"){
        fun createRoute(idBengkel: Int) = "service/konfirmasi-order/$idBengkel"
    }
    object Service_Status_Order : Screen("service/status-order")
    object Service_Chat_Order : Screen("service/chat-order")
    /* History Service */
    object History_Service : Screen("history")

    /* Shop Service */
    object Shop_User_Detail : Screen("shop/detail")
    object Shop_Bengkel_List : Screen("shop/list-bengkel")
    object Shop_Data_Item : Screen("shop/data_item/{idBengkel}"){
        fun createRoute(idBengkel: Int) = "shop/data_item/$idBengkel"
    }


    /* Dashboard Menu */
    object Faq : Screen("faq")
}

interface NavigationDestination {
    val route: String
}
