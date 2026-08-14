package com.noshindia.app.ui.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.noshindia.app.data.Product
import com.noshindia.app.data.PricingUnit
import com.noshindia.app.data.kgTiers
import com.noshindia.app.data.pieceTiers
import com.noshindia.app.data.products

class OrderViewModel : ViewModel() {

    var selectedProduct by mutableStateOf(products.first())
        private set

    // Index into pieceTiers or kgTiers depending on selectedProduct.unit
    var selectedTierIndex by mutableStateOf(2) // defaults to the 3rd tier, matching the live site's pre-selected option
        private set

    var name by mutableStateOf("")
        private set
    var phone by mutableStateOf("")
        private set
    var address by mutableStateOf("")
        private set
    var nameError by mutableStateOf<String?>(null)
        private set
    var phoneError by mutableStateOf<String?>(null)
        private set
    var addressError by mutableStateOf<String?>(null)
        private set

    fun onSelectProduct(product: Product) {
        selectedProduct = product
        selectedTierIndex = 0
    }

    fun onSelectTierIndex(index: Int) {
        selectedTierIndex = index
    }

    fun onNameChange(value: String) { name = value; nameError = null }
    fun onPhoneChange(value: String) { phone = value; phoneError = null }
    fun onAddressChange(value: String) { address = value; addressError = null }

    fun currentQuantityLabel(): String = when (selectedProduct.unit) {
        PricingUnit.PIECE -> "${pieceTiers[selectedTierIndex]} Pieces"
        PricingUnit.KG -> "${kgTiers[selectedTierIndex]} KG"
    }

    fun currentTotal(): Double = when (selectedProduct.unit) {
        PricingUnit.PIECE -> pieceTiers[selectedTierIndex] * selectedProduct.unitPrice
        PricingUnit.KG -> kgTiers[selectedTierIndex] * selectedProduct.unitPrice
    }

    fun tierOptions(): List<String> = when (selectedProduct.unit) {
        PricingUnit.PIECE -> pieceTiers.map { qty ->
            "$qty Pieces ($${"%.2f".format(qty * selectedProduct.unitPrice)})"
        }
        PricingUnit.KG -> kgTiers.map { kg ->
            "$kg KG ($${"%.2f".format(kg * selectedProduct.unitPrice)})"
        }
    }

    /** Returns a ready-to-send WhatsApp message, or null if validation fails. */
    fun buildOrderMessageOrValidate(): String? {
        var valid = true
        if (name.isBlank()) { nameError = "Name is required"; valid = false }
        if (phone.isBlank()) { phoneError = "Phone number is required"; valid = false }
        if (address.isBlank()) { addressError = "Delivery address is required"; valid = false }
        if (!valid) return null

        return "Hi Nosh India! I'd like to place an order:\n\n" +
            "Product: ${selectedProduct.name}\n" +
            "Quantity: ${currentQuantityLabel()}\n" +
            "Total: \$${"%.2f".format(currentTotal())} (Free delivery)\n\n" +
            "Name: $name\n" +
            "Phone: $phone\n" +
            "Delivery address: $address"
    }
}
