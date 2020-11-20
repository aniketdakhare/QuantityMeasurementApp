package com.example.quantitymeasurement.quantity_operation.convert_quantity

import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

interface ConvertQuantityContract {
    interface View {
        fun performQuantitySelectedAction(quantity: String): Int
        fun performInputQuantityValueChangedAction(result: String)
        fun performInputQuantityUnitSelectedAction(result: String)
        fun performResultQuantityUnitSelectedAction(result: String)
    }

    interface Presenter {
        fun onQuantitySelected(quantity: String): Int
        fun onInputQuantityValueChanged(fromValue: Double, fromUnit: String, toUnit:String)
        fun onInputQuantityUnitSelected(fromValue: Double, fromUnit: String, toUnit:String)
        fun onResultQuantityUnitSelected(fromValue: Double, fromUnit: String, toUnit:String)
    }

    interface Model {
        fun convertValue(quantity: Quantity, conversionUnit: UnitType): Quantity
    }
}