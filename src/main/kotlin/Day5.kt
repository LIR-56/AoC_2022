import java.util.*
import kotlin.collections.ArrayList

fun day5 (input: String, useBuffer: Boolean = false): String {
    val inputSplit = input.split("\n\n")
    val initPosition = inputSplit[0].split("\n")
    val moves = inputSplit[1].split("\n")
    val positions = ArrayList<Stack<Char>> ()
    //init
    for (i in initPosition.indices.reversed().drop(1)) {
        val layer = initPosition[i].chunked(4)
        for (stackNum in layer.indices) {
            if (positions.lastIndex < stackNum) {
                positions.add(Stack())
            }
            if (layer[stackNum].trim() != "") {
                positions[stackNum].add(layer[stackNum][1])
            }
        }
    }
    //moving
    for (move in moves) {
        val splitMove = move.split(" ")
        val from = splitMove[3].toInt() - 1
        val to = splitMove[5].toInt() - 1
        if (useBuffer) {
            val buffer = Stack<Char>()
            for (i in 1..splitMove[1].toInt()) {
                buffer.push(positions[from].pop())
            }
            for (i in 0..buffer.lastIndex) {
                positions[to].push(buffer.pop())
            }
        } else {
            for (i in 1..splitMove[1].toInt()) {
                positions[to].push(positions[from].pop())
            }
        }
    }

    var result = ""
    for (i in positions.indices) {
        result += positions[i].peek()
    }
    return result
}