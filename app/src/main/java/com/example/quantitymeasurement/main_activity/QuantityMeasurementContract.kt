package com.example.quantitymeasurement.main_activity

interface QuantityMeasurementContract {

    interface View {
        fun performConvertQuantityBtnAction()
        fun performAddQuantityBtnAction()
    }

    interface Presenter {
        fun onConvertQuantityBtnClicked()
        fun onAddQuantityBtnClicked()
    }
}