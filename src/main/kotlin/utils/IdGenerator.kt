package utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * Class to generate the random Id from the defined charsets
 */
class IdGenerator {
    companion object {
        // Function to generate random string id
        fun generate(n: Int): String {
            val charsets = "aA0bB1cC2dD3eE4fF5gG6hH7iI8jJ9kKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ"
            val randomString = StringBuilder()
            repeat(n) {
                val randomChar = charsets[Random.nextInt(charsets.length)]
                randomString.append(randomChar)
            }
            return randomString.toString()
        }
    }
}


/**
 * Class for transaction Id generation
 */
class TransactionId {
    companion object {
        fun generate(): String {
            return SimpleDateFormat
                .getDateTimeInstance()
                .format(Date())
                .replace(",", "")
                .replace(" ", "")
                .replace(":", "")
                .uppercase()
        }
    }
}