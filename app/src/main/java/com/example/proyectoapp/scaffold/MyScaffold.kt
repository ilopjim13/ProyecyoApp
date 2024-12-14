package com.example.proyectoapp.scaffold

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectoapp.appBar.MyTopAppBar
import com.example.proyectoapp.navigation.AppAlimentos
import com.example.proyectoapp.navigation.Menu
import com.example.proyectoapp.navigation.Portada
import com.example.proyectoapp.ui.theme.ProyecyoAppTheme
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel
import kotlinx.coroutines.launch

@Composable
fun MyScaffold(application: Application) {
    ProyecyoAppTheme {
        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        val navigationController = rememberNavController()
        val context = LocalContext.current
        val usuarioViewModel = UsuarioViewModel(application)
        val usuarioActivo = usuarioViewModel.userActive()

        ModalNavigationDrawer(
            drawerContent = {

                val items = listOf(
                    NavigationItem(
                        title = "Mi Perfil",
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Filled.AccountCircle
                    ),
                    NavigationItem(
                        title = "Sobre nosotros",
                        selectedIcon = Icons.Filled.Explore,
                        unselectedIcon = Icons.Filled.Explore
                    ),
                    NavigationItem(
                        title = "Novedades",
                        selectedIcon = Icons.Filled.NewReleases,
                        unselectedIcon = Icons.Filled.NewReleases
                    ),
                    NavigationItem(
                        title = "Cerrar Sesión",
                        selectedIcon = Icons.Filled.SentimentVeryDissatisfied,
                        unselectedIcon = Icons.Filled.SentimentVeryDissatisfied
                    ),
                )
                Column(
                    Modifier
                        .width(360.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                        .background(Color(0xFF001935))
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                if(item.title == "Cerrar Sesión") {
                                    if (usuarioActivo != null) {
                                        usuarioViewModel.updateUserActive(usuarioActivo.correo)
                                    }
                                    usuarioViewModel.resetVariables()
                                    usuarioViewModel.resetError()
                                    navigationController.navigate("Portada")
                                }
                                else {

                                    val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse("https://www.tumblr.com/explore/trending") }
                                    context.startActivity(intent)
                                }
                                coroutineScope.launch { drawerState.close() }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                item.badgeCount?.let { Text(text = item.badgeCount.toString()) }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            },
            drawerState = drawerState
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { if (navigationController.currentBackStackEntryAsState().value?.destination?.route == "Menu") { MyTopAppBar { coroutineScope.launch { drawerState.open() } } } }){ innerPadding ->
                AppAlimentos(modifier = Modifier.padding(innerPadding), navigationController, usuarioViewModel, context, usuarioActivo)
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)
