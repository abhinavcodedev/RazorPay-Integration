package com.example.razorpayintegration.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.razorpayintegration.ui.viewmodel.PaymentViewModel

@Composable
fun PaymentScreen(viewModel: PaymentViewModel,
                  modifier: Modifier) {
    var amount by remember { mutableStateOf("") }
    val context = LocalContext.current
    val paymentState by viewModel.paymentState.collectAsState()


}