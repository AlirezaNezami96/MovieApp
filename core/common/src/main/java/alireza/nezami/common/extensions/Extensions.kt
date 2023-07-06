package alireza.nezami.common.extensions

fun Int?.orZero() : Int {
    return this ?: 0
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}
