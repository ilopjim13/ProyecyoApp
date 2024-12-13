package com.example.proyectoapp.usuarioViewModel

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectoapp.model.Usuario
import com.google.gson.Gson
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream

class UsuarioViewModel(application: Application): AndroidViewModel(application) {


    private val usuarios: SnapshotStateList<Usuario> = mutableStateListOf()

    private val _correo = MutableLiveData<String>()
    var correo: LiveData<String> = _correo

    private val _pass = MutableLiveData<String>()
    var pass: LiveData<String> = _pass

    private val _errorTexto = MutableLiveData<String>()
    var errorTexto: LiveData<String> = _errorTexto

    private val _loginEnable = MutableLiveData<Boolean>()
    var loginEnable: LiveData<Boolean> = _loginEnable

    private val _check = MutableLiveData<Boolean>()
    var check: LiveData<Boolean> = _check

    private val _visibility = MutableLiveData<Boolean>()
    var visibility: LiveData<Boolean> = _visibility

    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    private val _error = MutableLiveData<Boolean>()
    var error: LiveData<Boolean> = _error


    init {
        getUsuarios()
    }

    fun onChangeValue(correo:String, pass:String) {
        _correo.value = correo
        _pass.value = pass
        _loginEnable.value = loginEnableCheck(correo, pass)
        _error.value = false
    }

    fun resetVariables() {
        _correo.value = ""
        _pass.value = ""
        _loginEnable.value = false
    }

    fun showCheck(check:Boolean) {
        _check.value = check
    }

    fun resetError() {
        _error.value = false
        _errorTexto.value = ""
        _check.value = false
    }
    fun showDialog(dialog:Boolean) {
        _showDialog.value = dialog
    }

    fun showVisibility(visibility:Boolean) {
        _visibility.value =  visibility
    }

    private fun loginEnableCheck(correo: String, pass: String) = Patterns.EMAIL_ADDRESS.matcher(correo).matches() && pass.length > 5

    fun checkLogin(correo: String, pass: String,rol:String = "USER"):Boolean {
        val usuario = Usuario(correo, pass, rol)
        if (usuario in usuarios){
            _error.value = false
            return true
        } else {
            _error.value = true
            _errorTexto.value = "El usuario o la contraseña son incorrectas"
            return false
        }
    }

    fun checkRegistro(correo: String):Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            _errorTexto.value = "El correo es inválido"
            _error.value = true
            return false
        } else if (usuarios.find { it.correo == correo } != null) {
            _errorTexto.value = "Este correo ya existe"
            _error.value = true
            return false
        }
        else return true
    }

    fun checkCorreo(correo: String, check: Boolean = true):Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            _errorTexto.value = "El correo es inválido"
            _error.value = true
            return false
        }else if(usuarios.find { it.correo == correo } == null) {
            _errorTexto.value = "No existe este correo en Tumblr"
            _error.value = true
            return false
        } else if(!check) {
            _errorTexto.value = "Debes aceptar el reCAPTCHA"
            _error.value = true
            return false
        } else{
            _errorTexto.value = ""
            _error.value = false
            return true
        }
    }

    fun saveUsuario(correo: String, pass: String, rol:String = "USER") {
        val usuario = Usuario(correo, pass, rol)
        usuarios.add(usuario)

        val jsonString = Gson().toJson(usuarios)
        val jsonFile = File(getApplication<Application>().filesDir, "usuarios.json")
        FileOutputStream(jsonFile).use {
                outputStream -> outputStream.write(jsonString.toByteArray())
        }
    }

    private fun getUsuarios() {
        val archivo = File(getApplication<Application>().filesDir, "usuarios.json")
        if (archivo.exists()) {
            val contenido = archivo.readText()
            val listaJSON = JSONArray(contenido)

            for (i in (0..<listaJSON.length())) {
                val usuarioJson = listaJSON.getJSONObject(i)
                val usuario = Usuario (
                    correo = usuarioJson.getString("correo"),
                    pass = usuarioJson.getString("pass"),
                    rol = usuarioJson.getString("rol")
                )
                usuarios.add(usuario)
            }
        }
        else saveUsuario("ivan@gmail.com", "123456")
    }


}