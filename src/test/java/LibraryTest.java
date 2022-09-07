import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    @Test
    public void addBooksNewBooksTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> patronList = new ArrayList<>();
        Library testLibrary = new Library(testBookCopies, patronList);
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");

        testLibrary.addBooks(gardensOfTheMoon, 2);

        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book not added to Map");
        assertEquals(2, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies added");
    }

    @Test
    public void addBooksExistingBooksTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> patronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        Library testLibrary = new Library(testBookCopies, patronList);
        testBookCopies.put(gardensOfTheMoon, 2);

        testLibrary.addBooks(gardensOfTheMoon, 2);
        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book no longer in Map");
        assertEquals(4, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies after add");
    }

    @Test
    public void checkOutEquivalenceTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> patronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        List<Book> patronCheckedOut = new ArrayList<>();
        Patron testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
        Library testLibrary = new Library(testBookCopies, patronList);
        testBookCopies.put(gardensOfTheMoon, 2);
        patronList.add(testPatron);

        testLibrary.checkOut(testPatron, gardensOfTheMoon);
        assertEquals(1, testBookCopies.get(gardensOfTheMoon),
                "Library has wrong number of copies of test book");
        assertTrue(patronCheckedOut.contains(gardensOfTheMoon),
                "Patron doesn't have test book in their checked out list");
        assertEquals(1, patronCheckedOut.size(), "Patron doesn't have right number of books checked out");
    }
}
