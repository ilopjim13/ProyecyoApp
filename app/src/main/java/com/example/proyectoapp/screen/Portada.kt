package com.example.proyectoapp.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun PortadaScreen(navHostController: NavHostController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Portada(navHostController,usuarioViewModel,modifier)
}

@Composable
fun Portada(navHostController: NavHostController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {

}