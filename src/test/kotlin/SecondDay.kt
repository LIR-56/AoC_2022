import org.junit.jupiter.api.Test

class SecondDay {

    val inputString = "A Y\n" +
            "B X\n" +
            "C Z\n"

    @Test
    fun test21() {
        val input = inputString.split("\n")
        assert(secondDay1(input) == 15)
    }

    @Test
    fun test22() {
        val input = inputString.split("\n")
        assert(secondDay2(input) == 12)
    }

}