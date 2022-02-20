package com.example.pictureofday.chips


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.pictureofday.R
import kotlinx.android.synthetic.main.fragment_chips.*



class ChipsFragment : Fragment() {

    val PREF = "pref"
    val THEME = "theme"

    val AppThemeDefault = 0;
    val AppThemeLightStyle_1 = 1;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chips, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         btn_theme_one.setOnClickListener {
            activity?.setTheme(R.style.RedTheme)
            Toast.makeText(context, "Тема 1", Toast.LENGTH_SHORT).show()
            activity?.recreate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}