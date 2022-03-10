package com.tseng.chat

import android.content.Intent
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
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val intent = Intent(this, PersonalActivity::class.java)
            startActivityForResult(intent, PERSONAL_CODE)
        }
    }
}