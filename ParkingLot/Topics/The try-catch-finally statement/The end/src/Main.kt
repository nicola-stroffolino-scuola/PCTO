fun solution() {
    val a = readln().toInt()
    val b = readln().toInt()

    println(
        try {
            a / b
        } catch (e: Exception) {
            e.message
        }
    )

    print("This is the end, my friend.")
}