package com.example.proyectoapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoapp.navigation.Login
import com.example.proyectoapp.navigation.Registro
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun PortadaScreen(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Portada(navController,usuarioViewModel,modifier)
}

@Composable
fun Portada(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {

        Icon(
            imageVector = Icons.Filled.Adb,
            contentDescription = ""
        )

        Text("Bienvenido a ....")

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {navController.navigate(Login)} ) {
                Text("Iniciar sesión")
            }

            Button({navController.navigate(Registro)}) {
                Text("Registrarse")
            }
        }

    }
}