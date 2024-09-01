import kotlin.random.Random

class Lotto {
    companion object {
        val lottoRange = 1..40
        const val n = 7

        fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
            return if (n > range.count()) {
                null
            } else {
                range.shuffled().take(n)
            }
        }

        fun numDistinct(list: List<Int>): Int {
            return list.toSet().size
        }

        fun numCommon(list1: List<Int>, list2: List<Int>): Int {
            return list1.toSet().intersect(list2.toSet()).size
        }

        fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
            return guess.size == count && guess.toSet().size == count && guess.all { it in range }
        }

        fun checkGuess(guess: List<Int>, secret: List<Int> = pickNDistinct(lottoRange, n)!!): Int {
            return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
        }
    }
}
