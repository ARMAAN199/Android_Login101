package com.example.chatapplive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.example.chatapplive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

        // abcd
        binding.toolbar.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_chat -> {
                    Log.d("MainActivity", "Chat menu clicked")
                    true
                }

                R.id.menu_friends -> {
                    Log.d("MainActivity", "Friends menu clicked")
                    true
                }

                else -> false
            }
        }

// abcd
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.mainContent.id, LoginFragment())
                .commit()
        }


    }
}