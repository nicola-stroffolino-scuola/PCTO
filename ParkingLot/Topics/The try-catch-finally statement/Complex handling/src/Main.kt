import java.io.IOException
import java.lang.ArithmeticException

fun suspiciousFunction (param: Int) {
    when (param) {
        0 -> throw Exception("Some exceptions?")
        1 -> throw ArithmeticException("Division by zero")
        2 -> throw Exception("An exception occurred here")
        3 -> throw IOException()
    }
}        

fun handleException (data: Int) {
    try {
        suspiciousFunction(data)
    } catch (e: Exception) {
        println(
            when (e) {
                is IOException -> "The IOException occurred"
                else -> e.message
            }
        )
    } finally {
        print("Handling completed successfully!")
    }
}