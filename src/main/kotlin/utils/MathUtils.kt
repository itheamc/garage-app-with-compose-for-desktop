package utils

/**
 * Function to round the double value
 */
fun Double.round(n: Int): Double {
    val s = this.toString()
    return if (s.contains(".")) {
        val lastIndex = s.indexOf(".") + n
        if (lastIndex <= s.length - 1) {
            var valAtLastIndex = s[lastIndex].toString().toInt()
            val valAfterLastIndex = s[lastIndex + 1].toString().toInt()
            if (valAfterLastIndex >= 5) {
                valAtLastIndex += 1
            }
            (s.substring(0, lastIndex) + valAtLastIndex).toDouble()
        } else {
            s.toDouble()
        }
    } else {
        s.toDouble()
    }
}