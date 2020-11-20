package com.example.quantitymeasurement.quantity_operation.convert_quantity.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.quantity_operation.convert_quantity.ConvertQuantityContract
import com.example.quantitymeasurement.quantity_operation.convert_quantity.presenter.ConvertQuantityPresenter
import com.example.quantitymeasurement.quantity_operation.model.service.QuantityMeasurement
import kotlinx.android.synthetic.main.fragment_convert_quantity.*

class ConvertQuantityFragment : Fragment(R.layout.fragment_convert_quantity), ConvertQuantityContract.View {
    private lateinit var quantityTypeSpinner: Spinner
    private lateinit var fromUnit: Spinner
    private lateinit var toUnit: Spinner
    private lateinit var presenter: ConvertQuantityPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quantityTypeSpinner = view.findViewById(R.id.quantityType)
        fromUnit = view.findViewById(R.id.fromUnit)
        toUnit = view.findViewById(R.id.toUnit)
        initView()
        onQuantitySelected()
    }

    private fun initView() {
        presenter = ConvertQuantityPresenter(this, QuantityMeasurement())
    }

    private fun onQuantitySelected() {
        val quantityArray = resources.getStringArray(R.array.quantityType)
        quantityTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val units = presenter.onQuantitySelected(quantityArray[position])
                val adapter = ArrayAdapter.createFromResource(requireContext(), units,
                    android.R.layout.simple_spinner_dropdown_item)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                fromUnit.adapter = adapter
                toUnit.adapter = adapter
                performConversion()
            }
            override fun onNothingSelected (parent: AdapterView<*>?) {}
        }
    }

    fun performConversion() {
        val fromQuantityValue = fromValue.editableText.toString()
        val fromQuantityUnit = fromUnit.selectedItem.toString()
        val toQuantityUnit = toUnit.selectedItem.toString()

        val value = if (fromQuantityValue.trim().isNotEmpty()) fromQuantityValue.toDouble() else 0.0

        fromValue.doAfterTextChanged { presenter.onInputQuantityValueChanged(value, fromQuantityUnit, toQuantityUnit) }
        fromUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { presenter.onInputQuantityUnitSelected(value, fromQuantityUnit, toQuantityUnit)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        toUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { presenter.onResultQuantityUnitSelected(value, fromQuantityUnit, toQuantityUnit)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
    }

    override fun performQuantitySelectedAction(quantity: String): Int {
        return when (quantity) {
            "Length" -> R.array.length
            "Weight" -> R.array.weight
            "Volume" -> R.array.volume
            "Temperature" -> R.array.temp
            else -> 0
        }
    }

    override fun performInputQuantityValueChangedAction(result: String) {
        toValue.setText(result)
    }

    override fun performInputQuantityUnitSelectedAction(result: String) {
        toValue.setText(result)
    }

    override fun performResultQuantityUnitSelectedAction(result: String) {
        toValue.setText(result)
    }
}