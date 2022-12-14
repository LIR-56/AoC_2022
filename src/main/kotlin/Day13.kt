import java.lang.Integer.min
import java.util.*
import kotlin.collections.ArrayList

fun day13_1(input: String): Int {
    val parsedPairs = parsePairs(input)
    var sum = 0
    for (i in parsedPairs.indices) {
        if (parsedPairs[i].first < parsedPairs[i].second) {
            sum += (i + 1)
        }
    }
    return sum
}

fun day13_2(input: String): Int {
    val parsed = ArrayList<Container>()
    for(s in input.split("\n")) {
        if (s == "") continue
        parsed.add(Container.of(s))
    }
    val x = Container.of("[[2]]")
    val y = Container.of("[[6]]")
    parsed.add(x)
    parsed.add(y)
    parsed.sort()
    return (parsed.indexOf(x) + 1) * (parsed.indexOf(y) + 1)
}

fun parsePairs(input: String): List<Pair<Container, Container>> {
    val pairs = input.split("\n\n")
    return pairs.stream()
        .map { pair: String ->
            val pairSplit = pair.split("\n")
            Pair(Container.of(pairSplit[0]), Container.of(pairSplit[1]))
        }.toList()
}

class Container() : Comparable<Container> {
    var array: ArrayList<Container>? = null
    var number: Int? = null

    constructor(number: Int) : this() {
        this.number = number
    }

    override fun compareTo(other: Container): Int {
        if (this.number != null && other.number != null) return this.number!!.compareTo(other.number!!)
        if (this.array != null && other.array != null) {
            for (i in 0 until  min(this.array!!.size, other.array!!.size)) {
                val compare = this.array!![i].compareTo(other.array!![i])
                if (compare != 0) return compare
            }
            return this.array!!.size - other.array!!.size
        }
        if (this.number != null && other.array != null) {
            val tmp = Container()
            tmp.array = ArrayList()
            tmp.array!!.add(this)
            return tmp.compareTo(other)
        }
        if (this.array != null && other.number != null) {
            val tmp = Container()
            tmp.array = ArrayList()
            tmp.array!!.add(other)
            return this.compareTo(tmp)
        }
        return 0
    }

    companion object {
        fun of(s: String): Container {
            val stack = Stack<Container>()
            val result = Container()
            var current = result
            current.array = ArrayList()
            var accum = StringBuilder()
            stack.add(current)
            for (c in s.removePrefix("[").toCharArray()) {
                if (c == '[') {
                    val newContainer = Container()
                    newContainer.array = ArrayList()
                    current.array!!.add(newContainer)
                    stack.add(newContainer)
                    current = newContainer
                } else if (c == ']') {
                    if (accum.isNotEmpty()) {
                        val new = Container(accum.toString().toInt())
                        current.array!!.add(new)
                        accum = StringBuilder()
                    }
                    stack.pop()
                    if (stack.isNotEmpty()) {
                        current = stack.peek()
                    }
                } else if (c == ',') {
                    if (accum.isNotEmpty()) {
                        val new = Container(accum.toString().toInt())
                        current.array!!.add(new)
                        accum = StringBuilder()
                    }
                } else {
                    accum.append(c)
                }
            }
            return result
        }
    }


    override fun toString(): String {
        return if (number != null) {
            return number.toString()
        } else if (array != null) {
            "[" + array!!.joinToString(",") + "]"
        } else ""
    }
}