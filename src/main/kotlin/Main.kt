import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
//    val input = generateSequence(::readLine)
//    val lines = input.toList()
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val input = reader.readText()
    reader.close()
    println(day10_2(input))
}

