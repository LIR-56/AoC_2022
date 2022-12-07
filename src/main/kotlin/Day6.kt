import java.lang.RuntimeException

fun day6_1 (input: String): Int {
    return findStartOfPacketMarker(input) + 1
}

fun day6_2 (input: String): Int {
    return findStartOfMessage(input) + 1
}

fun findStartOfPacketMarker(s: String): Int {
    for (i in 3 until s.length) {
        val set = HashSet<Char>()
        set.add(s[i])
        set.add(s[i-1])
        set.add(s[i-2])
        set.add(s[i-3])
        if (set.size == 4) {
            return i
        }
    }
    throw RuntimeException("FAILED")
}

fun findStartOfMessage(s: String): Int {
    for (i in 13 until s.length) {
        val set = HashSet<Char>()
        for (j in 0..13) {
            set.add(s[i-j])
        }
        if (set.size == 14) {
            return i
        }
    }
    throw RuntimeException("FAILED")
}