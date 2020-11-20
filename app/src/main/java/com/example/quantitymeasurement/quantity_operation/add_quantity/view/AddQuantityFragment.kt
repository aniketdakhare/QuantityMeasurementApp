package com.example.quantitymeasurement.quantity_operation.add_quantity.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.quantity_operation.add_quantity.AddQuantityContract
import com.example.quantitymeasurement.quantity_operation.add_quantity.presenter.AddQuantityPresenter
import com.example.quantitymeasurement.quantity_operation.convert_quantity.presenter.ConvertQuantityPresenter
import com.example.quantitymeasurement.quantity_operation.model.Quantity
import com.example.quantitymeasurement.quantity_operation.model.enums.UnitType
import com.example.quantitymeasurement.quantity_operation.model.service.QuantityMeasurement
import kotlinx.android.synthetic.main.fragment_add_quantity.*

class AddQuantityFragment : Fragment(R.layout.fragment_add_quantity), AddQuantityContract.View {
    private lateinit var quantityTypeSpinner: Spinner
    private lateinit var quantity1Unit: Spinner
    private lateinit var quantity2Unit: Spinner
    private lateinit var resultUnit: Spinner
    private lateinit var presenter: AddQuantityPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quantityTypeSpinner = view.findViewById(R.id.quantity)
        quantity1Unit = view.findViewById(R.id.firstUnit)
        quantity2Unit = view.findViewById(R.id.secondUnit)
        resultUnit = view.findViewById(R.id.resultUnit)
        initView()
        onQuantitySelected()
    }

    private fun initView() {
        presenter = AddQuantityPresenter(this, QuantityMeasurement())
    }

    private fun onQuantitySelected() {
        val quantityArray = resources.getStringArray(R.array.addableQuantity)
        quantityTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val units = presenter.onQuantitySelected(quantityArray[position])
                val adapter = ArrayAdapter.createFromResource(requireContext(), units,
                    android.R.layout.simple_spinner_dropdown_item)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                quantity1Unit.adapter = adapter
                quantity2Unit.adapter = adapter
                resultUnit.adapter = adapter
                performAddition()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun performAddition() {
        val firstQuantityValue = firstValue.editableText.toString()
        val firstQuantityUnit = firstUnit.selectedItem.toString()
        val secondQuantityValue = secondValue.editableText.toString()
        val secondQuantityUnit = secondUnit.selectedItem.toString()
        val resultQuantityUnit = resultUnit.selectedItem.toString()

        val value1 =
            if (firstQuantityValue.trim().isNotEmpty()) firstQuantityValue.toDouble() else 0.0
        val value2 =
            if (secondQuantityValue.trim().isNotEmpty()) secondQuantityValue.toDouble() else 0.0

        firstValue.doAfterTextChanged {
            presenter.onFirstQuantityValueChanged(
                Quantity(UnitType.valueOf(firstQuantityUnit), value1), Quantity(
                    UnitType.valueOf(secondQuantityUnit), value2
                ), UnitType.valueOf(resultQuantityUnit)
            )
        }
        secondValue.doAfterTextChanged {
            presenter.onSecondQuantityValueChanged(
                Quantity(UnitType.valueOf(firstQuantityUnit), value1), Quantity(
                    UnitType.valueOf(secondQuantityUnit), value2
                ), UnitType.valueOf(resultQuantityUnit)
            )
        }
        firstUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int,
                id: Long
            ) {
                presenter.onFirstQuantityUnitSelected(
                    Quantity(UnitType.valueOf(firstQuantityUnit), value1), Quantity(
                        UnitType.valueOf(secondQuantityUnit), value2
                    ), UnitType.valueOf(resultQuantityUnit)
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        secondUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int,
                id: Long
            ) {
                presenter.onSecondQuantityUnitSelected(
                    Quantity(UnitType.valueOf(firstQuantityUnit), value1), Quantity(
                        UnitType.valueOf(secondQuantityUnit), value2
                    ), UnitType.valueOf(resultQuantityUnit)
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        resultUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int,
                id: Long
            ) {
                presenter.onResultQuantityUnitSelected(
                    Quantity(UnitType.valueOf(firstQuantityUnit), value1), Quantity(
                        UnitType.valueOf(secondQuantityUnit), value2
                    ), UnitType.valueOf(resultQuantityUnit)
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun performQuantitySelectedAction(quantity: String): Int {
        return when (quantity) {
            "Length" -> R.array.length
            "Weight" -> R.array.weight
            "Volume" -> R.array.volume
            else -> 0
        }
    }

    override fun performFirstQuantityValueChangedAction(result: String) {
        resultValue.setText(result)
    }

    override fun performFirstQuantityUnitSelectedAction(result: String) {
        resultValue.setText(result)
    }

    override fun performSecondQuantityValueChangedAction(result: String) {
        resultValue.setText(result)
    }

    override fun performSecondQuantityUnitSelectedAction(result: String) {
        resultValue.setText(result)
    }

    override fun performResultQuantityUnitSelectedAction(result: String) {
        resultValue.setText(result)
    }
}