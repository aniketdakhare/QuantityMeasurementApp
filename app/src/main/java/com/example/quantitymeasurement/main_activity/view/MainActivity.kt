package com.example.quantitymeasurement.main_activity.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.main_activity.QuantityMeasurementContract
import com.example.quantitymeasurement.main_activity.presenter.QuantityPresenter
import com.example.quantitymeasurement.quantity_operation.add_quantity.view.AddQuantityFragment
import com.example.quantitymeasurement.quantity_operation.convert_quantity.view.ConvertQuantityFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), QuantityMeasurementContract.View {
    private lateinit var presenter: QuantityMeasurementContract.Presenter
    private val convertQuantity = ConvertQuantityFragment()
    private val addQuantity = AddQuantityFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        convertButton.setBackgroundColor(Color.BLUE)
        presenter.onConvertQuantityBtnClicked()
        addButton.setBackgroundColor(Color.BLUE)
    }

    private fun initView() {
        presenter = QuantityPresenter(this)
    }

    fun convertQuantity(view: View) {
        presenter.onConvertQuantityBtnClicked()
    }

    fun addQuantity(view: View) {
        presenter.onAddQuantityBtnClicked()
    }

    override fun performConvertQuantityBtnAction() {
        convertButton.setBackgroundColor(Color.GREEN)
        addButton.setBackgroundColor(Color.BLUE)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder, convertQuantity)
            commit()
        }
    }

    override fun performAddQuantityBtnAction() {
        addButton.setBackgroundColor(Color.GREEN)
        convertButton.setBackgroundColor(Color.BLUE)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder, addQuantity)
            commit()
        }
    }
}