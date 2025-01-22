package com.example.proyectoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectoapp.model.Usuario
import com.example.proyectoapp.screen.LoginScreen
import com.example.proyectoapp.screen.MenuScreen
import com.example.proyectoapp.screen.PortadaScreen
import com.example.proyectoapp.screen.RegistroScreen
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel


@Composable
fun AppAlimentos(modifier:Modifier = Modifier, navigationController: NavHostController, usuarioViewModel: UsuarioViewModel, usuarioActivo:Usuario?) {

    NavHost(
        navController = navigationController,
        startDestination = if (usuarioActivo == null) "Portada" else "Menu"
    ) {
        composable("Portada") {
            PortadaScreen(navigationController, modifier)
        }
        composable<Login> {
            LoginScreen(navigationController, usuarioViewModel)
        }
        composable<Registro> {
            RegistroScreen(navigationController, usuarioViewModel)
        }
        composable("Menu") {
            MenuScreen(modifier)
        }
    }
}
