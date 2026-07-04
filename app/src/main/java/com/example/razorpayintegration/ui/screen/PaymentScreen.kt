package com.example.razorpayintegration.ui.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.razorpayintegration.data.model.PaymentState
import com.example.razorpayintegration.ui.viewmodel.PaymentViewModel

@Composable
fun PaymentScreen(viewModel: PaymentViewModel) {
    var amount by remember { mutableStateOf("") }
    val context = LocalContext.current
    val paymentState by viewModel.paymentState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(value = amount,
            onValueChange = {amount=it},
            label = { Text(text = "Amount")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
            amount.toDoubleOrNull()?.let {
                viewModel.startPayment(context as Activity, amount.toInt())
            }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pay with Razorpay")
        }

        when(val state = paymentState){
            is PaymentState.Success->{
                Text("Razorpay: ${state.paymentId}",
                    color = MaterialTheme.colorScheme.primary)
            }
            is PaymentState.Error->{
                Text("Error: ${state.message}",
                    color = MaterialTheme.colorScheme.error)
            }
            else -> {}
        }
    }
}