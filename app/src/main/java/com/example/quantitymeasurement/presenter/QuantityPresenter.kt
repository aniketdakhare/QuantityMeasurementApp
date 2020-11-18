package com.example.quantitymeasurement.presenter

import com.example.quantitymeasurement.model.enums.UnitType
import com.example.quantitymeasurement.model.service.Quantity

class QuantityPresenter
{
    fun addQuantities(units: Array<String>, value: Array<Double>): Double {
        return Quantity(UnitType.valueOf(units[0]), value[0]).addQuantities(Quantity(UnitType
            .valueOf(units[1]), value[1]), UnitType.valueOf(units[2]))
    }

    fun convertQuantity(fromValue: Double, fromUnit: String, toUnit: String): Double {
        return Quantity(UnitType.valueOf(fromUnit), fromValue)
            .convertValue(UnitType.valueOf(toUnit))
    }
}