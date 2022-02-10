package com.example.pictureofday.picture

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import androidx.lifecycle.Observer
import com.example.pictureofday.MainActivity
import com.example.pictureofday.R
import com.example.pictureofday.chips.ChipsFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_fragment.*

class PictureOfTheDayFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var viewModel: PictureOfTheDayViewModel

    companion object{
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false )
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            // Инициализируем ViewModel
        viewModel = ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
        viewModel.getData()
            .observe(this@PictureOfTheDayFragment, Observer<PictureOfTheDayData> { renderData(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        input_layout.setEndIconOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                  // открываем Активити по введеному тексту на сайте Вики
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }

        setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)  {
            R.id.app_bar_fav -> toast("Favourite")

            R.id.app_bar_settings ->
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.container, ChipsFragment())?.addToBackStack(null)?.commit()

            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager,"tag")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }



    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

//        bottomSheetBehavior.addBottomSheetCallback(object :
//        BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_DRAGGING -> TODO()
//                    BottomSheetBehavior.STATE_COLLAPSED -> TODO()
//                    BottomSheetBehavior.STATE_EXPANDED -> TODO()
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO()
//                    BottomSheetBehavior.STATE_HIDDEN -> TODO()
//                    BottomSheetBehavior.STATE_SETTLING -> TODO()
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                TODO("Not yet implemented")
//            }
//        })
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))

        setHasOptionsMenu(true)

        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun renderData(data: PictureOfTheDayData) {
        when(data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url

                if(url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    image_view.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.image_icon_1)
                        placeholder(R.drawable.image_icon_1)
                    }
                }
            }

            is PictureOfTheDayData.Loading -> {
                //Nothing to do
            }

            is PictureOfTheDayData.Error -> {
                toast(data.error.message)
            }
        }
    }
    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0,258)
            show()
        }
    }
}