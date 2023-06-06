package eurodiffusion

import eurodiffusion.source.Country
import eurodiffusion.source.EuropeMap
import java.lang.Exception

fun main() {
    val inputCases: List<List<Country>>?

    try {
        inputCases = parseInputFile()
    } catch (e: Exception) {
        println(e.message)
        return
    }

    for ((index, case) in inputCases.withIndex()) {
        println("\nCase Number ${index + 1}")
        try {
            val map = EuropeMap(case)
            map.disseminateCoins()

            displayResults(map.countries)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

private fun displayResults(countries: List<Country>) {
    val sortedCountries = countries.sortedWith(compareBy({ it.daysToComplete }, { it.name }))
    for (country in sortedCountries) {
        println("${country.name} ${country.daysToComplete}")
    }
}