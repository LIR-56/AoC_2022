fun main() {
    val input = generateSequence(::readLine)
    val lines = input.toList()
    println(secondDay2(lines))
}

