package com.example.pictureofday.picture

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat
import com.example.pictureofday.MainActivity
import com.example.pictureofday.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_api_bottom.*
import kotlinx.android.synthetic.main.bottom_navigation_layout.*


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout, container, false )


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.theme_one -> let {
                    Toast.makeText(context, "Выбрана стандартная тема", Toast.LENGTH_SHORT).show()
                }
                R.id.theme_two -> let {
                    Toast.makeText(context, "Выбрана тема 2", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

}

private fun Any?.setTheme() {
    TODO("Not yet implemented")
}

private fun Any?.setTo(themePictureofday: Int) {

}
