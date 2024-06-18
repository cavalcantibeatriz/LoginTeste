package com.example.mobilefaztudo.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.mobilefaztudo.api.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MultipartBody
import java.io.File

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString("authToken", token).apply()
    }

    fun saveUserData(user: User) {
        val userJson = Gson().toJson(user)
        val categoryJson = Gson().toJson(user.category)
        sharedPreferences.edit().putString("JSON", userJson).apply()
        sharedPreferences.edit().putInt("id", user.id).apply()
        sharedPreferences.edit().putString("nome", user.name).apply()
        sharedPreferences.edit().putString("sobrenome", user.lastName).apply()
        sharedPreferences.edit().putString("dt_cadastro", user.dt_cadastro).apply()
        sharedPreferences.edit().putString("image_profile", user.image_profile).apply()
        sharedPreferences.edit().putString("phone", user.phone).apply()
        sharedPreferences.edit().putBoolean("proUser", user.proUser).apply()
        sharedPreferences.edit().putString("email", user.email).apply()
        sharedPreferences.edit().putString("username", user.username).apply()
        sharedPreferences.edit().putString("descricao", user.descricao).apply()
        sharedPreferences.edit().putString("cpf", user.cpf).apply()
        sharedPreferences.edit().putString("cep", user.cep).apply()
        sharedPreferences.edit().putString("logradouro", user.logradouro).apply()
        sharedPreferences.edit().putString("state", user.state).apply()
        sharedPreferences.edit().putString("city", user.city).apply()
        sharedPreferences.edit().putString("senha", user.senha).apply()
        sharedPreferences.edit().putString("role", user.role).apply()
        sharedPreferences.edit().putString("categoryJson", categoryJson).apply()
        sharedPreferences.edit().putString("categoryName", user.category?.name).apply()
        user.category?.let { sharedPreferences.edit().putInt("categoryId", it.id).apply() }

    }
    fun getDescricao(): String {
        return sharedPreferences.getString("descricao", "")?: ""
    }
    fun saveDescricao(des:String) {
        return sharedPreferences.edit().putString("descricao", des).apply()
    }
    fun getIdUser(): Int {
        return sharedPreferences.getInt("id", 0)
    }
    fun getNome(): String? {
        return sharedPreferences.getString("nome", "")
    }
    fun getDataCadastro():String?{
        return sharedPreferences.getString("dt_cadastro", "")
    }
    fun getImgProfile(): String? {
        return sharedPreferences.getString("image_profile", "")
    }
    fun getCep(): String {
        return sharedPreferences.getString("cep", "") ?: ""
    }
    fun saveCep(cep: String) {
        sharedPreferences.edit().putString("cep", cep).apply()
    }

    fun getLogradouro(): String{
        return sharedPreferences.getString("logradouro", "") ?: ""
    }
    fun saveLogradouro(logradouro: String) {
        sharedPreferences.edit().putString("logradouro", logradouro).apply()
    }
    fun getState(): String{
        return sharedPreferences.getString("state", "") ?: ""
    }
    fun saveState(state: String) {
        sharedPreferences.edit().putString("state", state).apply()
    }
    fun getCity(): String{
        return sharedPreferences.getString("city", "") ?: ""
    }
    fun saveCity(city: String) {
        sharedPreferences.edit().putString("city", city).apply()
    }
    fun getPhone(): String{
        return sharedPreferences.getString("phone", "") ?: ""
    }
    fun savePhone(phone: String) {
        sharedPreferences.edit().putString("phone", phone).apply()
    }

    fun getCategoriaName(): String{
        return sharedPreferences.getString("categoryName", "") ?: ""
    }
    fun saveCategoriaName(catName: String) {
        sharedPreferences.edit().putString("categoryName", catName).apply()
    }
    fun getCategoriaId(): Int{
        return sharedPreferences.getInt("categoryId", 0) ?: 0
    }
    fun saveCategoriaId(catId: Int) {
        sharedPreferences.edit().putInt("categoryId", catId).apply()
    }
    fun getSobrenome(): String? {
        return sharedPreferences.getString("sobrenome", "")
    }
    fun getEmail(): String? {
        return sharedPreferences.getString("email", "")
    }
    fun getTelefone(): String? {
        return sharedPreferences.getString("phone", "")
    }

    fun saveNovaImgPerfil(base64Image: String) {
        sharedPreferences.edit().putString("image_profile", base64Image).apply()
    }

    fun saveUser(user: User) {
        val userJson = Gson().toJson(user)
        sharedPreferences.edit().putString("JSON", userJson).apply()
    }

    fun getJsonUser(): User? {
        val userJson =  sharedPreferences.getString("JSON", null)
        return if (userJson != null) {
            val userType = object : TypeToken<User>() {}.type
            Gson().fromJson(userJson, userType)

        } else {
            null
        }
    }


    fun getCategory(): String? {
        return sharedPreferences.getString("categoryJson", null)
    }

    fun getAuthToken(): String {
        return sharedPreferences.getString("authToken", "").toString()
    }

    fun sessionStorage() {
        var nome = sharedPreferences.getString("nome", "")
        var sobrenome = sharedPreferences.getString("sobrenome", "")
        var dt_cadastro = sharedPreferences.getString("dt_cadastro", "")
        var image_profile = sharedPreferences.getString("image_profile", "")
        var phone = sharedPreferences.getString("phone", "")
        var proUser = sharedPreferences.getBoolean("proUser", false)
        var email = sharedPreferences.getString("email", "")
        var username = sharedPreferences.getString("username", "")
        var descricao = sharedPreferences.getString("descricao", "")
        var cpf = sharedPreferences.getString("cpf", "")
        var cep = sharedPreferences.getString("cep", "")
        var logradouro = sharedPreferences.getString("logradouro", "")
        var state = sharedPreferences.getString("state", "")
        var city = sharedPreferences.getString("city", "")
        var senha = sharedPreferences.getString("senha", "")
        var role = sharedPreferences.getString("role", "")
        var JSON = sharedPreferences.getString("JSON", "")
        var categoryJson = sharedPreferences.getString("categoryJson", null)
        var categoryName = sharedPreferences.getString("categoryName", null)
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}

