package com.example.quantitymeasurement.main_activity.presenter

import com.example.quantitymeasurement.main_activity.QuantityMeasurementContract

class QuantityPresenter(private val view: QuantityMeasurementContract.View) :
    QuantityMeasurementContract.Presenter {
    override fun onConvertQuantityBtnClicked() {
        view.performConvertQuantityBtnAction()
    }

    override fun onAddQuantityBtnClicked() {
        view.performAddQuantityBtnAction()
    }
}