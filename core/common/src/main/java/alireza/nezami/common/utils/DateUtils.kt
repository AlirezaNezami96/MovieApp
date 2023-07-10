package alireza.nezami.common.utils

object DateUtils {

    fun getYearFromDate(dateString: String?): String {
        if (dateString == null) return ""
        return try {
            val parts = dateString.split("-")
            val yearString = parts[0]
            yearString
        } catch (e: Exception) {
            dateString
        }
    }

}