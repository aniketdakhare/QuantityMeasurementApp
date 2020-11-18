package com.example.quantitymeasurement

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quantitymeasurement.view.AddQuantityFragment
import com.example.quantitymeasurement.view.ConvertQuantityFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val convertQuantity = ConvertQuantityFragment()
    private val addQuantity = AddQuantityFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        convertButton.setBackgroundColor(Color.GREEN)
        addButton.setBackgroundColor(Color.BLUE)
    }

    fun convertQuantity(view: View) {
        convertButton.setBackgroundColor(Color.RED)
        addButton.setBackgroundColor(Color.BLUE)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder, convertQuantity)
            commit()
        }
    }

    fun addQuantity(view: View) {
        addButton.setBackgroundColor(Color.RED)
        convertButton.setBackgroundColor(Color.GREEN)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder, addQuantity)
            commit()
        }
    }
}