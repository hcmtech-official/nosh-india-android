package com.noshindia.app.data

data class Testimonial(val quote: String, val author: String)

val testimonials = listOf(
    Testimonial(
        quote = "I've never had Gulab Jamuns this soft in Sydney. They literally melt in " +
            "your mouth. Tastes exactly like the ones my grandmother used to make.",
        author = "Priya S."
    ),
    Testimonial(
        quote = "The Milk Cake is incredible. Not too sweet, perfectly grainy, and so fresh. " +
            "Highly recommend for any family gathering!",
        author = "Amit K."
    ),
    Testimonial(
        quote = "Fast delivery and the sweets arrived warm. You can tell these are made with " +
            "high-quality ingredients. A true 5-star experience.",
        author = "Rahul M."
    )
)
