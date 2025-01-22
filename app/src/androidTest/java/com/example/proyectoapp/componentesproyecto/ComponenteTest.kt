package com.example.proyectoapp.componentesproyecto

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.proyectoapp.model.Alimento
import com.example.proyectoapp.screen.Alimentos
import com.example.proyectoapp.screen.Boton
import com.example.proyectoapp.screen.Botones
import com.example.proyectoapp.screen.CorreoField
import com.example.proyectoapp.screen.MensajeError
import com.example.proyectoapp.screen.Menu
import com.example.proyectoapp.screen.Registrarse
import org.junit.Rule
import org.junit.Test

class ComponenteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun correoField_updatesTextCorrectly() {
        composeTestRule.setContent {
            var correo by remember { mutableStateOf("") }

            CorreoField(
                texto = correo,
                placeholder = "Correo electrónico",
                onChageValue = { correo = it }
            )
        }
        composeTestRule.onNodeWithText("Correo electrónico").assertExists()
        composeTestRule.onNodeWithTag("correoField").performTextInput("ivan@gmail.com")
        composeTestRule.onNodeWithTag("correoField").assertTextEquals("ivan@gmail.com")
    }
    @Test
    fun boton_isEnabledAndClickable() {
        var clicked = false

        composeTestRule.setContent {
            Boton(
                texto = "Inicia sesión",
                loginEnable = true,
                onClickChange = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Inicia sesión")
            .assertIsEnabled()
            .performClick()

        assert(clicked)
    }

    @Test
    fun mensajeError_displaysErrorMessage() {
        val errorMessage = "Correo o contraseña incorrectos"

        composeTestRule.setContent {
            MensajeError(error = true, errorTexto = errorMessage)
        }

        composeTestRule.onNodeWithText(errorMessage)
            .assertExists()
    }



    @Test
    fun registrarse_navigatesWhenClicked() {
        var navigated = false

        composeTestRule.setContent {
            Registrarse(onClickNav = { navigated = true })
        }

        composeTestRule.onNodeWithText("¡Regístrate!")
            .assertExists()
            .performClick()

        assert(navigated)
    }

    @Test
    fun testButtonsExistAndClickable() {
        composeTestRule.setContent {
            Botones()
        }

        composeTestRule.onNodeWithText("Sin gluten").assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText("Sin lactosa").assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText("Vegano").assertExists().assertHasClickAction()
    }

    @Test
    fun testLazyRowShowsAlimentos() {
        val alimentosSinGluten = listOf(
            Alimento("Pan de molde", "Schar", "Sin Gluten", "https://img2.miravia.es/g/fb/kf/E7cd55c4979d44131b600c55b7268a898J.png_720x720q75.png_.webp")
        )

        composeTestRule.setContent {
            Menu(
                alimentosSinGluten = alimentosSinGluten,
                alimentosSinLactosa = emptyList(),
                alimentosVeganos = emptyList()
            )
        }

        composeTestRule.onNodeWithText("Pan de molde").assertExists()
        composeTestRule.onNodeWithText("Schar").assertExists()
        composeTestRule.onNodeWithText("Sin Gluten").assertExists()
    }

    @Test
    fun testAlimentosDisplaysCorrectInfo() {
        val alimento = Alimento(
            nombre = "Pan de molde",
            marca = "Schar",
            etiqueta = "Sin Gluten",
            imagenUrl = "https://img2.miravia.es/g/fb/kf/E7cd55c4979d44131b600c55b7268a898J.png_720x720q75.png_.webp"
        )

        composeTestRule.setContent {
            Alimentos(alimento)
        }

        composeTestRule.onNodeWithText("Pan de molde").assertExists()
        composeTestRule.onNodeWithText("Schar").assertExists()
        composeTestRule.onNodeWithText("Sin Gluten").assertExists()
    }

    @Test
    fun boton_isDisabledWhenNotEnabled() {
        composeTestRule.setContent {
            Boton(
                texto = "Inicia sesión",
                loginEnable = false,
                onClickChange = {}
            )
        }

        composeTestRule.onNodeWithText("Inicia sesión")
            .assertExists()
            .assertIsNotEnabled()
    }

    @Test
    fun botones_categoriesTriggerActionsOnClick() {
        var sinGlutenClicked = false
        var sinLactosaClicked = false
        var veganoClicked = false

        composeTestRule.setContent {
            Row {
                Button(onClick = { sinGlutenClicked = true }) {
                    Text("Sin gluten")
                }
                Button(onClick = { sinLactosaClicked = true }) {
                    Text("Sin lactosa")
                }
                Button(onClick = { veganoClicked = true }) {
                    Text("Vegano")
                }
            }
        }

        composeTestRule.onNodeWithText("Sin gluten").performClick()
        assert(sinGlutenClicked)
        composeTestRule.onNodeWithText("Sin lactosa").performClick()
        assert(sinLactosaClicked)
        composeTestRule.onNodeWithText("Vegano").performClick()
        assert(veganoClicked)
    }

    @Test
    fun navegarPantallaNueva_whenButtonClicked() {
        var navigatedToNewScreen = false

        composeTestRule.setContent {
            Button(onClick = { navigatedToNewScreen = true }) {
                Text("Ir a la nueva pantalla")
            }
        }

        composeTestRule.onNodeWithText("Ir a la nueva pantalla")
            .assertExists()
            .performClick()

        assert(navigatedToNewScreen)
    }























}