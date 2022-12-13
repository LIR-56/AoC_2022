import java.util.*
import kotlin.collections.ArrayList

fun day12_1(input: String): Int {
    var start = Point(-1, -1, Int.MIN_VALUE)
    var end = Point(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
    val map = ArrayList<ArrayList<MapPiece>>()
    for (line in input.split("\n")) {
        if (line == "") continue
        map.add(ArrayList())
        val row = map[map.lastIndex]
        for (point in line.toCharArray()) {
            when (point) {
                'S' -> {
                    row.add(MapPiece(0, false))
                    start = Point(map.lastIndex, row.lastIndex, 0)
                }

                'E' -> {
                    row.add(MapPiece(25, false))
                    end = Point(map.lastIndex, row.lastIndex, 25)
                }

                else -> {
                    row.add(MapPiece(point.code - 'a'.code, false))
                }
            }
        }
    }
    val startNode = Node(start, 0, ArrayList())
    val searchQueue = LinkedList<Node>()
    searchQueue.add(startNode)
    return search(map, searchQueue, end)
}

fun day12_2(input: String): Int {
    val searchQueue = LinkedList<Node>()
    var end = Point(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
    val map = ArrayList<ArrayList<MapPiece>>()
    for (line in input.split("\n")) {
        if (line == "") continue
        map.add(ArrayList())
        val row = map[map.lastIndex]
        for (point in line.toCharArray()) {
            when (point) {
                'S', 'a' -> {
                    row.add(MapPiece(0, true))
                    searchQueue.add(Node(Point(map.lastIndex, row.lastIndex, 0), 0, ArrayList()))
                }

                'E' -> {
                    row.add(MapPiece(25, false))
                    end = Point(map.lastIndex, row.lastIndex, 25)
                }

                else -> {
                    row.add(MapPiece(point.code - 'a'.code, false))
                }
            }
        }
    }
    return search(map, searchQueue, end)
}

fun search(map: List<List<MapPiece>>, searchQueueInitialized: LinkedList<Node>, end: Point): Int {
    var node: Node
    while (searchQueueInitialized.size > 0) {
        node = searchQueueInitialized.pollFirst()
        val x = node.point.x
        val y = node.point.y
        if (x > 0) {
            val p = map[y][x - 1]
            if (!p.isVisited && node.point.height >= p.height - 1) {
                if (x - 1 == end.x && y == end.y) {
                    return node.layer + 1
                }
                p.isVisited = true
                val newNode = Node(Point(y, x - 1, p.height), node.layer + 1, ArrayList())
                node.whereToGo.add(newNode)
                searchQueueInitialized.add(newNode)
            }
        }

        if (y > 0) {
            val p = map[y - 1][x]
            if (!p.isVisited && node.point.height >= p.height - 1) {
                if (x == end.x && y - 1 == end.y) {
                    return node.layer + 1
                }
                p.isVisited = true
                val newNode = Node(Point(y - 1, x, p.height), node.layer + 1, ArrayList())
                node.whereToGo.add(newNode)
                searchQueueInitialized.add(newNode)
            }
        }

        if (y < map.lastIndex) {
            val p = map[y + 1][x]
            if (!p.isVisited && node.point.height >= p.height - 1) {
                if (x == end.x && y + 1 == end.y) {
                    return node.layer + 1
                }
                p.isVisited = true
                val newNode = Node(Point(y + 1, x, p.height), node.layer + 1, ArrayList())
                node.whereToGo.add(newNode)
                searchQueueInitialized.add(newNode)
            }
        }

        if (x < map[y].lastIndex) {
            val p = map[y][x + 1]
            if (!p.isVisited && node.point.height >= p.height - 1) {
                if (x + 1 == end.x && y == end.y) {
                    return node.layer + 1
                }
                p.isVisited = true
                val newNode = Node(Point(y, x + 1, p.height), node.layer + 1, ArrayList())
                node.whereToGo.add(newNode)
                searchQueueInitialized.add(newNode)
            }
        }
    }
    return Int.MAX_VALUE
}

class MapPiece(val height: Int, var isVisited: Boolean)

data class Node(val point: Point, val layer: Int, val whereToGo: ArrayList<Node>)

data class Point(val y: Int, val x: Int, val height: Int)