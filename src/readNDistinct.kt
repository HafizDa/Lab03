fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
    var numbers: List<Int>
    while (true) {
        println("Give $n numbers from $low to $high, separated by commas:")
        val input = readLine()?.split(',')?.mapNotNull { it.trim().toIntOrNull() }
        if (input != null && input.size == n && input.distinct().size == n && input.all { it in low..high }) {
            numbers = input
            break
        } else {
            println("Invalid input. Please try again.")
        }
    }
    return numbers
}
