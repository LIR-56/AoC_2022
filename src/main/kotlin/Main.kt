fun main() {
    val input = generateSequence(::readLine)
    val lines = input.toList()
    println(day1(lines, 1))
}

