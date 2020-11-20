package com.example.quantitymeasurement.quantity_operation.model.service

import com.example.quantitymeasurement.quantity_operation.add_quantity.AddQuantityContract
import com.example.quantitymeasurement.quantity_operation.convert_quantity.ConvertQuantityContract
import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

class QuantityMeasurement() : ConvertQuantityContract.Model, AddQuantityContract.Model {
    override fun convertValue(quantity: Quantity, conversionUnit: UnitType): Quantity {
        if (quantity.unit == conversionUnit)
            return Quantity(conversionUnit, quantity.value)
        if (conversionUnit.quantityType == UnitType.QuantityType.TEMPERATURE)
            return Quantity(conversionUnit, convertTemperature(quantity, conversionUnit))
        return Quantity(
            conversionUnit,
            (quantity.value * quantity.unit.unitValue) * conversionUnit.conversionFactor
        )
    }

    private fun convertTemperature(quantity: Quantity, conversionUnit: UnitType): Double {
        return when (conversionUnit) {
            UnitType.CELSIUS -> (quantity.value - conversionUnit.unitValue) / conversionUnit.conversionFactor
            UnitType.FAHRENHEIT -> quantity.value * conversionUnit.conversionFactor + conversionUnit.unitValue
            else -> 0.0
        }
    }

    override fun addQuantities(firstQuantity: Quantity, secondQuantity: Quantity, resultUnit: UnitType
    ): Quantity {
        val result = ((firstQuantity.value * firstQuantity.unit.unitValue) +
                (secondQuantity.value * secondQuantity.unit.unitValue)) * resultUnit.conversionFactor
        return Quantity(resultUnit, result)
    }
}