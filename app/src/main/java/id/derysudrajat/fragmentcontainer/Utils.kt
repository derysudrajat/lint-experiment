package id.derysudrajat.fragmentcontainer

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    this.theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun Int.getInverseColor(): Int {
    return if (ColorUtils.calculateLuminance(this) <= 0.5) R.color.white else R.color.black
}