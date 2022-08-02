package com.example.gfu.workshop.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gfu.workshop.posts.ui.PostListScreen
import com.example.gfu.workshop.user.UserViewModel
import com.example.gfu.workshop.user.ui.UserScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        home(navController = navController)

        composable("posts") {
            PostListScreen(navigateToHome = { navController.popBackStack() })
        }

        composable(
            route = "user?userId={userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                    defaultValue = 99
                },
            ),
            deepLinks = listOf(
                navDeepLink {this.uriPattern = "https://www.google.de/{userId}"  }
            )
        ) { backStackEntry ->
            val vm: UserViewModel = viewModel(backStackEntry)
            UserScreen(viewModel = vm)
        }

    }
}

fun NavController.toPosts() = navigate(route = "posts")

fun NavGraphBuilder.home(navController: NavController) {
    composable(route = "home") {
        Column() {


            Button(onClick = { navController.toPosts() }) {
                Text("Zu den Posts")
            }
            Button(onClick = { navController.navigate("user?userId=1") }) {
                Text("User 1")
            }

            Button(onClick = { navController.navigate("user?userId=2") }) {
                Text("User 2")
            }
        }
    }
}

