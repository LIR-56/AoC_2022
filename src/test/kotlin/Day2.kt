import org.junit.jupiter.api.Test

class Day2 {

    val inputString = "A Y\n" +
            "B X\n" +
            "C Z\n"

    @Test
    fun test21() {
        val input = inputString.split("\n")
        assert(day2_1(input) == 15)
    }

    @Test
    fun test22() {
        val input = inputString.split("\n")
        assert(day2_2(input) == 12)
    }

}