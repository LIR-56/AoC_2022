import org.junit.jupiter.api.Test

class Day13 {
    var inputString = "[1,1,3,1,1]\n" +
            "[1,1,5,1,1]\n" +
            "\n" +
            "[[1],[2,3,4]]\n" +
            "[[1],4]\n" +
            "\n" +
            "[9]\n" +
            "[[8,7,6]]\n" +
            "\n" +
            "[[4,4],4,4]\n" +
            "[[4,4],4,4,4]\n" +
            "\n" +
            "[7,7,7,7]\n" +
            "[7,7,7]\n" +
            "\n" +
            "[]\n" +
            "[3]\n" +
            "\n" +
            "[[[]]]\n" +
            "[[]]\n" +
            "\n" +
            "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
            "[1,[2,[3,[4,[5,6,0]]]],8,9]\n"

    @Test
    fun containerParsing() {
        assert(Container.of("[1,1,3,1,1]").toString() == "[1,1,3,1,1]")
        assert(Container.of("[1,1,5,1,1]").toString() == "[1,1,5,1,1]")
        assert(Container.of("[[1],[2,3,4]]").toString() == "[[1],[2,3,4]]")
        assert(Container.of("[[1],4]").toString() == "[[1],4]")
        assert(Container.of("[9]").toString() == "[9]")
        assert(Container.of("[[8,7,6]]").toString() == "[[8,7,6]]")
        assert(Container.of("[[4,4],4,4]").toString() == "[[4,4],4,4]")
        assert(Container.of("[[4,4],4,4,4]").toString() == "[[4,4],4,4,4]")
        assert(Container.of("[7,7,7,7]").toString() == "[7,7,7,7]")
        assert(Container.of("[7,7,7]").toString() == "[7,7,7]")
        assert(Container.of("[]").toString() == "[]")
        assert(Container.of("[3]").toString() == "[3]")
        assert(Container.of("[[[]]]").toString() == "[[[]]]")
        assert(Container.of("[[]]").toString() == "[[]]")
        assert(Container.of("[1,[2,[3,[4,[5,6,7]]]],8,9]").toString() == "[1,[2,[3,[4,[5,6,7]]]],8,9]")
        assert(Container.of("[1,[2,[3,[4,[5,6,0]]]],8]").toString() == "[1,[2,[3,[4,[5,6,0]]]],8]")
        val longString = "[[8,5,2,2,4],[[4,3],[[],[7,8]],[[10,1,4,3],[10],[3,6],[7,8,9],9]],[[6,[1,6,1]],[[4],[0,10,6,6,10],3,10],5,5],[3,6,[3,[9,0,9,0,7],9,8,[8,3,3,9]]]]"
        assert(Container.of(longString).toString() == longString)
    }

    @Test
    fun day13_1() {
        assert(day13_1(inputString) == 13)
    }

    @Test
    fun day13_2() {
        assert(day13_2(inputString) == 140)
    }
}