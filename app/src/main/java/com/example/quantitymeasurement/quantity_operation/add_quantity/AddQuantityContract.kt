package com.example.quantitymeasurement.quantity_operation.add_quantity

import android.widget.ArrayAdapter
import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

interface AddQuantityContract {
    interface View {
        fun performQuantitySelectedAction(quantity: String): Int
        fun performFirstQuantityValueChangedAction(result: String)
        fun performFirstQuantityUnitSelectedAction(result: String)
        fun performSecondQuantityValueChangedAction(result: String)
        fun performSecondQuantityUnitSelectedAction(result: String)
        fun performResultQuantityUnitSelectedAction(result: String)
    }

    interface Presenter {
        fun onQuantitySelected(quantity: String): Int
        fun onFirstQuantityValueChanged(firstQuantity: Quantity, secondQuantity: Quantity, toUnit: UnitType)
        fun onFirstQuantityUnitSelected(firstQuantity: Quantity, secondQuantity: Quantity, toUnit: UnitType)
        fun onSecondQuantityValueChanged(firstQuantity: Quantity, secondQuantity: Quantity, toUnit: UnitType)
        fun onSecondQuantityUnitSelected(firstQuantity: Quantity, secondQuantity: Quantity, toUnit: UnitType)
        fun onResultQuantityUnitSelected(firstQuantity: Quantity, secondQuantity: Quantity, toUnit: UnitType)
    }

    interface Model {
        fun addQuantities(
            firstQuantity: Quantity, secondQuantity: Quantity, resultUnit: UnitType): Quantity
    }
}