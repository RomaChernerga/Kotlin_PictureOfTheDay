package com.example.pictureofday.ui.apibottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pictureofday.R
import com.example.pictureofday.main.api.EarthFragment
import com.example.pictureofday.main.api.MarsFragment
import com.example.pictureofday.main.api.WeatherFragment
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_api_bottom.*

class ApiBottomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_bottom)

        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
        bottom_navigation_view.selectedItemId = R.id.bottom_view_earth

        bottom_navigation_view.getOrCreateBadge(R.id.bottom_view_earth)
        val badge = bottom_navigation_view.getBadge(R.id.bottom_view_earth)

        badge?.maxCharacterCount = 2
        badge?.number = 99
        badge?.badgeGravity = BadgeDrawable.BOTTOM_END



        bottom_navigation_view.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.bottom_view_earth -> {
                    Toast.makeText(this@ApiBottomActivity, "Item 0 tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_view_mars -> {
                    Toast.makeText(this@ApiBottomActivity, "Item 1 tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_view_weather -> {
                    Toast.makeText(this@ApiBottomActivity, "Item 2 tapped", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

        bottom_navigation_view.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    // Item tapped
                }
                R.id.bottom_view_mars -> {
                    // Item tapped
                }
                R.id.bottom_view_weather -> {
                    // Item tapped
                }
            }
        }
    }
}