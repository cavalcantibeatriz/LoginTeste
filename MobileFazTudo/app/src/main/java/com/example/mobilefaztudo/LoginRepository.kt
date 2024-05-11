package com.example.mobilefaztudo

import retrofit2.Response
import retrofit2.http.Body

class LoginRepository : ILoginRepository {

    override suspend fun login(body: LoginRequestBody): Response<LoginResponse> {
        val api = RetrofitClient
            .getInstance()
            .create(ApiService::class.java)

        return api.login(body)
    }
}