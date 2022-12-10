import kotlin.math.abs

fun day9_1(input: String): Int {
    val allTailPositions = HashSet<Position>()
    var tailPosition = Position(0, 0)
    allTailPositions.add(tailPosition)
    var headPosition = Position(0, 0)
    for (moves in input.split("\n")) {
        if (moves == "") {
            continue
        }
        val movesSplit = moves.split(" ")
        for (i in 1..movesSplit[1].toInt()) {
            when (movesSplit[0]) {
                "L" -> headPosition = Position(headPosition.x - 1, headPosition.y)
                "R" -> headPosition = Position(headPosition.x + 1, headPosition.y)
                "U" -> headPosition = Position(headPosition.x, headPosition.y + 1)
                "D" -> headPosition = Position(headPosition.x, headPosition.y - 1)
            }
            if (!isNear(headPosition, tailPosition)) {
                if (headPosition.x == tailPosition.x) {
                    if (abs(headPosition.y - tailPosition.y) == 2) {
                        tailPosition = Position(tailPosition.x, tailPosition.y + signOfDifference(headPosition.y, tailPosition.y))
                    }
                } else if (headPosition.y == tailPosition.y) {
                    if (abs(headPosition.x - tailPosition.x) == 2) {
                        tailPosition = Position(tailPosition.x + signOfDifference(headPosition.x, tailPosition.x), tailPosition.y)
                    }
                } else {
                    tailPosition = Position(
                        tailPosition.x + signOfDifference(headPosition.x, tailPosition.x),
                        tailPosition.y + signOfDifference(headPosition.y, tailPosition.y)
                    )
                }
                allTailPositions.add(tailPosition)
            }
        }
    }
    return allTailPositions.size
}


fun day9_2(input: String): Int {
    val allTailPositions = HashSet<Position>()
    val positions = ArrayList<Position>()
    for (i in 0 until 10) {
        positions.add(Position(0, 0))
    }
    allTailPositions.add(positions[9])
    for (moves in input.split("\n")) {
        if (moves == "") {
            continue
        }
        val movesSplit = moves.split(" ")
        for (i in 1..movesSplit[1].toInt()) {
            when (movesSplit[0]) {
                "L" -> positions[0] = Position(positions[0].x - 1, positions[0].y)
                "R" -> positions[0] = Position(positions[0].x + 1, positions[0].y)
                "U" -> positions[0] = Position(positions[0].x, positions[0].y + 1)
                "D" -> positions[0] = Position(positions[0].x, positions[0].y - 1)
            }
            for(j in 1 until 10) {
                if (!isNear(positions[j-1], positions[j])) {
                    if (positions[j-1].x == positions[j].x) {
                        if (abs(positions[j-1].y - positions[j].y) == 2) {
                            positions[j] = Position(
                                positions[j].x,
                                positions[j].y + signOfDifference(positions[j-1].y, positions[j].y)
                            )
                        }
                    } else if (positions[j-1].y == positions[j].y) {
                        if (abs(positions[j-1].x - positions[j].x) == 2) {
                            positions[j] = Position(
                                positions[j].x + signOfDifference(positions[j-1].x, positions[j].x),
                                positions[j].y
                            )
                        }
                    } else {
                        positions[j] = Position(
                            positions[j].x + signOfDifference(positions[j-1].x, positions[j].x),
                            positions[j].y + signOfDifference(positions[j-1].y, positions[j].y)
                        )
                    }
                }
            }
            allTailPositions.add(positions[9])
        }
    }
    return allTailPositions.size
}

fun signOfDifference(a: Int, b: Int): Int {
    if (a == b) return 0
    return (a - b) / abs(a - b)
}

fun isNear(headPosition: Position, tailPosition: Position): Boolean {
    return abs(headPosition.x - tailPosition.x) <= 1 && abs(headPosition.y - tailPosition.y) <= 1
}

data class Position(val x: Int, val y: Int) {}