import org.junit.jupiter.api.Test

class Day8 {
    val inputString = "30373\n" +
            "25512\n" +
            "65332\n" +
            "33549\n" +
            "35390"

    @Test
    fun day8_1() {
        assert(day8_1(inputString) == 21)
    }

    @Test
    fun day8_2() {
        assert(day8_2(inputString) == 8)
    }

}