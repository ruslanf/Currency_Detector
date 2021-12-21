package bz.soft.currencydetector.root.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import coil.loadAny
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(@StringRes messageId: Int): Snackbar =
    Snackbar.make(this, messageId, Snackbar.LENGTH_LONG)

fun ImageView.showImage(image: String) {
    loadImage(this, image)
}

fun ImageView.showImageSquare(@DrawableRes image: Int) {
    loadImage(this, image, isRounded = false)
}

fun ImageView.showImage(@DrawableRes image: Int, radius: Float = 48f) {
    loadImage(this, image, radius)
}

fun ImageView.showImage(image: Uri) {
    loadImage(this, image)
}

fun ImageView.showImage(image: Bitmap) {
    loadImage(this, image)
}

fun ImageView.showImage(image: Drawable) {
    loadImage(this, image)
}

fun ImageView.showImage(comparable: Comparable<*>) {
    loadImage(this, comparable)
}

fun ImageView.loadImage(url: String) {
    loadImageFromUrl(this, url)
}

private fun loadImageFromUrl(imageView: ImageView, image: Any) {
    imageView.loadAny(image) {
        crossfade(true)
    }
}

private fun loadImage(imageView: ImageView, image: Any, radius: Float = 48f, isRounded: Boolean = true) {
    imageView.loadAny(image) {
        crossfade(true)
        if (isRounded) transformations(RoundedCornersTransformation(radius))
    }
}

fun ImageView.showAvatar(comparable: Comparable<*>) {
    this.loadAny(comparable) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}

fun ImageView.showAvatar(comparable: Comparable<*>, radius: Float = 48f) {
    this.loadAny(comparable) {
        crossfade(true)
        transformations(RoundedCornersTransformation(radius))
    }
}