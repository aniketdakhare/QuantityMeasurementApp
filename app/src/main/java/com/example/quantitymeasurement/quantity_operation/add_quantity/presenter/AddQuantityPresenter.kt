package com.example.quantitymeasurement.quantity_operation.add_quantity.presenter

import android.widget.ArrayAdapter
import com.example.quantitymeasurement.quantity_operation.add_quantity.AddQuantityContract
import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

class AddQuantityPresenter(
    private val view: AddQuantityContract.View,
    private val model: AddQuantityContract.Model
) : AddQuantityContract.Presenter {
    override fun onQuantitySelected(quantity: String): Int {
        return view.performQuantitySelectedAction(quantity)
    }

    override fun onFirstQuantityValueChanged(
        firstQuantity: Quantity,
        secondQuantity: Quantity,
        toUnit: UnitType
    ) {
        val result = model.addQuantities(firstQuantity, secondQuantity, toUnit).value.toString()
        view.performFirstQuantityValueChangedAction(result)
    }

    override fun onFirstQuantityUnitSelected(
        firstQuantity: Quantity,
        secondQuantity: Quantity,
        toUnit: UnitType
    ) {
        val result = model.addQuantities(firstQuantity, secondQuantity, toUnit).value.toString()
        view.performFirstQuantityUnitSelectedAction(result)
    }

    override fun onSecondQuantityValueChanged(
        firstQuantity: Quantity,
        secondQuantity: Quantity,
        toUnit: UnitType
    ) {
        val result = model.addQuantities(firstQuantity, secondQuantity, toUnit).value.toString()
        view.performSecondQuantityValueChangedAction(result)
    }

    override fun onSecondQuantityUnitSelected(
        firstQuantity: Quantity,
        secondQuantity: Quantity,
        toUnit: UnitType
    ) {
        val result = model.addQuantities(firstQuantity, secondQuantity, toUnit).value.toString()
        view.performSecondQuantityUnitSelectedAction(result)
    }

    override fun onResultQuantityUnitSelected(
        firstQuantity: Quantity,
        secondQuantity: Quantity,
        toUnit: UnitType
    ) {
        val result = model.addQuantities(firstQuantity, secondQuantity, toUnit).value.toString()
        view.performResultQuantityUnitSelectedAction(result)
    }
}