fun day4_1(input:List<String>):Int {
    return input.stream()
        .map { x -> intersect(x) }
        .filter {x -> x}
        .count().toInt()
}

fun day4_2(input:List<String>):Int {
    return input.stream()
        .map { x -> overlap(x) }
        .filter {x -> x}
        .count().toInt()
}

fun intersect(line: String): Boolean {
    val input = line.split(",","-")
    return if (input[0].toInt() < input[2].toInt()) {
        input[1].toInt() >= input[3].toInt()
    } else if (input[0].toInt() > input[2].toInt()) {
        input[1].toInt() <= input[3].toInt()
    } else
        return true
}

fun overlap(line: String): Boolean {
    val input = line.split(",","-")
    return if (input[0].toInt() < input[2].toInt()) {
        input[1].toInt() >= input[2].toInt()
    } else if (input[0].toInt() > input[2].toInt()) {
        input[0].toInt() <= input[3].toInt()
    } else true
}