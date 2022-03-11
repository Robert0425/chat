package com.tseng.chat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.tseng.chat.databinding.ActivityLoginBinding
import com.tseng.chat.databinding.ActivityPersonalBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var pref: SharedPreferences
    var isRemember = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = baseContext.getSharedPreferences("user", Context.MODE_PRIVATE)
        isRemember = pref.getBoolean("isRemember", false)
        val prefAccount = pref.getString("account", "")
        val prefPassword = pref.getString("password", "")

        binding.cbRemember.isChecked = isRemember

        if (isRemember) {
            binding.edAccount.setText(prefAccount)
            binding.edPassword.setText(prefPassword)
        }

        // check button
        binding.cbRemember.setOnCheckedChangeListener { button, checked ->
            isRemember = checked
        }

        // sign button
        binding.bSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, MainActivity.SIGNUP_CODE)
        }

        // login button
        binding.bLogin.setOnClickListener {
            val account = binding.edAccount.text.toString()
            val password = binding.edPassword.text.toString()
            if (account == pref.getString("account", "") && password == pref.getString("password", "")) {
                // 跳轉至首頁
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, MainActivity.PERSONAL_CODE)
            } else {
                // 登入失敗
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("Login fail!")
                    .setPositiveButton("OK") { dialog, which ->
                        binding.edAccount.setText("")
                        binding.edPassword.setText("")
                    }
                    .show()
            }
        }
    }
}