import java.lang.RuntimeException

fun day2_1(input: List<String>): Int {
    var result = 0
    for (i in input) {
        if (i == "") continue
        var rowSum = 0
        val string = i.split(" ")
        rowSum += scoresForShape(string)
        rowSum += scoresForOutcome(string)
        result += rowSum
    }
    return result
}

private fun scoresForShape(string: List<String>): Int {
    return when (string[1]) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> throw RuntimeException("Wrong letter in string")
    }
}

private fun scoresForOutcome(string: List<String>): Int {
    return when (string[0]) {
        "A" -> when (string[1]) {
            "X" -> 3
            "Y" -> 6
            "Z" -> 0
            else -> throw RuntimeException("Wrong letter in string")
        }
        "B" -> when (string[1]) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> throw RuntimeException("Wrong letter in string")
        }
        "C" -> when (string[1]) {
            "X" -> 6
            "Y" -> 0
            "Z" -> 3
            else -> throw RuntimeException("Wrong letter in string")
        }
        else -> throw RuntimeException("Wrong letter in string")
    }
}

fun day2_2(input: List<String>): Int {
    var result = 0
    for (i in input) {
        if (i == "") continue
        var rowSum = 0
        val string = i.split(" ")
        rowSum += scoresForShape2(string)
        rowSum += scoresForOutcome2(string)
        result += rowSum
    }
    return result
}

private fun scoresForOutcome2(string: List<String>): Int {
    return when (string[1]) {
        "X" -> 0
        "Y" -> 3
        "Z" -> 6
        else -> throw RuntimeException("Wrong letter in string")
    }
}

private fun scoresForShape2(string: List<String>): Int {
    return when (string[0]) {
        "A" -> when (string[1]) {
            "X" -> 3
            "Y" -> 1
            "Z" -> 2
            else -> throw RuntimeException("Wrong letter in string")
        }
        "B" -> when (string[1]) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> throw RuntimeException("Wrong letter in string")
        }
        "C" -> when (string[1]) {
            "X" -> 2
            "Y" -> 3
            "Z" -> 1
            else -> throw RuntimeException("Wrong letter in string")
        }
        else -> throw RuntimeException("Wrong letter in string")
    }
}