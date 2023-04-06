package machine

interface Recipe {
    val waterPerCup: Int
    val milkPerCup: Int
    val beansPerCup: Int
    val price: Int
}

class CoffeeMachine(waterSupply: Int = 0, milkSupply: Int = 0, beanSupply: Int = 0,
                    disposableCups: Int = 0, money: Int = 0) {
    private val supply = Supply(waterSupply, milkSupply, beanSupply, disposableCups, money)
    private lateinit var cup: Recipe

    fun status() = """
        The coffee machine has:
        ${supply.water} ml of water
        ${supply.milk} ml of milk
        ${supply.beans} g of coffee beans
        ${supply.disposableCups} disposable cups
        $${supply.money} of money
    """.trimIndent()

    fun canProduce(cups: Int): ProductionOutcome {
        val quantity = listOf(
            supply.water / cup.waterPerCup,
            supply.milk / cup.milkPerCup,
            supply.beans / cup.beansPerCup
        ).minOrNull() ?: 0
        return ProductionOutcome(quantity, quantity >= cups, quantity - cups)
    }

    fun buy(coffeeOption: Int): String {
        cup = when(coffeeOption){
            1 -> Espresso()
            2 -> Latte()
            3 -> Cappuccino()
            else -> return ""
        }

        val issues = mutableListOf<String>()
        if (supply.disposableCups == 0) issues += "disposable cups"
        if (supply.water < cup.waterPerCup) issues += "water"
        if (supply.milk < cup.milkPerCup) issues += "milk"
        if (supply.beans < cup.beansPerCup) issues += "coffee beans"

        if (issues.isNotEmpty()) return "Sorry, not enough ${issues.joinToString(", ")}!"

        supply.water -= cup.waterPerCup
        supply.milk -= cup.milkPerCup
        supply.beans -= cup.beansPerCup
        supply.disposableCups -= 1
        supply.money += cup.price
        return "I have enough resources, making you a coffee!"
    }
    fun take() = "I gave you $" + "".run {
        val tmp = supply.money
        supply.money = 0
        tmp
    }
    fun fill(waterRefill: Int = 0, milkRefill: Int = 0, beanRestock: Int = 0, cupRestock: Int = 0) = supply.run {
        water += waterRefill
        milk += milkRefill
        beans += beanRestock
        disposableCups += cupRestock
    }

    data class Espresso(
        override val waterPerCup: Int = 250,
        override val milkPerCup: Int = 0,
        override val beansPerCup: Int = 16,
        override val price: Int = 4
    ) : Recipe
    data class Latte(
        override val waterPerCup: Int = 350,
        override val milkPerCup: Int = 75,
        override val beansPerCup: Int = 20,
        override val price: Int = 7
    ) : Recipe
    data class Cappuccino(
        override val waterPerCup: Int = 200,
        override val milkPerCup: Int = 100,
        override val beansPerCup: Int = 12,
        override val price: Int = 6
    ) : Recipe

    data class Supply(var water: Int, var milk: Int, var beans: Int, var disposableCups: Int, var money: Int)
    data class ProductionOutcome(var quantity: Int, var canBePrepared: Boolean, var overflow: Int)
}

fun main() {
    val machine1 = CoffeeMachine(400, 540, 120, 9, 550)

    while (true) {
        println()
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                val choice = readln()
                if (choice == "back") continue
                println(machine1.buy(choice.toInt()))
            }
            "fill" -> machine1.fill(
                "".run {
                    println("Write how many ml of water you want to add:")
                    readln().toInt()
                },
                "".run {
                    println("Write how many ml of milk you want to add:")
                    readln().toInt()
                },
                "".run {
                    println("Write how many grams of coffee beans you want to add:")
                    readln().toInt()
                },
                "".run {
                    println("Write how many disposable cups you want to add:")
                    readln().toInt()
                }
            )
            "take" -> println(machine1.take())
            "remaining" -> println(machine1.status())
            "exit" -> break
        }
    }
}

