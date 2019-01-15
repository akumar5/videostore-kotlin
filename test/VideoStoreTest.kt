import junit.framework.TestCase

class VideoStoreTest(name: String) : TestCase(name) {

    private var customer: Customer? = null

    override fun setUp() {
        customer = Customer("Fred")
    }

    fun testSingleNewReleaseStatement() {
        customer!!.addRental(Rental(Movie("The Cell", Movie.NEW_RELEASE), 3))
        assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n",
            customer!!.statement()
        )
    }

    fun testDualNewReleaseStatement() {
        customer!!.addRental(Rental(Movie("The Cell", Movie.NEW_RELEASE), 3))
        customer!!.addRental(Rental(Movie("The Tigger Movie", Movie.NEW_RELEASE), 3))
        assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n",
            customer!!.statement()
        )
    }

    fun testSingleChildrensStatement() {
        customer!!.addRental(Rental(Movie("The Tigger Movie", Movie.CHILDRENS), 3))
        assertEquals(
            "Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n",
            customer!!.statement()
        )
    }

    fun testMultipleRegularStatement() {
        customer!!.addRental(Rental(Movie("Plan 9 from Outer Space", Movie.REGULAR), 1))
        customer!!.addRental(Rental(Movie("8 1/2", Movie.REGULAR), 2))
        customer!!.addRental(Rental(Movie("Eraserhead", Movie.REGULAR), 3))

        assertEquals(
            "Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
            customer!!.statement()
        )
    }
}