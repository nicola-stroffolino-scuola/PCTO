package parking

class Lot {
    private var spots: Array<Car?> = arrayOfNulls(0)
    fun execute(input: String): String {
        val len = input.split(' ', limit = 3)

        val splitCmd = arrayOf(" ", " ", " ")
        for (i in len.indices) splitCmd[i] = len[i]
        val (command, args, color) = splitCmd

        return if (spots.isEmpty() && !(command == "create" || command == "exit"))
            "Sorry, a parking lot has not been created."
        else when (command) {
            "create" -> create(args.toInt())
            "park" -> park(args.uppercase(), color)
            "leave" -> leave(args.toInt())
            "status" -> status()
            "reg_by_color" -> regBy(args)
            "spot_by_color" -> spotBy("color", args)
            "spot_by_reg" -> spotBy("registration", args)
            "exit" -> "exiting"
            else -> "Command Error" // My Exception
        }
    }
    private fun create(maxSpots: Int): String {
        spots = arrayOfNulls(maxSpots)
        return "Created a parking lot with $maxSpots spots."
    }
    private fun park(registration: String, color: String): String {
        var spot = 0
        for (i in spots.indices) {
            if (spots[i] == null) {
                spot = i
                spots[i] = Car(registration, color, spot)
                break
            }
            if (i == spots.lastIndex) return "Sorry, the parking lot is full."
        }
        return "$color car parked in spot ${spot + 1}."
    }
    private fun leave(spot: Int): String {
        return when (spots[spot - 1]) {
            null -> "There is no car in spot $spot."
            is Car -> {
                spots[spot - 1] = null
                "Spot $spot is free."
            }
            else -> "Invalid Spot" // My Exception
        }
    }
    private fun status(): String {
        return if (spots.all { it == null }) "Parking lot is empty."
        else {
            var res = ""
            for (i in spots) {
                if (i != null) res += "${i.parkingSpot + 1} ${i.registration} ${i.color}\n"
            }
            res = res.dropLast(1)
            res
        }
    }
    private fun regBy(color: String): String {
        val filteredCars = spots.filter { it?.color?.lowercase() == color.lowercase() }
        return if (filteredCars.isEmpty()) "No cars with color $color were found."
        else filteredCars.joinToString (", " ) { "${it?.registration}" }
    }
    private fun spotBy(criteria: String, args: String): String {
        var crt = criteria
        val filteredCars = when (criteria) {
            "color" -> spots.filter { it?.color?.lowercase() == args.lowercase() }
            "registration" -> {
                crt += " number"
                spots.filter { it?.registration == args.uppercase() }
            }
            else -> null
        }
        return if (filteredCars.isNullOrEmpty()) "No cars with $crt $args were found."
        else {
            val filteredSpots = filteredCars.map { it?.parkingSpot }
            filteredSpots.joinToString ( ", " ) { "${it?.plus(1)}" }
        }
    }
}

class Car(val registration: String?, var color: String?, var parkingSpot: Int)

fun main() {
    val parking = Lot()

    while (true) {
        val feedback = parking.execute(readln())
        if (feedback == "exiting") break
        println(feedback)
    }
}
