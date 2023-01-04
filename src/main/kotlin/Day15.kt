import java.lang.RuntimeException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun day15_1(input: String, row: Int): Int {
    val inputPoints = parse(input)
    var ranges = project(inputPoints, row)
    ranges = intersect(ranges)
    var sum = 0
    for (r in ranges) {
        sum += r.second - r.first + 1
    }
    val beaconsInRow = inputPoints.filter { p -> p.beacon.second == row }.map { p -> p.beacon.first }.toSet()
    return sum - beaconsInRow.size
}

fun day15_2(input: String, limit: Int): Long {
    val inputPoints = parse(input)
    var y = Int.MIN_VALUE
    var finalRanges: List<Pair<Int, Int>>? = null
    for (i in 0..limit) {
        var ranges = project(inputPoints, i)
        ranges = ranges.filter { p -> p.first <= limit && p.second >= 0 }.toList()
        ranges = intersect(ranges)
        var sum = 0
        for (j in ranges) {
            //if (j.first > limit || j.second < 0) continue
            sum += min(j.second, limit) - max( j.first, 0) + 1
        }
        if (sum == limit) {
            y = i
            finalRanges = ranges
            break
        }
    }
    if (finalRanges == null) {
        throw RuntimeException("AAAAA")
    }
    finalRanges = finalRanges.map { p -> Pair(max(p.first, 0), min(p.second,limit)) }.toList()
    if (finalRanges.size == 1) {
        return if (finalRanges[0].first == 0) {
            limit.toLong() * 4_000_000L + y
        } else {
            y.toLong()
        }
    } else if (finalRanges.size == 2) {
        return (finalRanges[0].second+1).toLong() * 4_000_000L + y
    } else {
        throw RuntimeException("Something goes wrong, size of resulting array = " + finalRanges.size.toString())
    }
}

fun intersect(input: List<Pair<Int, Int>>) : List<Pair<Int, Int>> {
    val ranges2 = input.sortedBy { x -> x.first }
    var i = 0
    val result = ArrayList<Pair<Int, Int>>()
    result.add(ranges2[0])
    for (j in 1 until ranges2.size) {
        val a = result[i]
        val b = ranges2[j]
        if (a.second < b.first) {
            //independent
            result.add(b)
            i++
        } else {
            result[i] = Pair(a.first, max(a.second, b.second))
        }
    }
    return result
}

fun project(inputPoints: ArrayList<SbPair>, row: Int): List<Pair<Int, Int>> {
    val result = ArrayList<Pair<Int, Int>>()
    for (p in inputPoints) {
        val radius = p.distance - abs(p.sensor.second - row)
        if (radius < 0) {
            continue
        }
        result.add(Pair(p.sensor.first - radius, p.sensor.first + radius))
    }
    return result
}

fun parse(input: String) : ArrayList<SbPair> {
    val result = ArrayList<SbPair>()
    for (s in input.split("\n")) {
        if (s == "") {
            continue
        }
        val s2 = s.split(": ")
        val s21 = s2[0].removePrefix("Sensor at x=").split(", y=")
        val sensor = Pair(s21[0].toInt(), s21[1].toInt())
        val s22 = s2[1].removePrefix("closest beacon is at x=").split(", y=")
        val beacon = Pair(s22[0].toInt(), s22[1].toInt())
        result.add(SbPair(sensor, beacon))
    }
    return result
}

class SbPair(sensor: Pair<Int, Int>, beacon: Pair<Int, Int>) {
    val sensor: Pair<Int, Int>
    val beacon: Pair<Int, Int>
    val distance: Int
    init {
        this.sensor = sensor
        this.beacon = beacon
        this.distance = abs(this.sensor.first - this.beacon.first) + abs(this.sensor.second - this.beacon.second)
    }
}