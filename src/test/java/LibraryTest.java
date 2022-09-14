import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library testLibrary;
    private Map<Book, Integer> testBookCopies;
    private List<Patron> testPatronList;

    private Book gardensOfTheMoon;

    private Patron testPatron;
    private List<Book> testCheckOutList;

    @BeforeEach
    public void setupDefaultTestObjects() {
        testBookCopies = new HashMap<>();
        testPatronList = new ArrayList<>();
        testLibrary = new Library(testBookCopies, testPatronList);

        gardensOfTheMoon = new Book(1,"Gardens Of The Moon: Book 1 of Malazan Book of the Fallen", "Steven Erikson");

        testCheckOutList = new ArrayList<>();
        testPatron = new Patron(12, "John", "Smith", testCheckOutList);
    }
    
    @Test
    public void addBooksNewBooksTest() {
        testLibrary.addBooks(gardensOfTheMoon, 2); // add copies of a new book

        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book not added to Map");
        assertEquals(2, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies added");
    }

    @Test
    public void addBooksExistingBooksTest() {
        testBookCopies.put(gardensOfTheMoon, 2); // add existing book

        testLibrary.addBooks(gardensOfTheMoon, 2); // add new copies of existing book

        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book no longer in Map");
        assertEquals(4, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies after add");
    }

    @Test
    public void checkOutEquivalenceTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        List<Book> patronCheckedOut = new ArrayList<>();
        Patron testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
        Library testLibrary = new Library(testBookCopies, testPatronList);
        testBookCopies.put(gardensOfTheMoon, 2);
        testPatronList.add(testPatron);

        testLibrary.checkOut(testPatron, gardensOfTheMoon);
        assertEquals(1, testBookCopies.get(gardensOfTheMoon),
                "Library has wrong number of copies of test book");
        assertTrue(patronCheckedOut.contains(gardensOfTheMoon),
                "Patron doesn't have test book in their checked out list");
        assertEquals(1, patronCheckedOut.size(), "Patron doesn't have right number of books checked out");
    }

    @Test
    public void checkOutNoMoreCopiesTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        List<Book> patronCheckedOut = new ArrayList<>();
        Patron testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
        Library testLibrary = new Library(testBookCopies, testPatronList);
        testBookCopies.put(gardensOfTheMoon, 0);
        testPatronList.add(testPatron);

        assertThrows(RuntimeException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));


        assertEquals(0, testBookCopies.get(gardensOfTheMoon),
                "Library should till have no copies");
        assertFalse(patronCheckedOut.contains(gardensOfTheMoon),
                "Patron doesn't have test book in their checked out list");
        assertEquals(0, patronCheckedOut.size(),
                "Patron doesn't have right number of books checked out");
    }

    @Test
    public void checkOutLibraryDoesntHaveBookTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        List<Book> patronCheckedOut = new ArrayList<>();
        Patron testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
        Library testLibrary = new Library(testBookCopies, testPatronList);
        testPatronList.add(testPatron);

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));
        assertFalse(testBookCopies.containsKey(gardensOfTheMoon));
        assertFalse(patronCheckedOut.contains(gardensOfTheMoon),
                "Patron doesn't have test book in their checked out list");
        assertEquals(0, patronCheckedOut.size(),
                "Patron doesn't have right number of books checked out");
    }

    @Test
    public void checkOutInvalidPatronTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        List<Book> patronCheckedOut = new ArrayList<>();
        Patron testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
        Library testLibrary = new Library(testBookCopies, testPatronList);
        testBookCopies.put(gardensOfTheMoon, 2);

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));
        assertEquals(2, testBookCopies.get(gardensOfTheMoon),
                "Library has wrong number of copies of test book");
        assertFalse(patronCheckedOut.contains(gardensOfTheMoon),
                "Invalid Patron has test book in their checked out list");
        assertEquals(0, patronCheckedOut.size(), "Patron doesn't have right number of books checked out");

    }
}
