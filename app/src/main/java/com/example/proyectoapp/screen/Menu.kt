package com.example.proyectoapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.proyectoapp.model.Alimento
import com.example.proyectoapp.navigation.Login
import com.example.proyectoapp.navigation.Menu
import com.example.proyectoapp.navigation.Registro
import com.example.proyectoapp.usuarioViewModel.UsuarioViewModel
import kotlin.math.sin

@Composable
fun MenuScreen(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {
    Menu(navController,usuarioViewModel,modifier)
}

@Composable
fun Menu(navController: NavController, usuarioViewModel: UsuarioViewModel, modifier: Modifier = Modifier) {

    val alimentosSinGluten = listOf(
        Alimento( nombre = "Pan de molde", marca = "Schar", etiqueta = "Sin Gluten", imagenUrl = "https://img2.miravia.es/g/fb/kf/E7cd55c4979d44131b600c55b7268a898J.png_720x720q75.png_.webp" ),
        Alimento( nombre = "Harina de almendra", marca = "NaturGreen", etiqueta = "Sin Gluten", imagenUrl = "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202311/27/00120754600433____4__600x600.jpg" ),
        Alimento( nombre = "Pasta de arroz", marca = "Tinkyada", etiqueta = "Sin Gluten", imagenUrl = "https://m.media-amazon.com/images/I/91kCujKKo5L._SX522_PIbundle-12,TopRight,0,0_SX522SY666SH20_.jpg" ),
        Alimento( nombre = "Penne", marca = "Tinkyada", etiqueta = "Sin Gluten", imagenUrl = "https://greenparadise.com.mx/uploads/Pasta%20Penne%20de%20Arroz%20Org%C3%A1nico%20Sin%20Gluten%20Tinkyada%20340%20g.jpeg" ),
        Alimento( nombre = "Corne flakes", marca = "Nestle", etiqueta = "Sin Gluten", imagenUrl = "https://static.carrefour.es/hd_510x_/img_pim_food/887619_00_1.jpg" ) )

    val alimentosSinLactosa = listOf(
        Alimento(nombre = "Leche de almendra", marca = "Alpro", etiqueta = "Sin Lactosa", imagenUrl = "https://static.carrefour.es/hd_510x_/img_pim_food/732758_00_1.jpg"),
        Alimento(nombre = "Yogur de coco", marca = "So Delicious", etiqueta = "Sin Lactosa", imagenUrl = "https://californiaranchmarket.com/cdn/shop/products/102002.jpg?v=1622478695"),
        Alimento(nombre = "Queso sin lactosa", marca = "Hacendado", etiqueta = "Sin Lactosa", imagenUrl = "https://finditapp.es/product/23580/queso-sin-lactosa-hacendado-lonchas"),
        Alimento(nombre = "Mantequilla de almendra", marca = "Naturhouse", etiqueta = "Sin Lactosa", imagenUrl = "https://d3gr7hv60ouvr1.cloudfront.net/CACHE/images/products/a154e0c2-8b52-4626-bdd0-9b4a622e7b85/e8f378e39304fe568a7772f0c754badd.jpg"),
        Alimento(nombre = "Helado de soja", marca = "Ben and Jerry's", etiqueta = "Sin Lactosa", imagenUrl = "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202304/26/00118952006924____7__1200x1200.jpg"))

    val alimentosVeganos = listOf(
        Alimento(nombre = "Tofu", marca = "Morinaga", etiqueta = "Vegano", imagenUrl = "https://www.orientalmarket.es/shop/15855-medium_default/tofu-morinaga-349-g.jpg"),
        Alimento(nombre = "Leche de avena", marca = "Oatly", etiqueta = "Vegano", imagenUrl = "https://www.biosano.es/cdnassets//products/large/oat001_l-1-.jpg"),
        Alimento(nombre = "Salmon vegano", marca = "MyWay", etiqueta = "Vegano", imagenUrl = "https://images.openfoodfacts.org/images/products/200/406/009/6162/front_es.3.400.jpg"),
        Alimento(nombre = "Nutella vegana", marca = "Nocciolata", etiqueta = "Vegano", imagenUrl = "https://img2.miravia.es/g/fb/kf/Eb86a9cb498cf40fe8c063594c5f35cb1F.jpg_720x720q75.jpg_.webp"),
        Alimento(nombre = "Helado de coco", marca = "Ben and Jerry's", etiqueta = "Vegano", imagenUrl = "https://cdn11.bigcommerce.com/s-bdoyvle6ab/images/stencil/1280x1280/products/189/588/Ben__jerry_coconutterly_caramel_m__90050.1641264843.jpg?c=1"))


    Column(modifier.padding(end = 16.dp, start = 16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier.height(100.dp))

        Botones()

        Spacer(Modifier.height(20.dp))

        Text("Sin gluten")

        LazyRow {
            items(alimentosSinGluten.size) {
                Alimentos(alimentosSinGluten[it])
                Spacer(Modifier.width(15.dp))
            }
        }

        Spacer(Modifier.height(20.dp))

        Text("Sin Lactosa")

        LazyRow {
            items(alimentosSinLactosa.size) {
                Alimentos(alimentosSinLactosa[it])
                Spacer(Modifier.width(15.dp))
            }
        }

        Spacer(Modifier.height(20.dp))


        Text("Vegano")

        LazyRow {
            items(alimentosVeganos.size) {
                Alimentos(alimentosVeganos[it])
                Spacer(Modifier.width(15.dp))
            }
        }

    }

}

@Composable
fun Alimentos(alimento: Alimento) {

    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(alimento.imagenUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Alimento",
            modifier = Modifier.size(120.dp)
        )

        Text(alimento.nombre)
        Text(alimento.marca)
        Text(alimento.etiqueta)
    }
}



@Composable
fun Botones() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button({}) {
            Text("Sin gluten")
        }
        Button({}) {
            Text("Sin lactosa")
        }
        Button({}) {
            Text("Vegano")
        }

    }
}
