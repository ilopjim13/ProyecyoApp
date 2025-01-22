package com.example.proyectoapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoapp.R
import com.example.proyectoapp.navigation.Registro
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Login(navController, usuarioViewModel, modifier)
}

@Composable
fun Login(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    val correo by usuarioViewModel.correo.observeAsState("")
    val pass by usuarioViewModel.pass.observeAsState("")
    val loginEnable by usuarioViewModel.loginEnable.observeAsState(false)
    val error by usuarioViewModel.error.observeAsState(false)
    val visibility by usuarioViewModel.visibility.observeAsState(false)
    val errorTexto by usuarioViewModel.errorTexto.observeAsState("")

    Box(Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.fondoterminado),
            contentDescription = "fondo",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier.padding(top = 16.dp).align(Alignment.TopEnd)) {
            IconButton({navController.navigate("Portada")}) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Espacio(273.dp)

            Text("¡Sarracenito te echaba de menos!")

            Espacio(15.dp)

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
                    onClickIcon = { usuarioViewModel.showVisibility(!visibility) })

                Espacio(10.dp)


                Boton("Inicia sesión", loginEnable) {
                    if (usuarioViewModel.checkLogin(correo, pass)) {
                        usuarioViewModel.updateUserActive(correo)
                        navController.navigate("Menu")
                    }
                    usuarioViewModel.resetVariables()
                }


                OlvidadoContra()

                SeparadorOr()


                Espacio(5.dp)

                Registrarse {
                    navController.navigate(Registro)
                    usuarioViewModel.resetVariables()
                    usuarioViewModel.resetError()
                }

                Espacio(15.dp)

                Pie()
            }
        }
    }
}



@Composable
fun Espacio(altura: Dp) {
    Spacer(Modifier.height(altura))
}

@Composable
fun CorreoField(texto: String, placeholder: String, onChageValue: (String) -> Unit) {
    OutlinedTextField(
        value = texto,
        onValueChange = onChageValue,
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = textColors(),
        placeholder = { Text(placeholder, color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth().testTag("correoField"),
    )
    Espacio(5.dp)
}

@Composable
fun PassField(texto: String, placeholder: String, visibility:Boolean, onChageValue: (String) -> Unit, onClickIcon:() -> Unit) {
    OutlinedTextField(
        value = texto,
        onValueChange = onChageValue,
        shape = RoundedCornerShape(10.dp),
        colors = textColors(),
        singleLine = true,
        placeholder = { Text(placeholder, color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if(!visibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = onClickIcon) {
                Icon(
                    imageVector = if (visibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Visibility"
                )
            }
        }
    )
    Espacio(5.dp)
}

@Composable
fun OlvidadoContra() {

    Text(
        "¿Has olvidado tu contraseña?",
        fontSize = 13.sp
    )
}


@Composable
fun SeparadorOr() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            thickness = 1.dp, color = Color.Black, modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Text(
            "o  "
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
fun Registrarse(onClickNav:()-> Unit) {
    Row {
        Text(
            "¿Acabas de aterrizar? ",
            fontSize = 13.sp
        )
        Text(
            "¡Regístrate!",
            fontSize = 13.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick =  onClickNav) )
    }
}

@Composable
fun MensajeError(error:Boolean, errorTexto:String) {
    if (error) {
        Box(
            Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Color(
                        0xFFCE2323
                    )
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                errorTexto,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Boton(texto: String, loginEnable: Boolean, onClickChange: () -> Unit) {
    Button(
        onClick = onClickChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = loginEnable,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6D9ED), contentColor = Color.Black, disabledContentColor = Color.Black, disabledContainerColor = Color.LightGray)
    ) {
        Text(texto)
    }
}

@Composable
fun CondicionesPrivacidadText(texto: String) {
    Text(
        texto,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Pie() {
    Row {
        CondicionesPrivacidadText("Condiciones  ")
        CondicionesPrivacidadText("Privacidad  ")
        CondicionesPrivacidadText("Empleo  ")
        CondicionesPrivacidadText("Ayuda")
    }
}

@Composable
fun textColors() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color.Black,
    unfocusedContainerColor = Color.White,
    focusedBorderColor = Color.Black,
    focusedContainerColor = Color.White,
    cursorColor = Color.Black,
)


