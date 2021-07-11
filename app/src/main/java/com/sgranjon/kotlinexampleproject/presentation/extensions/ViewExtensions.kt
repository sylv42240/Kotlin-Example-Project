package com.sgranjon.kotlinexampleproject.presentation.extensions

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.sgranjon.kotlinexampleproject.presentation.values.ViewValues


/**
 * Makes the view object visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Makes the view object gone
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * Makes the view object invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Set the view object's visibility depending on given boolean. Hidden state can be set but defaults to GONE
 */
fun View.setVisible(visible: Boolean, hiddenState: Int = View.GONE) {
    when {
        visible -> {
            show()
        }
        hiddenState == View.GONE -> {
            hide()
        }
        hiddenState == View.INVISIBLE -> {
            invisible()
        }
    }
}

/**
 * Set OnClickListener with a delay to prevent click spam
 */
fun View.setOnClickDelayListener(onClick: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime > ViewValues.DOUBLE_CLICK_DELAY) {
            onClick(it)
            lastClickTime = System.currentTimeMillis()
        }
    }
}

/**
 * This extension permit to avoid the xxx.text.toString() syntax on EditText widget
 */
val EditText.content
    get() = this.text.toString()

/**
 * EditText extensions
 */
fun EditText.clear() {
    setText("")
}

fun EditText.isNotBlank() = text.isNotBlank()

fun EditText.isBlank() = text.isBlank()

fun EditText.afterTextChanged(onTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(newText: Editable?) {
            newText?.let {
                onTextChanged(it.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

/**
 * Keyboard extensions
 */
fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun View.showKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
        this,
        0
    )
}

/**
 * Get color from @AttrRes
 */
@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}