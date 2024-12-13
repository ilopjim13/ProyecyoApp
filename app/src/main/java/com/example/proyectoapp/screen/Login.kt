package com.example.proyectoapp.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.logintumblr.R
import com.example.proyectoapp.navigation.Registro
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavController, usuarioViewModel: UsuarioViewModel,context: Context, modifier: Modifier = Modifier) {
    Login(navController, usuarioViewModel ,context, modifier)
}

@Composable
fun Login(navController: NavController, usuarioViewModel: UsuarioViewModel,context: Context, modifier: Modifier = Modifier) {
    val correo by usuarioViewModel.correo.observeAsState("")
    val pass by usuarioViewModel.pass.observeAsState("")
    val loginEnable by usuarioViewModel.loginEnable.observeAsState(false)
    val error by usuarioViewModel.error.observeAsState(false)
    val visibility by usuarioViewModel.visibility.observeAsState(false)
    val showDialog by usuarioViewModel.showDialog.observeAsState(false)
    val errorTexto by usuarioViewModel.errorTexto.observeAsState("")

    if (showDialog) {
        DialogLogin("Has iniciado sesión correctamente") { usuarioViewModel.showDialog(false) }
    }

    Box(Modifier.fillMaxSize()) {

        //Image(
        //    painter = painterResource(R.drawable.fondo),
        //    contentDescription = "fondo",
        //    Modifier.fillMaxSize(),
        //    contentScale = ContentScale.Crop
        //)

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
                    onClickIcon = { usuarioViewModel.showVisibility(!visibility) })

                MensajeCondiciones()

                Espacio(10.dp)


                Boton("Inicia sesión", loginEnable) {
                    if (usuarioViewModel.checkLogin(correo, pass)) usuarioViewModel.showDialog(true)
                    usuarioViewModel.resetVariables()
                }


                OlvidadoContra {
                    //navController.navigate(PassOlvidado)
                    usuarioViewModel.resetVariables()
                    usuarioViewModel.resetError()
                }

                SeparadorOr()

                Espacio(5.dp)

                InicioConOtros(context)

                Espacio(5.dp)

                Registrarse {
                    navController.navigate(Registro)
                    usuarioViewModel.resetVariables()
                    usuarioViewModel.resetError()
                }

                Espacio(15.dp)

                Pie(context)
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
        modifier = Modifier.fillMaxWidth(),
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
fun InicioConOtros(context: Context) {
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Faccounts.google.com%2F&followup=https%3A%2F%2Faccounts.google.com%2F&ifkv=AcMMx-epW4NV0aofmsIjnucKlvHS4tDh6jdHBqr3-9GmrtBANI4eA_G5Zun5XWYcmIBo3DV1HeOHSw&passive=1209600&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-2044595131%3A1732884865399079&ddm=1") }
            context.startActivity(intent) },
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.textButtonColors(Color.White, Color.Black)
    ) {
        //Image(
        //    painter = painterResource(R.drawable.g),
        //    contentDescription = "",
        //    modifier = Modifier.size(16.dp)
        //)
        Text(
            "  Inicia sesión con Google"
        )
    }

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse("https://appleid.apple.com/auth/authorize?state=eyJyZWRpcmVjdFRvIjoidW5kZWZpbmVkIn0%3D&scope=name%20email&response_type=code&response_mode=form_post&redirect_uri=https%3A%2F%2Fwww.tumblr.com%2Fauth%2Fapple%2Fcallback&client_id=com.tumblr.siwa.redpop.production") }
            context.startActivity(intent)
        },
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.textButtonColors(Color.White, Color.Black)
    ) {
        //Image(
        //    painter = painterResource(R.drawable.a),
        //    contentDescription = "",
        //    modifier = Modifier.size(16.dp)
        //)
        Text(
            "  Inicia sesión con Apple  "
        )
    }
}

@Composable
fun OlvidadoContra(onClickNav:() -> Unit) {

    Text(
        "¿Has olvidado tu contraseña?",
        color = Color.White,
        fontSize = 13.sp,
        modifier = Modifier.clickable(onClick = onClickNav)
    )
}


@Composable
fun SeparadorOr() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            thickness = 1.dp, color = Color.White, modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Text(
            "o  ",
            color = Color.White
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
fun Registrarse(onClickNav:()-> Unit) {
    Row {
        Text(
            "¿Acabas de aterrizar en Tumblr? ",
            color = Color.White,
            fontSize = 13.sp
        )
        Text(
            "¡Regístrate!",
            color = Color.White,
            fontSize = 13.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick =  onClickNav) )
    }
}

@Composable
fun MensajeCondiciones() {

    val annotatedText = buildAnnotatedString {
        append("Al hacer clic en el botón de inicio de sesión o seguir adelante con el resto de opciones a continuación, aceptas las ")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) { append("Condiciones de uso") }
        append(" y confirmas que has leído la ")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) { append("Política de privacidad") }
        append(" de privacidad de Tumblr")
    }

    Text(
        text = annotatedText,
        color = Color.White,
        fontSize = 11.sp,
        textAlign = TextAlign.Center
    )
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
                textAlign = TextAlign.Center,
                color = Color.White
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
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00b8ff), contentColor = Color.Black, disabledContentColor = Color.Black, disabledContainerColor = Color.LightGray)
    ) {
        Text(texto)
    }
}

@Composable
fun CondicionesPrivacidadText(texto: String, url: String, context: Context) {
    Text(
        texto,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
            context.startActivity(intent)
        }
    )
}

@Composable
fun Pie(context: Context) {
    Row {
        CondicionesPrivacidadText("Condiciones  ", "https://www.tumblr.com/policy/es/terms-of-service", context)
        CondicionesPrivacidadText("Privacidad  ", "https://www.tumblr.com/privacy", context)
        CondicionesPrivacidadText("Empleo  ", "https://www.tumblr.com/jobs", context)
        CondicionesPrivacidadText("Ayuda", "https://www.tumblr.com/support", context)
    }
}


@Composable
fun DialogLogin(
    dialogMenssage:String,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = dialogMenssage,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { onDismissRequest() }) {
                    Text("Aceptar")
                }

            }
        }
    }
}





@Composable
fun textColors() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedBorderColor = Color.White,
    focusedContainerColor = Color.White,
    cursorColor = Color.Black,
)


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)
