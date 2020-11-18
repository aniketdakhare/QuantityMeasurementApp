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
import kotlinx.android.synthetic.main.fragment_add_quantity.*

class AddQuantityFragment : Fragment(R.layout.fragment_add_quantity) {
    private lateinit var quantityTypeSpinner: Spinner
    private lateinit var quantity1Unit: Spinner
    private lateinit var quantity2Unit: Spinner
    private lateinit var resultUnit: Spinner
    private val quantityPresenter = QuantityPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quantityTypeSpinner = view.findViewById(R.id.quantity)
        quantity1Unit = view.findViewById(R.id.firstUnit)
        quantity2Unit = view.findViewById(R.id.secondUnit)
        resultUnit = view.findViewById(R.id.resultUnit)
        onQuantitySelected()
    }

    private fun onQuantitySelected() {
        val quantityArray = resources.getStringArray(R.array.addableQuantity)
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
                    else -> null
                }
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                quantity1Unit.adapter = adapter
                quantity2Unit.adapter = adapter
                resultUnit.adapter = adapter
                performAddition()
            }
            override fun onNothingSelected (parent: AdapterView<*>?) {}
        }
    }

    fun performAddition()
    {
        firstValue.doAfterTextChanged { addQuantities() }
        secondValue.doAfterTextChanged { addQuantities() }
        firstUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { addQuantities() }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        secondUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { addQuantities() }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        resultUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long) { addQuantities() }
            override fun onNothingSelected(parent: AdapterView<*>?) {} }
    }

    private fun addQuantities() {
        val firstValue = firstValue.editableText.toString()
        val firstUnit = firstUnit.selectedItem.toString()
        val secondValue = secondValue.editableText.toString()
        val secondUnit = secondUnit.selectedItem.toString()
        val resultUnit = resultUnit.selectedItem.toString()

        val value1 = if (firstValue.trim().isNotEmpty()) firstValue.toDouble() else 0.0
        val value2 = if (secondValue.trim().isNotEmpty()) secondValue.toDouble() else 0.0

        val result = quantityPresenter.addQuantities(
            arrayOf(firstUnit, secondUnit, resultUnit), arrayOf(value1, value2))
        resultValue.setText(result.toString())
    }
}