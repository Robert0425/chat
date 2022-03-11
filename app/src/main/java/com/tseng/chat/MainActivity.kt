package com.tseng.chat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tseng.chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val MAIN_CODE = 1
        const val ROOM_CODE = 2
        const val SEARCH_CODE = 3
        const val PERSONAL_CODE = 4
        const val SIGNUP_CODE = 5
        const val LOGIN_CODE = 6
    }
    lateinit var binding: ActivityMainBinding
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = baseContext.getSharedPreferences("user", MODE_PRIVATE)
//        pref.edit().clear().commit()

        binding.ibGirl1.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("id", "123456")
            startActivityForResult(intent, ROOM_CODE)
        }

        // bottom bar
        setupBar()
    }

    fun setupBar() {
        binding.bHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, MAIN_CODE)
        }
        binding.bSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, SEARCH_CODE)
        }
        binding.bPersonal.setOnClickListener {
            if (pref.getString("account", "") !== "") {
                val intent = Intent(this, PersonalActivity::class.java)
                startActivityForResult(intent, PERSONAL_CODE)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, LOGIN_CODE)
            }
        }
    }
}