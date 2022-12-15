import org.junit.jupiter.api.Test

class Day14 {

    val input = "498,4 -> 498,6 -> 496,6\n" +
            "503,4 -> 502,4 -> 502,9 -> 494,9\n"

    @Test
    fun day14_1() {
        assert(day14_1(input) == 24)
    }

    @Test
    fun day14_2() {
        assert(day14_2(input) == 93)
    }

}