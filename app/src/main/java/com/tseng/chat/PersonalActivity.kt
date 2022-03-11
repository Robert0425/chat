package com.tseng.chat
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.tseng.chat.databinding.ActivityPersonalBinding

class PersonalActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalBinding
    lateinit var pref: SharedPreferences
    val TAG = this::class.java.simpleName

    // wht not work?
//    val pref = baseContext.getSharedPreferences("user", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = baseContext.getSharedPreferences("user", Context.MODE_PRIVATE)

        binding.tNick.setText(pref.getString("nickname", ""))
        binding.tAccount.setText(pref.getString("account", ""))

        binding.bLogout.setOnClickListener {
            pref.edit().clear().commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, MainActivity.PERSONAL_CODE)
        }
    }
}