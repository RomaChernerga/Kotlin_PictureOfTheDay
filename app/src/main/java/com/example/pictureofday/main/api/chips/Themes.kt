package com.example.pictureofday.main.api.chips

import com.example.pictureofday.R

enum class Theme (
    val themeKey: String,
    val themeRes: Int
) {
    Default_theme("Default_theme", R.style.DefaultTheme),
    Red_theme("Red_theme", R.style.RedTheme),
    Blue_theme("Blue_theme", R.style.BlueTheme)
}