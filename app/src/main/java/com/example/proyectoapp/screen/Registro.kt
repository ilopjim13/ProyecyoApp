package com.example.proyectoapp.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyectoapp.navigation.Login
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun RegistroScreen(navController: NavController, usuarioViewModel: UsuarioViewModel,context: Context, modifier: Modifier = Modifier) {
    Registro(navController,usuarioViewModel,context,modifier)
}

@Composable
fun Registro(navController: NavController, usuarioViewModel: UsuarioViewModel,context: Context, modifier: Modifier = Modifier) {
    val correo by usuarioViewModel.correo.observeAsState("")
    val pass by usuarioViewModel.pass.observeAsState("")
    val loginEnable by usuarioViewModel.loginEnable.observeAsState(false)
    val error by usuarioViewModel.error.observeAsState(false)
    val visibility by usuarioViewModel.visibility.observeAsState(false)
    val showDialog by usuarioViewModel.showDialog.observeAsState(false)
    val errorTexto by usuarioViewModel.errorTexto.observeAsState("")

    Box(Modifier.fillMaxSize()) {

        //Image(
        //    painter = painterResource(R.drawable.fondo),
        //    contentDescription = "fondo",
        //    Modifier.fillMaxSize(),
        //    contentScale = ContentScale.Crop
        //)

        Box(modifier.padding(top = 16.dp).align(Alignment.TopEnd)) {
            IconButton({navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "tumblr",
                fontSize = 62.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 140.dp, bottom = 30.dp)
            )

            Column(
                Modifier
                    .padding(start = 25.dp, end = 25.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MensajeError(error, errorTexto)

                CorreoField(
                    correo,
                    "Correo electrónico",
                    onChageValue = { usuarioViewModel.onChangeValue(it, pass) })
                PassField(
                    pass,
                    "Contraseña",
                    visibility,
                    onChageValue = { usuarioViewModel.onChangeValue(correo, it) },
                    onClickIcon = {usuarioViewModel.showVisibility(!visibility)})


                Espacio(10.dp)

                Boton("Registrate", loginEnable) {
                    if (usuarioViewModel.checkRegistro(correo)) {
                        usuarioViewModel.showDialog(true)
                        usuarioViewModel.saveUsuario(correo, pass)
                    }
                    usuarioViewModel.resetVariables()
                }

                IniciarSesion  {
                    navController.navigate(Login)
                    usuarioViewModel.resetVariables()
                    usuarioViewModel.resetError()
                }

                SeparadorOr()

                Espacio(5.dp)

                InicioConOtros(context)

                Espacio(15.dp)

                Pie(context)

            }
        }
    }
}

@Composable
fun IniciarSesion(onClickNav:()-> Unit) {
    Row {
        Text(
            "¿Ya tienes cuenta? ",
            color = Color.White,
            fontSize = 13.sp
        )
        Text(
            "Inicia Sesión",
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            fontSize = 13.sp,
            modifier = Modifier.clickable(onClick = onClickNav)
        )
    }
}