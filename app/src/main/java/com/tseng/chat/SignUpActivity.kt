package com.tseng.chat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.tseng.chat.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = baseContext.getSharedPreferences("user", MODE_PRIVATE)

        binding.bSubmit.setOnClickListener {
            val nickname = binding.edNick.text.toString()
            val account = binding.edReaccount.text.toString()
            val password = binding.edRepassword.text.toString()

            if (nickname !== "" && account !== "" && password !== "") {
                pref.edit().putString("nickname", nickname).apply()
                pref.edit().putString("account", account).apply()
                pref.edit().putString("password", password).apply()
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("註冊成功")
                    .setPositiveButton("OK") { _, _ ->
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivityForResult(intent, MainActivity.SIGNUP_CODE)
                    }
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("註冊失敗")
                    .show()
            }
        }
    }
}