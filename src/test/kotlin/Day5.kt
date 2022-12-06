import org.junit.jupiter.api.Test

class Day5 {
    var inputString = "    [D]    \n" +
            "[N] [C]    \n" +
            "[Z] [M] [P]\n" +
            " 1   2   3 \n" +
            "\n" +
            "move 1 from 2 to 1\n" +
            "move 3 from 1 to 3\n" +
            "move 2 from 2 to 1\n" +
            "move 1 from 1 to 2"

    @Test
    fun day5_1 () {
        assert (day5(inputString) == "CMZ")
    }

    @Test
    fun day5_2 () {
        assert (day5(inputString, true) == "MCD")
    }
}