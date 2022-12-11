fun day10_1(input: String): Long {
    var x = 1
    var cycle = 0
    var sum = 0L
    val cyclesToSum = listOf(20, 60, 100, 140, 180, 220)
    for (line in input.split("\n")) {
        if (line == "") {
            continue
        } else if (line == "noop") {
            cycle++
            if (cycle in cyclesToSum) {
                sum += x * cycle
            }
        } else if (line.startsWith("addx")) {
            cycle++
            if (cycle in cyclesToSum) {
                sum += x * cycle
            }
            cycle++
            if (cycle in cyclesToSum) {
                sum += x * cycle
            }
            x += (line.split(" "))[1].toInt()
        }
    }
    return sum
}


fun day10_2(input: String): String {
    var x = 1
    var cycle = 0
    val resultString = StringBuilder()
    for (line in input.split("\n")) {
        if (line == "") {
            continue
        } else if (line == "noop") {
            cycle++
            resultString.append(drawPixel(x, cycle))
        } else if (line.startsWith("addx")) {
            cycle++
            resultString.append(drawPixel(x, cycle))
            cycle++
            resultString.append(drawPixel(x, cycle))
            x += (line.split(" "))[1].toInt()
        }
    }
    return resultString.toString()
}

fun drawPixel(x: Int, cycle: Int): String {
    val position = cycle - 1
    val c: Char = if (x == position % 40 || x - 1 == position % 40 || x + 1 == position % 40) {
        '#'
    } else
        '.'

    return if (cycle % 40 == 0) {
        c.toString() + "\n"
    } else c.toString()
}