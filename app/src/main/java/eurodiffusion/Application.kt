package eurodiffusion

import eurodiffusion.source.Country
import eurodiffusion.source.EuropeMap

fun main() {

    val numberOfCountries = 3

    val country1 = Country("France", 1, 4, 4, 6)
    val country2 = Country("Spain", 3, 1, 6, 3)
    val country3 = Country("Portugal", 1, 1, 2, 2)

    EuropeMap(listOf(country1, country2, country3)).printResult()

//    val c2 = Country("Belgium", 1, 1, 2, 2)
//    val c = Country("Netherlands", 1, 3, 2, 4)
//    EuropeMap(listOf(c2, c)).printResult()

}