package bz.soft.currencydetector.root.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Parcelable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bz.soft.currencydetector.root.delegated.DelegateAdapter

fun ProgressBar.showProgressBar(progress: Int) {
    this.isVisible = progress > 0
}

val EditText.asTextView: TextView
    get() = this

var EditText.value: CharSequence?
    get() = asTextView.text
    set(value) {
        asTextView.text = value
    }

@SuppressLint("UseCompatLoadingForDrawables")
fun View.drawable(@DrawableRes res: Int): Drawable = this.resources.getDrawable(res, null)
@SuppressLint("UseCompatLoadingForDrawables")
fun Context.drawable(@DrawableRes res: Int): Drawable = this.resources.getDrawable(res, null)

fun Context.color(@ColorRes color: Int): Int = this.resources.getColor(color, null)

fun Context.setAnimationTextLeft(): Animation =
    AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)

fun scrollToPosition(recyclerView: RecyclerView, position: Int) =
    Handler().postDelayed({ recyclerView.scrollToPosition(position) }, 200)

fun <T> RecyclerView.setRecyclerView(v: View, delegateAdapter: DelegateAdapter<T>) {
    val recyclerViewState: Parcelable? = null
    var position = 0
    this.apply {
        adapter = delegateAdapter
        startAnimation(context.setAnimationTextLeft())
        layoutManager = LinearLayoutManager(v.context, RecyclerView.VERTICAL, false)
        recyclerViewState?.apply {
            layoutManager?.onRestoreInstanceState(recyclerViewState)
            scrollToPosition(this@setRecyclerView, position)
        }
    }
}