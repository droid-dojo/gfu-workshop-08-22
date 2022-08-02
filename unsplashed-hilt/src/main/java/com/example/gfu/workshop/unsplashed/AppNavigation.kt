package com.example.gfu.workshop.unsplashed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gfu.workshop.unsplashed.photo.list.ui.TrendingPhotosScreen
import com.example.gfu.workshop.unsplashed.user.ui.UserDetailScreen

@Composable
fun AppNavigation() {

    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = "home") {

        composable(route = "home") {
            TrendingPhotosScreen(
                viewModel = hiltViewModel(it),
                onNavigateToUser = { controller.navigate(route = "user/$it") }
            )
        }

        composable(route = "user/{userId}") {
            UserDetailScreen()
        }

    }
}