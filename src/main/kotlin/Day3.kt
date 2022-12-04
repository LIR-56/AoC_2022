import java.lang.RuntimeException

fun day3_1(input: List<String>): Int {
    var sum = 0
    for (string in input) {
        val firstHalf = HashSet<Char>()
        val secondHalf = HashSet<Char>()
        for (i in string.indices) {
            if (i < string.length / 2) {
                firstHalf.add(string[i])
            } else {
                secondHalf.add(string[i])
            }
        }
        firstHalf.retainAll(secondHalf)
        if (firstHalf.size > 1) {
            throw RuntimeException("Several types in both compartments")
        }
        for (i in firstHalf) {
            sum += priority(i)
        }
    }
    return sum
}

fun day3_2(input: List<String>): Int {
    var sum = 0
    for (i in input.indices step 3) {
        val first = HashSet<Char>(input[i].toList())
        val second = HashSet<Char>(input[i+1].toList())
        val third = HashSet<Char>(input[i+2].toList())
        first.retainAll(second)
        first.retainAll(third)
        if (first.size > 1) {
            throw RuntimeException("Several types in all three rucksacks")
        }
        for (j in first) {
            sum += priority(j)
        }
    }
    return sum
}
fun priority(c: Char): Int {
    return if (c.isUpperCase()) {
        c.code - 65 + 27;
    } else {
        c.code - 96;
    }
}
