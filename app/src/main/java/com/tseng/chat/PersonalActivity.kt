package com.tseng.chat
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.tseng.chat.databinding.ActivityPersonalBinding

class PersonalActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalBinding
    val TAG = this::class.java.simpleName
    var isRemember = false

    // wht not work?
//    val pref = baseContext.getSharedPreferences("user", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        val pref = baseContext.getSharedPreferences("user", Context.MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        // login button
        binding.bLogin.setOnClickListener {
            val account = binding.edAccount.text.toString()
            val password = binding.edPassword.text.toString()
            if (account == "robert" && password == "123") {
                // 登入成功 暫存
                pref.edit().putBoolean("isRemember", isRemember).apply()
                pref.edit().putString("account", account).apply()
                pref.edit().putString("password", password).apply()
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

        // sign up button
        binding.bSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, MainActivity.SIGNUP_CODE)
        }
    }
}