package bz.soft.currencydetector.root.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun textWatcher(afterTextChanged: (String) -> Unit): TextWatcher =
    object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {  }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s.toString())
        }
    }

fun EditText.setTextWatcher(textWatcher: TextWatcher) {
    this.addTextChangedListener(textWatcher)
}

fun EditText.changeText(text: String) {
    with(this) {
        setText(text)
        setSelection(text.length)
    }
}