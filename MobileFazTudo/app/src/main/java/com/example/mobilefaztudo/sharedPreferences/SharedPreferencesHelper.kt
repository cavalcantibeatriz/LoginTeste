package com.example.mobilefaztudo.sharedPreferences
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
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
        sharedPreferences.edit().putInt("category_id", user.category.id).apply()
        sharedPreferences.edit().putString("category_id", user.category.name).apply()
        
    }
}

