package com.example.quantitymeasurement.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.presenter.QuantityPresenter
import kotlinx.android.synthetic.main.fragment_convert_quantity.*

class ConvertQuantityFragment : Fragment(R.layout.fragment_convert_quantity) {
    private lateinit var quantityTypeSpinner: Spinner
    private lateinit var fromUnit: Spinner
    private lateinit var toUnit: Spinner
    private val quantityPresenter = QuantityPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quantityTypeSpinner = view.findViewById(R.id.quantityType)
        fromUnit = view.findViewById(R.id.fromUnit)
        toUnit = view.findViewById(R.id.toUnit)
        onQuantitySelected()
    }

    private fun onQuantitySelected() {
        val quantityArray = resources.getStringArray(R.array.quantityType)
        quantityTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val adapter = when (quantityArray[position]) {
                    "Length" -> ArrayAdapter.createFromResource(requireContext(), R.array.length,
                        android.R.layout.simple_spinner_dropdown_item)
                    "Weight" -> ArrayAdapter.createFromResource(requireContext(), R.array.weight,
                        android.R.layout.simple_spinner_dropdown_item)
                    "Volume" -> ArrayAdapter.createFromResource(requireContext(), R.array.volume,
                        android.R.layout.simple_spinner_dropdown_item)
                    "Temperature" -> ArrayAdapter.createFromResource(requireContext(), R.array.temp,
                        android.R.layout.simple_spinner_dropdown_item)
                    else -> null
                }
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                fromUnit.adapter = adapter
                toUnit.adapter = adapter
                performConversion()
            }
            override fun onNothingSelected (parent: AdapterView<*>?) {}
        }
    }

    fun performConversion() {
        fromValue.doAfterTextChanged { convertQuantity() }
        fromUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { convertQuantity() }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        toUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { convertQuantity() }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
    }

    fun convertQuantity() {
        val fromValue = fromValue.editableText.toString()
        val fromUnit = fromUnit.selectedItem.toString()
        val toUnit = toUnit.selectedItem.toString()

        val value = if (fromValue.trim().isNotEmpty()) fromValue.toDouble() else 0.0

        toValue.setText(quantityPresenter.convertQuantity(value, fromUnit, toUnit).toString())
    }
}