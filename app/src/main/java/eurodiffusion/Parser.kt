package eurodiffusion

import eurodiffusion.source.Country
import eurodiffusion.source.EuropeMapConstants.GRID_SIZE
import eurodiffusion.source.EuropeMapConstants.MAX_COUNTRIES_QUANTITY
import eurodiffusion.source.EuropeMapConstants.MAX_COUNTRY_NAME_LENGTH
import java.io.File
import java.util.Scanner


fun parseInputFile(): List<List<Country>> {
    val path = System.getProperty("user.dir")
    val input = File("$path/app/src/main/java/eurodiffusion/data/input.txt")
    val scanner = Scanner(input)
    val inputCases = mutableListOf<List<Country>>()

    val checkCoordinatesRange = { pos: Int ->
        if (pos < 1 || pos > GRID_SIZE)
            throw Exception("Country coordinates are out of range")
    }

    while (true) {
        val countryCount = scanner.nextInt()

        if (countryCount == 0) break
        if (countryCount < 1 || countryCount > MAX_COUNTRIES_QUANTITY)
            throw Exception("Country count is out of range $countryCount")

        val countries = mutableListOf<Country>()
        for (i in 0 until countryCount) {
            val name = scanner.next()
            if (name.length > MAX_COUNTRY_NAME_LENGTH)
                throw Exception("Country name must be at most $MAX_COUNTRY_NAME_LENGTH characters")

            val xl = scanner.nextInt().also(checkCoordinatesRange)
            val yl = scanner.nextInt().also(checkCoordinatesRange)
            val xh = scanner.nextInt().also(checkCoordinatesRange)
            val yh = scanner.nextInt().also(checkCoordinatesRange)

            countries.add(Country(name, xl, yl, xh, yh))
        }
        inputCases.add(countries)
    }

    return inputCases
}
