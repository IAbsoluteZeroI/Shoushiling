package com.example.kotlinsecond

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View


@Suppress("DEPRECATION")
class CardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    var mFrontBitmapDrawable : BitmapDrawable? = null
    var mBackBitmapDrawable : BitmapDrawable? = null
    var mIsFrontShowing = false
    var mImageName : String? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    fun init(name : String, isFront : Boolean) {
        mImageName = name
        mIsFrontShowing = isFront
        if (mImageName == "rock") {
            mFrontBitmapDrawable =
                resources.getDrawable(R.drawable.rock) as BitmapDrawable?
        }
        if (mImageName == "scissors") {
            mFrontBitmapDrawable =
                resources.getDrawable(R.drawable.scissors) as BitmapDrawable?
        }
        if (mImageName == "paper") {
            mFrontBitmapDrawable =
                resources.getDrawable(R.drawable.paper) as BitmapDrawable?
        }
        mBackBitmapDrawable = resources.getDrawable(R.drawable.beck) as BitmapDrawable?
        if (mIsFrontShowing) {
            setImageDrawable(mFrontBitmapDrawable)
        } else {
            setImageDrawable(mBackBitmapDrawable)
        }
    }

    fun flip() {
        val rotate = PropertyValuesHolder.ofFloat(View.ROTATION_Y, -180f, 0f)
        val cardAnimator = ObjectAnimator.ofPropertyValuesHolder(this, rotate)
        cardAnimator.duration = 500
        cardAnimator.addUpdateListener { p0 ->
            if (p0.animatedFraction >= 0.5) {
                setImageDrawable(mFrontBitmapDrawable)
            } else {
                setImageDrawable(mBackBitmapDrawable)
            }
        }
        if (mIsFrontShowing) {
            cardAnimator.reverse()
            mIsFrontShowing = false
        } else {
            cardAnimator.start()
            mIsFrontShowing = true
        }
    }
}