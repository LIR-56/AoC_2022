import org.junit.jupiter.api.Test

class Day6 {
    val inputString1 = "bvwbjplbgvbhsrlpgdmjqwftvncz"
    val inputString2 = "nppdvjthqldpwncqszvftbrmjlhg"
    val inputString3 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
    val inputString4 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

    val inputString5 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    val inputString6 = "bvwbjplbgvbhsrlpgdmjqwftvncz"
    val inputString7 = "nppdvjthqldpwncqszvftbrmjlhg"
    val inputString8 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
    val inputString9 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

    @Test
    fun day6_1() {
        assert(day6_1(inputString1) == 5)
        assert(day6_1(inputString2) == 6)
        assert(day6_1(inputString3) == 10)
        assert(day6_1(inputString4) == 11)
    }

    @Test
    fun day6_w() {
        assert(day6_2(inputString5) == 19)
        assert(day6_2(inputString6) == 23)
        assert(day6_2(inputString7) == 23)
        assert(day6_2(inputString8) == 29)
        assert(day6_2(inputString9) == 26)
    }

}