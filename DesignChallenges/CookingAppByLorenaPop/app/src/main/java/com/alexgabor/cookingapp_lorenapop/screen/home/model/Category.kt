package com.alexgabor.cookingapp_lorenapop.screen.home.model

import com.alexgabor.cookingapp_lorenapop.R

data class Category(
    val res: Int,
    val name: String,
    val count: String,
)

val categories = listOf(
    Category(R.drawable.hamburger_1f354, "Burger", "120+"),
    Category(R.drawable.slice_of_pizza_1f355, "Pizza", "45+"),
    Category(R.drawable.hamburger_1f354, "Burger", "120+"),
    Category(R.drawable.slice_of_pizza_1f355, "Pizza", "45+"),
    Category(R.drawable.hamburger_1f354, "Burger", "120+"),
    Category(R.drawable.slice_of_pizza_1f355, "Pizza", "45+"),
)