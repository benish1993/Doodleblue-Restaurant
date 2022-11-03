package com.doodleblue.doodlebluerestaurant

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : Activity() {

    lateinit var rlViewCart : RelativeLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rlViewCart = findViewById(R.id.rlViewCart) as RelativeLayout

        rlViewCart.setOnClickListener{
            val intent = Intent(this,MyCart::class.java)
            startActivity(intent)
        }
    }
}