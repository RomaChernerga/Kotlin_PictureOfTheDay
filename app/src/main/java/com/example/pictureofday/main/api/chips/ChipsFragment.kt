package com.example.pictureofday.main.api.chips

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.pictureofday.R
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_chips.*


open class ChipsFragment : Fragment() {

    private var buttonIsVisible = false
    private var DefaultTheme: Int = 0
    private var RedTheme: Int = 1
    private var BlueTheme: Int = 2
    private var MODE_NIGHT_YES: Int = 0
    private var MODE_NIGHT_NO: Int = 1


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_chips_start, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChipsListener()

    }

    private fun setChipsListener() {

        btn_def_theme.setOnClickListener {
            apply {
                activity?.setTheme(R.style.DefaultTheme)
                MyPreferencesTHEME(it.context).themeMode = DefaultTheme
                Toast.makeText(context,"Включена DefaultTheme тема", Toast.LENGTH_SHORT).show()
                activity?.recreate()
            }
        }

        btn_blue_theme.setOnClickListener {
            apply {
                activity?.setTheme(R.style.RedTheme)
                MyPreferencesTHEME(it.context).themeMode = RedTheme
                Toast.makeText(context,"Включена RedTheme тема", Toast.LENGTH_SHORT).show()
                activity?.recreate()
            }
        }

        btn_red_theme.setOnClickListener {
            apply {
                activity?.setTheme(R.style.BlueTheme)
                MyPreferencesTHEME(it.context).themeMode = BlueTheme
                Toast.makeText(context,"Включена BlueTheme тема", Toast.LENGTH_SHORT).show()
                activity?.recreate()
            }
        }

        check_style.setOnClickListener {

            TransitionManager.beginDelayedTransition(transitions_container_styles, Slide(Gravity.END))
            buttonIsVisible = !buttonIsVisible
            btn_dark_style.visibility = if(buttonIsVisible) View.VISIBLE else View.GONE
            btn_light_style.visibility = if(buttonIsVisible) View.VISIBLE else View.GONE
            if (buttonIsVisible) {
                check_style.alpha = 0.6f
            } else {
                check_style.alpha = 1f
            }
        }

        btn_dark_style.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MyPreferencesSCREEN(it.context).screenMode = MODE_NIGHT_YES
            Toast.makeText(context, "Выбран темный режим экрана", Toast.LENGTH_SHORT).show()
        }

        btn_light_style.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MyPreferencesSCREEN(it.context).screenMode = MODE_NIGHT_NO
            Toast.makeText(context, "Выбран светлый режим экрана", Toast.LENGTH_SHORT).show()
        }
    }

    class MyPreferencesSCREEN(context: Context) {
        companion object {
            private const val SCREEN_MODE = "SCREEN_MODE"
        }

        private val preferences by lazy { getDefaultSharedPreferences(context) }

        var screenMode = preferences.getInt(SCREEN_MODE, 0)

        set(value) = preferences.edit().putInt(SCREEN_MODE, value).apply()



    }

    class MyPreferencesTHEME(context: Context) {
        companion object {
            private const val THEME_MODE = "THEME_MODE"
        }
        private val preferences by lazy { getDefaultSharedPreferences(context) }
        var themeMode = preferences.getInt(MyPreferencesTHEME.THEME_MODE,0)
        set(value) = preferences.edit().putInt(THEME_MODE, value).apply()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_chip_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

}