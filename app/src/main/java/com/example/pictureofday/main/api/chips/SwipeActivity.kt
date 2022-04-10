package com.example.pictureofday.main.api.chips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.findNavController
import com.example.pictureofday.R
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.behavior.SwipeDismissBehavior
import androidx.navigation.ui.navigateUp
import kotlinx.android.synthetic.main.fragment_chips.*

class SwipeActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_swipe)

        val swipe: SwipeDismissBehavior<CardView> = SwipeDismissBehavior()

        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
        swipe.listener = object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View) {
                Toast.makeText(
                    this@SwipeActivity,
                    "Card swiped !!", Toast.LENGTH_SHORT
                ).show()
            }

            override fun onDragStateChanged(state: Int) {

            }
        }

        val coordinatorParams = card.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorParams.behavior = swipe
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_swipe)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}