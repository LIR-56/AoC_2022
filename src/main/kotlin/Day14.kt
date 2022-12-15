import java.lang.RuntimeException
import kotlin.math.max
import kotlin.math.min

fun day14_1(input: String): Int {
    val map = ArrayList<Array<Int>>()
    initMap(input, map)
    val lowestPoint = map.size - 2
    val startPoint = Pair(0, 500)
    val s = processOnMap(map, startPoint, lowestPoint)
    return s
}

fun day14_2(input: String): Int {
    val map = ArrayList<Array<Int>>()
    initMap(input, map)
    map[map.size-2] = Array(1000) { _ -> 1 }
    val startPoint = Pair(0, 500)
    return process2OnMap(map, startPoint)
}

fun process2OnMap(map: ArrayList<Array<Int>>, startPoint: Pair<Int, Int>): Int {
    var sandUnitsRest = 0
    while (true) {
        if (map[startPoint.first][startPoint.second] == 1) {
            return sandUnitsRest
        }
        var falling = true
        val newSandBlockCoordinates = Point2(startPoint.first, startPoint.second)
        while (falling) {
            if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y] == 0) {
                newSandBlockCoordinates.x++
            } else if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y - 1] == 0) {
                newSandBlockCoordinates.x++
                newSandBlockCoordinates.y--
            } else if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y + 1] == 0) {
                newSandBlockCoordinates.x++
                newSandBlockCoordinates.y++
            } else falling = false
        }
        map[newSandBlockCoordinates.x][newSandBlockCoordinates.y] = 1
        sandUnitsRest++
    }
}

fun processOnMap(map: ArrayList<Array<Int>>, startPoint: Pair<Int, Int>, lowestPoint: Int): Int {
    var sandUnitsRest = 0
    while (true) {
        var falling = true
        val newSandBlockCoordinates = Point2(startPoint.first, startPoint.second)
        while (falling) {
            if (newSandBlockCoordinates.x > lowestPoint) {
                return sandUnitsRest
            }
            if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y] == 0) {
                newSandBlockCoordinates.x++
            } else if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y - 1] == 0) {
                newSandBlockCoordinates.x++
                newSandBlockCoordinates.y--
            } else if (map[newSandBlockCoordinates.x + 1][newSandBlockCoordinates.y + 1] == 0) {
                newSandBlockCoordinates.x++
                newSandBlockCoordinates.y++
            } else falling = false
        }
        map[newSandBlockCoordinates.x][newSandBlockCoordinates.y] = 1
        sandUnitsRest++
    }
}


private fun initMap(input: String, map: ArrayList<Array<Int>>) {
    for (s in input.split("\n")) {
        if (s == "") {
            continue
        }
        val coordinates = s.split(" -> ").map { c ->
            val split = c.split(",")
            Pair(split[0].toInt(), split[1].toInt())
        }
        enhanceMap(coordinates[0].second, map)
        map[coordinates[0].second][coordinates[0].first] = 1
        for (i in 1 until coordinates.size) {
            if (coordinates[i - 1].second == coordinates[i].second) {
                val x = coordinates[i].second
                for (y in min(coordinates[i - 1].first, coordinates[i].first)..max(
                    coordinates[i - 1].first,
                    coordinates[i].first
                )) {
                    map[x][y] = 1
                }
            } else if (coordinates[i - 1].first == coordinates[i].first) {
                enhanceMap(max(coordinates[i - 1].second, coordinates[i].second), map)
                val y = coordinates[i].first
                for (x in min(coordinates[i - 1].second, coordinates[i].second)..max(
                    coordinates[i - 1].second,
                    coordinates[i].second
                )) {
                    map[x][y] = 1
                }
            } else throw RuntimeException("One of the coordinates should stay the same")
        }
    }
    enhanceMap(map.size + 2, map)
    return
}

private fun enhanceMap(
    rowNumber: Int,
    map: ArrayList<Array<Int>>
) {
    val toAdd = rowNumber - map.size
    if (toAdd >= 0) {
        for (i in 0..toAdd) {
            map.add(Array(1000) { _ -> 0 })
        }
    }
}

data class Point2(var x: Int, var y: Int)