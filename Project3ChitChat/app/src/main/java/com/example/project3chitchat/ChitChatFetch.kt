package com.example.project3chitchat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project3chitchat.api.ChitChatApi
import com.example.project3chitchat.api.ChitChatResponse
import com.example.project3chitchat.api.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ChitChat"

class ChitChatFetch {
    private val chitChatApi: ChitChatApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://www.stepoutnyc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        chitChatApi = retrofit.create(ChitChatApi::class.java)
    }

    fun fetchContents(): LiveData<List<ChitChatItem>> {
        val responseLiveData: MutableLiveData<List<ChitChatItem>> = MutableLiveData()
        val chitChatRequest: Call<ChitChatResponse> = chitChatApi.fetchContents()

        chitChatRequest.enqueue(object : Callback<ChitChatResponse> {
            override fun onFailure(call: Call<ChitChatResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch contents", t)
            }

            override fun onResponse(call: Call<ChitChatResponse>, response: Response<ChitChatResponse>) {
                Log.d(TAG, "Response received")
                val chitChatResponse: ChitChatResponse? = response.body()
                val messageResponse: MessageResponse? = chitChatResponse?.messages
                val chitChatItems: List<ChitChatItem> = messageResponse?.chitChatItems
                        ?: mutableListOf()
                responseLiveData.value = chitChatItems
            }
        })

        return responseLiveData
    }
}