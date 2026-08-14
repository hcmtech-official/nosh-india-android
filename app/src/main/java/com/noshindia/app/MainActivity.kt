package com.noshindia.app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.noshindia.app.ui.navigation.NoshIndiaNavGraph
import com.noshindia.app.ui.theme.NoshIndiaTheme
import java.net.URLEncoder

class MainActivity : ComponentActivity() {

    // PLACEHOLDER — replace with the real Nosh India WhatsApp business
    // number in international format, no "+" or leading zeros
    // (e.g. Australian mobile 04XX XXX XXX -> "614XXXXXXXX").
    private val whatsAppNumber = "REPLACE_WITH_BUSINESS_NUMBER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NoshIndiaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NoshIndiaNavGraph(onPlaceOrder = ::sendOrderViaWhatsApp)
                }
            }
        }
    }

    private fun sendOrderViaWhatsApp(message: String) {
        val encoded = URLEncoder.encode(message, "UTF-8")
        val uri = Uri.parse("https://wa.me/$whatsAppNumber?text=$encoded")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No app found to open WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
}
