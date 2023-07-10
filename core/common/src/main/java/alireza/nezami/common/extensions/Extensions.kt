package alireza.nezami.common.extensions

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
 * Returns this [Boolean] value if it is not null, or false if it is null.
 *
 * @return The non-null [Boolean] value or false.
 */
fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

/**
 * Returns this [List<T>] value if it is not null, or empty list if it is null.
 *
 * @return The non-null [List<T>] value or empty list.
 */
fun <T> List<T>?.orEmptyList(): List<T> {
    return this ?: emptyList()
}
