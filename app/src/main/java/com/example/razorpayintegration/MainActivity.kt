package com.example.razorpayintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.razorpayintegration.ui.screen.PaymentScreen
import com.example.razorpayintegration.ui.theme.RazorpayIntegrationTheme
import com.example.razorpayintegration.ui.viewmodel.PaymentViewModel
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener

class MainActivity : ComponentActivity(), PaymentResultWithDataListener {
    private val viewModel: PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Checkout.preload(applicationContext)
        setContent {
            PaymentScreen(viewModel = viewModel)
        }
    }

    override fun onPaymentSuccess(razorPaymentId: String?, paymentData: PaymentData?) {
        razorPaymentId?.let { viewModel.handlePaymentSuccess(it) }
    }

    override fun onPaymentError(code: Int, response: String?, paymentData: PaymentData?) {
         viewModel.handlePaymentError(code,response?: "Payment Failed")
    }
}
