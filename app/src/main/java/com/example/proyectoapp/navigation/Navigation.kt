package com.example.proyectoapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectoapp.screen.LoginScreen
import com.example.proyectoapp.screen.MenuScreen
import com.example.proyectoapp.screen.PortadaScreen
import com.example.proyectoapp.screen.RegistroScreen
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel


@Composable
fun AppAlimentos(modifier:Modifier = Modifier, navigationController: NavHostController, usuarioViewModel: UsuarioViewModel, context:Context) {
    NavHost(
        navController = navigationController,
        startDestination = Login
    ) {
        composable<Portada> {
            PortadaScreen(navigationController, usuarioViewModel)
        }
        composable<Login> {
            LoginScreen(navigationController, usuarioViewModel)
        }
        composable<Registro> {
            RegistroScreen(navigationController, usuarioViewModel)
        }
        composable<Menu> {
            MenuScreen(navigationController, usuarioViewModel)
        }
    }
}
