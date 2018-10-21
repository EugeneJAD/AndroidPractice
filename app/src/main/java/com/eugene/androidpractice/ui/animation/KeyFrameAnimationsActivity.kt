package com.eugene.androidpractice.ui.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.Transition
import android.support.transition.TransitionInflater
import android.support.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.eugene.androidpractice.R
import kotlinx.android.synthetic.main.activity_key_frame_animations_set1.*

class KeyFrameAnimationsActivity : AppCompatActivity() {

    private val constraintSet1 = ConstraintSet()
    private val constraintSet2 = ConstraintSet()
    private var show = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animations)
        constraintSet1.clone(this, R.layout.activity_key_frame_animations_set1)
        constraintSet2.clone(this, R.layout.activity_key_frame_animations_set2)
        article_image.setOnClickListener {
            if (show)
                showArticleDetails()
            else
                showArticlePreview()
        }
    }

    private fun showArticlePreview() {
        show = true
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(constraint_layout_root, transition)
        constraintSet1.applyTo(constraint_layout_root)
    }

    private fun showArticleDetails() {
        show = false
        val transition: Transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.article_details_transition)
        TransitionManager.beginDelayedTransition(constraint_layout_root, transition)
        constraintSet2.applyTo(constraint_layout_root)
    }
}
