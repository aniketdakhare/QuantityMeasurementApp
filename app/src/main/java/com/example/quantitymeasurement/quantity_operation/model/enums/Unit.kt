    package com.example.quantitymeasurement.quantity_operation.model.enums

enum class UnitType(val unitValue: Double, val conversionFactor: Double, val quantityType: QuantityType) {
    INCH(1.0, 1.0, QuantityType.LENGTH),
    FEET(12.0, 0.0833, QuantityType.LENGTH),
    YARD(36.0, 0.0277778, QuantityType.LENGTH),
    CENTIMETER(0.4, 2.54, QuantityType.LENGTH),
    GALLON(3.78, 0.264172, QuantityType.VOLUME),
    LITRE(1.0, 1.0, QuantityType.VOLUME),
    MILLILITRE(0.001, 1000.0, QuantityType.VOLUME),
    KILOGRAM(1.0, 1.0, QuantityType.WEIGHT),
    GRAM(0.001, 1000.0, QuantityType.WEIGHT),
    TONNE(1000.0, 0.001, QuantityType.WEIGHT),
    CELSIUS(32.0, 1.8, QuantityType.TEMPERATURE),
    FAHRENHEIT(32.0, 1.8, QuantityType.TEMPERATURE);

    enum class QuantityType {
        LENGTH, VOLUME, WEIGHT, TEMPERATURE
    }
}