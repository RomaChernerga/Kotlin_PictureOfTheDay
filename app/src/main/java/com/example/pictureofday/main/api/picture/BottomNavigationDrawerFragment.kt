package com.example.pictureofday.main.api.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pictureofday.R
import com.example.pictureofday.main.api.recycler.RecyclerActivity
import com.example.pictureofday.ui.apibottom.animations.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_api_bottom.*
import kotlinx.android.synthetic.main.bottom_navigation_layout.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

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

                R.id.screen_one -> activity?.let {
                    startActivity(Intent(it, AnimationsActivity::class.java))
                    Toast.makeText(context, "Открыта AnimationActivity", Toast.LENGTH_SHORT).show()}

                R.id.screen_two -> activity?.let {
                    startActivity(Intent(it, AnimationActivity_enlarge::class.java))
                    Toast.makeText(context, "Открыта AnimationActivityEnLarge", Toast.LENGTH_SHORT).show()}

                R.id.screen_three -> activity?.let {
                    startActivity(Intent(it,AnimationActivityPath::class.java))
                    Toast.makeText(context, "Открыта AnimationActivityPath", Toast.LENGTH_SHORT).show()}
                R.id.screen_four -> activity?.let {
                    startActivity(Intent(it, AnimationActivityShuffle::class.java))
                    Toast.makeText(context, "Открыта AnimationActivityShuffle", Toast.LENGTH_SHORT).show()}

                R.id.screen_five -> activity?.let {
                    startActivity(Intent(it, AnimationActivityAnimator::class.java))
                    Toast.makeText(context, "Открыта AnimationActivityAnimator", Toast.LENGTH_SHORT).show()}

                R.id.screen_six -> activity?.let {
                    startActivity(Intent(it, AnimationActivityInterpollator::class.java))
                    Toast.makeText(context, "Открыта AnimationActivityAnimator", Toast.LENGTH_SHORT).show()
                }
                R.id.screen_seven -> activity?.let {
                    startActivity(Intent(it, RecyclerActivity::class.java))
                    Toast.makeText(context, "Открыта RecyclerActivity", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

}
