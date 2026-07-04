package com.example.razorpayintegration.ui.viewmodel

import android.app.Activity
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import com.example.razorpayintegration.data.config.RazorpayConfig
import com.example.razorpayintegration.data.model.PaymentState
import com.example.razorpayintegration.ui.screen.PaymentScreen
import com.razorpay.Checkout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject

class PaymentViewModel: ViewModel() {
    private val _paymentState = MutableStateFlow<PaymentState>(PaymentState.Idel)
    val paymentState = _paymentState.asStateFlow()

    fun startPayment(activity: Activity, amount: Int, description: String ="test payment"){

        try {
            val options = JSONObject().apply {
                put("name","paymentIntegration app")
                put("description",description)
                put("currency","INR")
                put("amount",(amount*100).toLong())
                put("theme","#3399cc")
                put("method", JSONObject().apply {
                    put("upi",true)
                    put("qr",true)
                })
                put("upi", JSONObject().apply {
                    put("flow","intent")
                })
                put("readonly", JSONObject().apply {
                    put("email",false)
                    put("contact",false)
                    put("method",false)
                })
            }
            val checkout = Checkout()
                checkout.setKeyID(RazorpayConfig.KEY_ID)
                checkout.open(activity,options)
        }catch (e: Exception){
            _paymentState.value = PaymentState.Error(e.message.toString())
        }
    }

    fun handlePaymentSucess(paymentId: String){
        _paymentState.value = PaymentState.Success(paymentId)
    }

    fun handlePaymentError(message: String){
        _paymentState.value = PaymentState.Error(message)
    }
}