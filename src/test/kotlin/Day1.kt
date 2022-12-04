import org.junit.jupiter.api.Test

class Day1 {

    val inputString = "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000"

    @Test
    fun test11() {

        val input = inputString.split("\n")
        assert(day1(input, 1) == 24000)
    }

    @Test
    fun test12() {
        val input = inputString.split("\n")
        assert(day1(input, 3) == 45000)
    }
}