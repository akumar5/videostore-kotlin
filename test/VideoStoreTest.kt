import org.junit.Test
import kotlin.test.assertEquals

class VideoStoreTest {

    private var customer = Customer("Fred")

    @Test
    fun testSingleNewReleaseStatement() {
        rentForPeriod(Movie("The Cell", Movie.NEW_RELEASE), 3)
        assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n",
            generateStatement()
        )
    }

    @Test
    fun testDualNewReleaseStatement() {
        rentForPeriod(Movie("The Cell", Movie.NEW_RELEASE), 3)
        rentForPeriod(Movie("The Tigger Movie", Movie.NEW_RELEASE), 3)
        assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n",
            generateStatement()
        )
    }

    @Test
    fun testSingleChildrensStatement() {
        rentForPeriod(Movie("The Tigger Movie", Movie.CHILDRENS), 3)
        assertEquals(
            "Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n",
            generateStatement()
        )
    }

    @Test
    fun testMultipleRegularStatement() {
        rentForPeriod(Movie("Plan 9 from Outer Space", Movie.REGULAR), 1)
        rentForPeriod(Movie("8 1/2", Movie.REGULAR), 2)
        rentForPeriod(Movie("Eraserhead", Movie.REGULAR), 3)

        assertEquals(
            "Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
            generateStatement()
        )
    }

    private fun generateStatement() = customer.statement()

    private fun rentForPeriod(movie: Movie, daysRented: Int) {
        customer.addRental(Rental(movie, daysRented))
    }
}