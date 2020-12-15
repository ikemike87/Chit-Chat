package com.example.project3chitchat.api

import retrofit2.Call
import retrofit2.http.GET

interface ChitChatApi {
    @GET("chitchat?" +
                "&client=michael.bamford@mymail.champlain.edu" +
                "&key=03af63c4-90eb-4af5-9494-f02dd2747f0e")
    fun fetchContents(): Call<ChitChatResponse>
}