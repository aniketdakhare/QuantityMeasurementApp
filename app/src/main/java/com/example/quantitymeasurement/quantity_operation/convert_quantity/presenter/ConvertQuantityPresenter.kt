package com.example.quantitymeasurement.quantity_operation.convert_quantity.presenter

import android.widget.ArrayAdapter
import com.example.quantitymeasurement.quantity_operation.convert_quantity.ConvertQuantityContract
import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

class ConvertQuantityPresenter(
    private val view: ConvertQuantityContract.View,
    private val model: ConvertQuantityContract.Model
) : ConvertQuantityContract.Presenter {
    override fun onQuantitySelected(quantity: String): Int {
        return view.performQuantitySelectedAction(quantity)
    }

    override fun onInputQuantityValueChanged(fromValue: Double, fromUnit: String, toUnit: String) {
        val result = convertQuantity(fromValue, fromUnit, toUnit)
        view.performInputQuantityValueChangedAction(result)
    }

    override fun onInputQuantityUnitSelected(fromValue: Double, fromUnit: String, toUnit: String) {
        val result = convertQuantity(fromValue, fromUnit, toUnit)
        view.performInputQuantityUnitSelectedAction(result)
    }

    override fun onResultQuantityUnitSelected(fromValue: Double, fromUnit: String, toUnit: String) {
        val result = convertQuantity(fromValue, fromUnit, toUnit)
        view.performResultQuantityUnitSelectedAction(result)
    }

    private fun convertQuantity(fromValue: Double, fromUnit: String, toUnit: String): String {
        return model.convertValue(
            Quantity(UnitType.valueOf(fromUnit), fromValue),
            UnitType.valueOf(toUnit)
        ).value.toString()
    }
}