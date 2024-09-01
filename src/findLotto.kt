fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    val populationSize = 100
    val maxSteps = 1000
    val mutationRate = 0.1

    var population = List(populationSize) { Lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!! }
    var steps = 0

    while (steps < maxSteps) {
        steps++

        // Evaluate fitness
        val fitness = population.map { guess -> Lotto.checkGuess(guess) }

        // Check if we found the correct guess
        val maxFitness = fitness.maxOrNull() ?: 0
        if (maxFitness == Lotto.n) {
            val correctGuess = population[fitness.indexOf(maxFitness)]
            return Pair(steps, correctGuess)
        }

        // Selection
        val selected = population.zip(fitness).sortedByDescending { it.second }.take(populationSize / 2).map { it.first }

        // Crossover
        val newPopulation = mutableListOf<List<Int>>()
        for (i in 0 until populationSize / 2) {
            val parent1 = selected.random()
            val parent2 = selected.random()
            val crossoverPoint = (1 until Lotto.n).random()
            val child = parent1.take(crossoverPoint) + parent2.takeLast(Lotto.n - crossoverPoint)
            newPopulation.add(child)
        }

        // Mutation
        population = newPopulation.map { guess ->
            if (Math.random() < mutationRate) {
                val mutatedGuess = guess.toMutableList()
                val index = (0 until Lotto.n).random()
                mutatedGuess[index] = Lotto.lottoRange.random()
                mutatedGuess
            } else {
                guess
            }
        }
    }

    return Pair(steps, emptyList())
}