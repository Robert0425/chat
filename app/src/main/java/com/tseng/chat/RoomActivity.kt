package com.tseng.chat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.tseng.chat.databinding.ActivityRoomBinding
import okhttp3.*
import java.util.concurrent.TimeUnit
import java.net.URL

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    lateinit var pref: SharedPreferences
    val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = baseContext.getSharedPreferences("user", MODE_PRIVATE)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bLeave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, MainActivity.ROOM_CODE)
        }

        val client = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url("wss://lott-dev.lottcube.asia/ws/chat/chat:app_test?nickname=${pref.getString("nickname", "")}")
            .build()
        val websocket = client?.newWebSocket(request, object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.d(TAG, "onClosed")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.d(TAG, "onClosing")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.d(TAG, "onFailure")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.d(TAG, "onMessage: $text")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d(TAG, "onOpen")
            }
        })

        binding.bSend.setOnClickListener {
            val message = binding.edMessage.text.toString()
            val json = Gson().toJson(Message("N", message))
            websocket.send(json)
            binding.edMessage.setText("")
        }

        binding.bLeave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, MainActivity.ROOM_CODE)
        }
    }
}

data class Message(val action: String, val content: String)