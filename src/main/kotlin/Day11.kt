import java.util.*
import kotlin.collections.ArrayList

fun day11_1(input: String): Int {
    val monkeysInput = input.split("\n\n")
    val monkeys = parseMonkeys(monkeysInput)
    for (i in 1..20) {
        for (m in monkeys) {
            val itemsAmount = m.items.size
            for (j in 1..itemsAmount) {
                val inspected = m.inspect()
                monkeys[inspected.first].items.push(inspected.second)
            }
        }
    }
    monkeys.sortBy { x -> x.inspected * -1 }
    return (monkeys[0].inspected * monkeys[1].inspected).toInt()
}

fun day11_2(input: String): Long {
    val monkeysInput = input.split("\n\n")
    val monkeys = parseMonkeys(monkeysInput, true)
    for (i in 1..10_000) {
        for (m in monkeys) {
            val itemsAmount = m.items.size
            for (j in 1..itemsAmount) {
                val inspected = m.inspectWithoutRelief()
                monkeys[inspected.first].items.push(inspected.second)
            }
        }
    }
    monkeys.sortBy { x -> x.inspected * -1 }
    return monkeys[0].inspected * monkeys[1].inspected
}

private fun parseMonkeys(monkeysInput: List<String>, overwriteDevider: Boolean = false): ArrayList<Monkey> {
    val monkeys = ArrayList<Monkey>()
    var devider = 1
    for (m in monkeysInput) {
        val mSplit = m.split("\n")
        val name = mSplit[0].removeSuffix(":")
        val monkey = Monkey()
        for (item in mSplit[1].removePrefix("  Starting items: ").split(", ")) {
            monkey.items.add(Item(item.toLong()))
        }
        val op = mSplit[2].removePrefix("  Operation: new = ")
        val opSplit = op.split(" ")
        assert(opSplit[0] == "old")
        monkey.operation = when (opSplit[1]) {
            "*" -> when (opSplit[2]) {
                "old" -> { x -> x * x }
                else -> { x -> x * opSplit[2].toInt() }
            }

            "+" -> when (opSplit[2]) {
                "old" -> { x -> x + x }
                else -> { x -> x + opSplit[2].toInt() }
            }

            else -> throw RuntimeException()
        }
        val divisibleBy = mSplit[3].removePrefix("  Test: divisible by ").toInt()
        devider *= divisibleBy
        val throwToIfTrue = mSplit[4].removePrefix("    If true: throw to monkey ").toInt()
        val throwToIfFalse = mSplit[5].removePrefix("    If false: throw to monkey ").toInt()
        monkey.test = { x -> if (x % divisibleBy == 0L) throwToIfTrue else throwToIfFalse }
        monkeys.add(monkey)
    }
    if (overwriteDevider) {
        for (monkey in monkeys) {
            monkey.devider = devider
        }
    }
    return monkeys
}


class Monkey {
    var items: LinkedList<Item> = LinkedList()
    var operation: (Long) -> Long = { x: Long -> x }
    var test: (Long) -> Int = { x: Long -> x.toInt() }
    var inspected = 0L
    var devider = 3

    fun inspect(): Pair<Int, Item> {
        val item = items.poll()
        item.worryLevel = this.operation(item.worryLevel)
        item.worryLevel = item.worryLevel / 3
        val nextMonkey = this.test(item.worryLevel)
        this.inspected++
        return Pair(nextMonkey, item)
    }

    fun inspectWithoutRelief(): Pair<Int, Item> {
        val item = items.poll()
        item.worryLevel = this.operation(item.worryLevel)
        item.worryLevel = item.worryLevel % devider
        val nextMonkey = this.test(item.worryLevel)
        this.inspected++
        return Pair(nextMonkey, item)
    }
}

class Item(var worryLevel: Long) {
    override fun toString(): String {
        return "Item($worryLevel)"
    }
}