package com.example.quantitymeasurement.model.service

import com.example.quantitymeasurement.model.enums.UnitType

class Quantity(private val unit: UnitType, private val value: Double) {
    fun convertValue(conversionUnit: UnitType): Double {
        require(value >= 0) { "Negative values are not Excepted" }
        if (unit == conversionUnit)
            return value
        if (conversionUnit.quantityType == UnitType.QuantityType.TEMPERATURE)
            return convertTemperature(conversionUnit)
        return (value * unit.unitValue) * conversionUnit.conversionFactor
    }

    private fun convertTemperature(conversionUnit: UnitType): Double {
        return when (conversionUnit)
        {
            UnitType.CELSIUS -> (value - conversionUnit.unitValue) / conversionUnit.conversionFactor
            UnitType.FAHRENHEIT -> value * conversionUnit.conversionFactor + conversionUnit.unitValue
            else -> 0.0
        }
    }

    fun addQuantities(quantity: Quantity, resultUnit: UnitType): Double {
        return ((this.value * this.unit.unitValue) +
                    (quantity.value * quantity.unit.unitValue)) * resultUnit.conversionFactor
    }
}