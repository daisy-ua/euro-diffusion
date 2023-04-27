package eurodiffusion.source

import eurodiffusion.source.CityConstants.INITIAL_BALANCE
import eurodiffusion.source.CityConstants.REPRESENTATIVE_PORTION

class City(
    private val countryName: String,
    private val motifCount: Int,
) {
    private val neighbors: MutableList<City> = mutableListOf()

    private val balance: MutableMap<String, Int> = mutableMapOf()
    private val incomeBalance: MutableMap<String, Int> = mutableMapOf()

    init {
        balance[countryName] = INITIAL_BALANCE
    }

    val isCompleted: Boolean
        get() = balance.keys.size == motifCount

    fun addNeighbor(city: City) = neighbors.add(city)

    fun disseminateCoins() {
        for (motif in balance) {
            if (motif.value >= REPRESENTATIVE_PORTION) {
                val outcomeBalance = motif.value / REPRESENTATIVE_PORTION

                for (neighbor in neighbors) {
                    balance[motif.key] = balance[motif.key]!! - outcomeBalance
                    neighbor.addIncomeBalance(motif.key, outcomeBalance)
                }
            }
        }
    }

    fun summarizeIncomeBalance() {
        incomeBalance.keys.forEach { motif ->
            balance[motif] = (balance[motif] ?: 0) + incomeBalance[motif]!!
            incomeBalance[motif] = 0
        }
    }

    fun hasBorder(countryName: String): Boolean {
        for (neighbor in neighbors) {
            if (neighbor.countryName != countryName)
                return true
        }
        return false
    }

    private fun addIncomeBalance(motif: String, amount: Int) {
        incomeBalance[motif] = (incomeBalance[motif] ?: 0) + amount
    }
}

