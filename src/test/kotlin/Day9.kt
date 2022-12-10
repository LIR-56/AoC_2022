import org.junit.jupiter.api.Test

class Day9 {
    val testInput = "R 4\n" +
            "U 4\n" +
            "L 3\n" +
            "D 1\n" +
            "R 4\n" +
            "D 1\n" +
            "L 5\n" +
            "R 2\n"

    val testInput2 = "R 5\n" +
            "U 8\n" +
            "L 8\n" +
            "D 3\n" +
            "R 17\n" +
            "D 10\n" +
            "L 25\n" +
            "U 20\n"
    @Test
    fun day9_1() {
        assert(day9_1(testInput) == 13)
    }

    @Test
    fun day9_2() {
        assert(day9_2(testInput) == 1)
    }

    @Test
    fun day9_2full() {
        assert(day9_2(testInput2) == 36)
    }
}