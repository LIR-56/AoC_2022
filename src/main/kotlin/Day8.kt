fun day8_1(input: String): Int {
    val rows = input.split("\n")
    val map = ArrayList<List<Int>>()
    for (i in rows.indices) {
        if (rows[i] == "") {
            continue
        }
        map.add(rows[i].chunked(1).map { c -> c.toInt() })
    }
    var numberOfVisible = 0
    for (i in rows.indices) {
        for (j in rows[i].indices) {
            if (isVisible(map, i, j)) {
                numberOfVisible++
            }
        }
    }
    return numberOfVisible
}

fun day8_2(input: String): Int {
    val rows = input.split("\n")
    val map = ArrayList<List<Int>>()
    for (i in rows.indices) {
        if (rows[i] == "") {
            continue
        }
        map.add(rows[i].chunked(1).map { c -> c.toInt() })
    }
    var bestViewScore = 0
    for (i in rows.indices) {
        for (j in rows[i].indices) {
            val viewScores = scenicViewScores(map, i, j)
            if (viewScores > bestViewScore) {
                bestViewScore = viewScores
            }
        }
    }
    return bestViewScore
}

fun isVisible(map: ArrayList<List<Int>>, y: Int, x: Int): Boolean {
    val height = map[y][x]

    var visibility = true
    for (i in 0 until y) {
        if (map[i][x] >= height) {
            visibility = false
            break
        }
    }
    if (visibility) {
        return true
    }

    visibility = true
    for (i in y+1 until map.size) {
        if (map[i][x] >= height) {
            visibility = false
            break
        }
    }
    if (visibility) {
        return true
    }

    visibility = true
    for (i in 0 until x) {
        if (map[y][i] >= height) {
            visibility = false
            break
        }
    }
    if (visibility) {
        return true
    }

    visibility = true
    for (i in x+1 until map[y].size) {
        if (map[y][i] >= height) {
            visibility = false
            break
        }
    }
    return visibility
}

fun scenicViewScores(map: ArrayList<List<Int>>, y: Int, x: Int): Int {
    val height = map[y][x]
    var scenicScore = 1

    var distanceView = 0
    for (i in y-1 downTo 0) {
        distanceView++
        if (map[i][x] >= height) {
            break
        }
    }
    scenicScore *= distanceView

    distanceView = 0
    for (i in y+1 until map.size) {
        distanceView++
        if (map[i][x] >= height) {
            break
        }
    }
    scenicScore *= distanceView

    distanceView = 0
    for (i in x-1 downTo 0) {
        distanceView++
        if (map[y][i] >= height) {
            break
        }
    }
    scenicScore *= distanceView

    distanceView = 0
    for (i in x+1 until map[y].size) {
        distanceView++
        if (map[y][i] >= height) {
            break
        }
    }
    scenicScore *= distanceView

    return scenicScore
}