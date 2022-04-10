package com.example.pictureofday


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.pictureofday.main.api.chips.ChipsFragment
import com.example.pictureofday.main.api.picture.PictureOfTheDayFragment
import kotlinx.android.synthetic.main.bottom_sheet_layout.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.DefaultTheme)
        checkStyle()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    private fun checkStyle() {
        when(this.let { ChipsFragment.MyPreferencesSCREEN(it).screenMode }) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        when(this.let { ChipsFragment.MyPreferencesTHEME(it).themeMode }) {
            0 -> setTheme(R.style.DefaultTheme)
            1 -> setTheme(R.style.RedTheme)
            2 -> setTheme(R.style.BlueTheme)
        }
    }


}