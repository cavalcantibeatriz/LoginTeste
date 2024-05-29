package com.example.mobilefaztudo.sharedPreferences
import android.content.Context
import android.content.SharedPreferences
import com.example.mobilefaztudo.api.User
import com.google.gson.Gson

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
    }

    fun getIdUser() : Int {
        return sharedPreferences.getInt("id", 0)
    }

    fun getAuthToken() : String {
        return sharedPreferences.getString("authToken","").toString()
    }

    fun sessionStorage(){
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
}

