import org.junit.jupiter.api.Test

class Day12 {
    val input = "Sabqponm\n" +
            "abcryxxl\n" +
            "accszExk\n" +
            "acctuvwj\n" +
            "abdefghi\n"

    @Test
    fun day12_1() {
        assert(day12_1(input) == 31)
    }

    @Test
    fun day12_2() {
        assert(day12_2(input) == 29)
    }
}