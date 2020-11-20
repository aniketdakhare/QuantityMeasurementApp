package com.example.quantitymeasurement.quantity_operation.model

import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType

data class Quantity(val unit: UnitType, val value: Double) {}