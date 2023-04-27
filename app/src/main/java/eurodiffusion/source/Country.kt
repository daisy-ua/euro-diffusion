package eurodiffusion.source

class Country(
    val name: String,
    val xl: Int,
    val yl: Int,
    val xh: Int,
    val yh: Int,
) {
    private var cities: MutableList<City> = mutableListOf()

    var daysToComplete: Int = 0
        private set

    private var isCompleted: Boolean = false

    fun isCompleted(days: Int): Boolean {
        if (isCompleted) return true

        if (cities.all { it.isCompleted }) {
            isCompleted = true
            daysToComplete = days
        }

        return isCompleted
    }

    fun addCity(city: City) = cities.add(city)
}