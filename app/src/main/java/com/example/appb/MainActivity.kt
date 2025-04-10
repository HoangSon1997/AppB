package com.example.appb

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("content://com.example.appa.provider/user_table")
        val providers = packageManager.queryContentProviders(null, 0, 0)
        val found = providers.any { it.authority == "com.example.appa.provider" }

        Log.d("Check sondeptrai", "Provider found: $found")

        for (info in providers) {
            Log.d("Provider", "authority=${info.authority}, package=${info.packageName}")
        }

        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val name = it.getString(it.getColumnIndexOrThrow("name"))
                Log.d("sondeptrai", "ID: $id, Name: $name")
            }
        }
    }
}