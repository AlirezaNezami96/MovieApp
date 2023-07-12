package alireza.nezami.common.extensions

import java.text.DecimalFormat

/**
 * Returns this [Int] value if it is not null, or 0 if it is null.
 *
 * @return The non-null [Int] value or 0.
 */
fun Int?.orZero() : Int {
    return this ?: 0
}

/**
 * Returns this [Double] value if it is not null, or 0.0 if it is null.
 *
 * @return The non-null [Double] value or 0.0.
 */
fun Double?.orZero(): Double {
    return this ?: 0.0
}


/**
 * Formats the nullable [Double] value with one decimal place.
 *
 * @return The formatted value as a [Double] with one decimal place, or null if the input value is null.
 */
fun Double?.formatToOneDecimalPlace(): Double? {
    if (this == null) {
        return null
    }

    val decimalFormat = DecimalFormat("#.#")
    val formattedValue = decimalFormat.format(this).toDouble()
    return if (formattedValue.isNaN()) null else formattedValue
}

/**
 * Returns this [Boolean] value if it is not null, or false if it is null.
 *
 * @return The non-null [Boolean] value or false.
 */
fun Boolean?.orFalse(): Boolean {
    return this ?: false
}
/**
 * Returns this [Boolean] value if it is not null, or false if it is null.
 *
 * @return The non-null [Boolean] value or false.
 */
fun Boolean.toYesOrNo(): String {
    return if (this) "Yes" else "No"
}

/**
 * Returns this [List<T>] value if it is not null, or empty list if it is null.
 *
 * @return The non-null [List<T>] value or empty list.
 */
fun <T> List<T>?.orEmptyList(): List<T> {
    return this ?: emptyList()
}

/**
 * Separates the digits of an integer value with commas every three digits.
 *
 * @return The string representation of the integer with comma-separated digits,
 * or an empty string if the input value is null.
 */
fun Int?.formatWithCommas(): String {
    if (this == null) {
        return ""
    }

    val formattedValue = StringBuilder()
    val valueString = this.toString()
    val length = valueString.length

    for (i in 0 until length) {
        formattedValue.append(valueString[i])
        if ((length - i - 1) % 3 == 0 && i < length - 1) {
            formattedValue.append(",")
        }
    }

    return formattedValue.toString()
}