import org.junit.jupiter.api.Test

class Day4 {
    val inputString ="2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8"

    @Test
    fun test41() {
        val input = inputString.split("\n")
        assert(day4_1(input) == 2)
    }

    @Test
    fun test42() {
        val input = inputString.split("\n")
        assert(day4_2(input) == 4)
    }
}