import java.lang.RuntimeException
import java.util.HashMap

fun day71(input: String) :Long {
    val commandsAndOutput = input.split("$ ")
    val tree = buildTree(commandsAndOutput)
    goAroundAndCount(tree)
    return goAroundAndFindAndSum(tree)
}

fun day72(input: String): Long {
    val commandsAndOutput = input.split("$ ")
    val tree = buildTree(commandsAndOutput)
    goAroundAndCount(tree)
    val freeSpace = 70_000_000 - tree.size
    val needToDelete = 30_000_000 - freeSpace
    return findSizeOfDirectoryToDelete(needToDelete, tree)
}

fun findSizeOfDirectoryToDelete(needToDelete: Long, tree: TreeNode): Long {
    var bestKnownSize = tree.size
    for (node in tree.children!!.values.filter { x -> x.isDir && x.size >= needToDelete}) {
        val directoryBestSize = findSizeOfDirectoryToDelete(needToDelete, node)
        if (directoryBestSize < bestKnownSize) {
            bestKnownSize = directoryBestSize
        }
    }
    return bestKnownSize
}

fun goAroundAndFindAndSum(tree: TreeNode): Long {
    var result = 0L
    for (node in tree.children!!.values) {
        if (node.isDir) {
            if (node.size < 100_000) {
                result += node.size
            }
            result += goAroundAndFindAndSum(node)
        }
    }
    return result
}

fun goAroundAndCount(tree: TreeNode): Long {
    var size = 0L
    for (node in tree.children!!.entries) {
        size += if (node.value.size != 0L) {
            node.value.size
        } else {
            goAroundAndCount(node.value)
        }
    }
    tree.size = size
    return size
}

fun buildTree(commandsAndOutput: List<String>):TreeNode{
    var currentPlace = TreeNode("enter", true, null, HashMap(), 0)
    currentPlace.children!!["/"] = TreeNode("/", true, currentPlace, HashMap(), 0)
    val upperThanRoot = currentPlace
    for (i in commandsAndOutput.indices) {
        val lines = commandsAndOutput[i].split("\n")
        val command = lines[0].split(" ")
        when (command[0]) {
            "cd" -> currentPlace = cd(currentPlace, command[1])
            "ls" -> currentPlace = ls(currentPlace, lines)
        }
    }
    return upperThanRoot
}

fun ls(currentPlace: TreeNode, lines: List<String>): TreeNode {
    for (line in lines) {
        if (line == "" || line == "ls") continue
        val splitLine = line.split(" ")
        when (splitLine[0]) {
            "dir" -> currentPlace.children?.put(splitLine[1], TreeNode(splitLine[1], true, currentPlace, HashMap<String, TreeNode>(), 0))
            else -> currentPlace.children?.put(splitLine[1], TreeNode(splitLine[1], false, currentPlace, null, splitLine[0].toLong()))
        }
    }
    return currentPlace
}

fun cd(currentPlace: TreeNode, where: String): TreeNode {
    return if (where == "..") {
        if (currentPlace.children == null) {
            throw RuntimeException("internal exception: folder should have children non-null")
        }
        currentPlace.size = currentPlace.children!!.entries.stream().mapToLong { x -> x.value.size}.sum()
        currentPlace.parent ?: throw RuntimeException("wrong command")
    } else {
        if (currentPlace.children == null || currentPlace.children!![where] == null) {
            throw RuntimeException("wrong command")
        } else {
            currentPlace.children!![where]!!
        }
    }
}

data class TreeNode(val name: String, val isDir: Boolean, val parent: TreeNode?, var children: HashMap<String, TreeNode>?, var size: Long) {
    override fun toString(): String {
        return "TreeNode(name='$name', isDir=$isDir, parent=$parent, size=$size, children=${children?.values?.map { x -> x.name }})"
    }
}