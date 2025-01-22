package com.example.proyectoapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoapp.R
import com.example.proyectoapp.navigation.Login
import com.example.proyectoapp.navigation.Registro

@Composable
fun PortadaScreen(navController: NavController, modifier: Modifier = Modifier) {
    Portada(navController,modifier)
}

@Composable
fun Portada(navController: NavController, modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.fondoterminado),
            contentDescription = "Fondo",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {

            Box{}

            Spacer(Modifier.height(30.dp))

            Text("Bienvenido a CulinaryHero")

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding( end = 24.dp, start = 24.dp).fillMaxWidth()) {
                Button(
                    onClick = {navController.navigate(Login)},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF6D9ED),
                        contentColor = Color.Black
                    )
                    ) {
                    Text("Iniciar sesión")
                }
                Spacer(Modifier.height(15.dp))
                Button(
                    onClick = {navController.navigate(Registro)},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF6D9ED),
                        contentColor = Color.Black,
                    )
                ) {
                    Text("Registrarse")
                }
            }

        }
    }

}