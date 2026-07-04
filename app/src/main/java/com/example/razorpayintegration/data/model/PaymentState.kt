package com.example.razorpayintegration.data.model

sealed class PaymentState{
    data class Success(val paymentId: String): PaymentState()
    data class Error(val message: String): PaymentState()
    data object Idel: PaymentState()
}
