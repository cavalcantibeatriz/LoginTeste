package com.example.mobilefaztudo

import retrofit2.Response
import retrofit2.http.Body

interface ILoginRepository {

    suspend fun login(@Body body: LoginRequestBody): Response<LoginResponse>
}