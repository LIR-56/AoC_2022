import org.junit.jupiter.api.Test

class Day3 {
    val inputString = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw"

    @Test
    fun test31() {
        val input = inputString.split("\n")
        assert(day3_1(input) == 157)
    }

    @Test
    fun test32() {
        val input = inputString.split("\n")
        assert(day3_2(input) == 70)
    }
}