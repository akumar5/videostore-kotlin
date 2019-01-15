import java.util.*

class Customer(val name: String) {
    private val rentals = Vector<Rental>()

    fun addRental(rental: Rental) {
        rentals.addElement(rental)
    }

    fun statement(): String {
        var totalAmount = 0.0
        var frequentRenterPoints = 0
        val rentals = this.rentals.elements()
        var result = "Rental Record for $name\n"

        while (rentals.hasMoreElements()) {
            var thisAmount = 0.0
            val each = rentals.nextElement() as Rental

            // determines the amount for each line
            when (each.movie.priceCode) {
                Movie.REGULAR -> {
                    thisAmount += 2.0
                    if (each.daysRented > 2)
                        thisAmount += (each.daysRented - 2) * 1.5
                }
                Movie.NEW_RELEASE -> thisAmount += each.daysRented * 3
                Movie.CHILDRENS -> {
                    thisAmount += 1.5
                    if (each.daysRented > 3)
                        thisAmount += (each.daysRented - 3) * 1.5
                }
            }

            frequentRenterPoints++

            if (each.movie.priceCode == Movie.NEW_RELEASE && each.daysRented > 1)
                frequentRenterPoints++

            result += ("\t" + each.movie.title + "\t"
                    + thisAmount.toString() + "\n")
            totalAmount += thisAmount

        }

        result += "You owed " + totalAmount.toString() + "\n"
        result += "You earned " + frequentRenterPoints.toString() + " frequent renter points\n"


        return result
    }
}