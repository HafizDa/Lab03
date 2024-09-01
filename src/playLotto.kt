fun playLotto() {
    val lotto = Lotto()
    var continuePlaying = true

    while (continuePlaying) {
        val secretNumbers = Lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!!
        val userGuess = readNDistinct(Lotto.lottoRange.first, Lotto.lottoRange.last, Lotto.n)
        val correctCount = Lotto.checkGuess(userGuess, secretNumbers)

        println("lotto numbers: $secretNumbers, you got $correctCount correct")

        // For demonstration purposes, we use a static method to find computer guess
        val (steps, computerGuess) = findLotto(lotto)
        println("computer guess in $steps steps is $computerGuess")

        println("More? (Y/N): ")
        continuePlaying = readLine()?.trim()?.equals("Y", ignoreCase = true) == true
    }
}
