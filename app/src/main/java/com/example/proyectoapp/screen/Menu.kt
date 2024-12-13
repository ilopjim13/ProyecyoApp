package com.example.proyectoapp.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun MenuScreen(navHostController: NavHostController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Menu(navHostController,usuarioViewModel,modifier)
}

@Composable
fun Menu(navHostController: NavHostController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {

}