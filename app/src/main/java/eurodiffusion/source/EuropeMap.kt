package eurodiffusion.source

import eurodiffusion.source.EuropeMapConstants.GRID_SIZE

class EuropeMap(
    private val countries: List<Country>,
) {
    private val grid: Array<Array<City?>> = Array(GRID_SIZE + 2) { Array(GRID_SIZE + 2) { null } }

    init {
        for (country in countries) {
            setupCountry(country)
        }

        setupCityNeighbors()
    }

    fun printResult() {
        disseminateCoins()
        println("---------")
        val sortedCountries = countries.sortedWith(compareBy({ it.daysToComplete }, { it.name }))
        for (country in sortedCountries) {
            println("${country.name} ${country.daysToComplete}")
        }
    }

    fun disseminateCoins() {
        if (countries.size == 1) {
            return
        }

        var isCompleted = false
        var daysToComplete = 1

        while (!isCompleted) {
            for (x in 1..GRID_SIZE) {
                for (y in 1..GRID_SIZE) {
                    grid[x][y]?.disseminateCoins()
                }
            }

            for (x in 1..GRID_SIZE) {
                for (y in 1..GRID_SIZE) {
                    grid[x][y]?.summarizeIncomeBalance()
                }
            }

            isCompleted = true
            for (country in countries) {
                if (!country.isCompleted(daysToComplete)) {
                    isCompleted = false
                }
            }

            daysToComplete++
        }
    }

    private fun setupCountry(country: Country) {
        for (x in country.xl..country.xh) {
            for (y in country.yl..country.yh) {
                if (grid[x][y] != null) {
                    throw Exception("Countries intersect!")
                }

                grid[x][y] = City(country.name, countries.size).also {
                    country.addCity(it)
                }
            }
        }
    }

    private fun setupCityNeighbors() {
        for (i in 1..GRID_SIZE) {
            for (j in 1..GRID_SIZE) {
                grid[i][j]?.let { city ->
                    grid[i][j + 1]?.let {
                        city.addNeighbor(it)
                    }

                    grid[i + 1][j]?.let {
                        city.addNeighbor(it)
                    }

                    grid[i][j - 1]?.let {
                        city.addNeighbor(it)
                    }

                    grid[i - 1][j]?.let {
                        city.addNeighbor(it)
                    }
                }
            }
        }
    }
}