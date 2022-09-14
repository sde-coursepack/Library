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

        gardensOfTheMoon = new Book(1, "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen", "Steven Erikson");

        testCheckOutList = new ArrayList<>();
        testPatron = new Patron(12, "John", "Smith", testCheckOutList);
    }

    @Test
    public void addBooksNewBooksTest() {
        testLibrary.addBooks(gardensOfTheMoon, 2); // add copies of a new book

        assertLibraryHasNCopiesOfBook(gardensOfTheMoon, 2);
    }

    @Test
    public void addBooksExistingBooksTest() {
        givenBookCopyEntryToTestLibraryMap(gardensOfTheMoon, 2); // add existing book

        testLibrary.addBooks(gardensOfTheMoon, 2); // add new copies of existing book

        assertLibraryHasNCopiesOfBook(gardensOfTheMoon, 4);
    }

    @Test
    public void checkOutEquivalenceTest() {
        givenBookCopyEntryToTestLibraryMap(gardensOfTheMoon, 2); // add copies of the book we check out
        givenPatronEnrolledInLibrary(testPatron); // enroll testPatron in Library

        testLibrary.checkOut(testPatron, gardensOfTheMoon);

        assertLibraryHasNCopiesOfBook(gardensOfTheMoon, 1);
        assertCheckOutListEquals(gardensOfTheMoon);
    }

    @Test
    public void checkOutNoMoreCopiesTest() {
        givenBookCopyEntryToTestLibraryMap(gardensOfTheMoon, 0); // library carries book, but no copies available
        givenPatronEnrolledInLibrary(testPatron); // enroll testPatron in Library

        assertThrows(RuntimeException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));


        assertLibraryHasNCopiesOfBook(gardensOfTheMoon, 0);
        assertEmptyCheckOutList();
    }

    @Test
    public void checkOutLibraryDoesntCarryBookTest() {
        givenPatronEnrolledInLibrary(testPatron); // enroll testPatron in Library

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));

        assertLibraryDoesNotCarryBook(gardensOfTheMoon);
        assertEmptyCheckOutList();
    }

    @Test
    public void checkOutInvalidPatronTest() {
        givenBookCopyEntryToTestLibraryMap(gardensOfTheMoon, 2);

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));

        assertLibraryHasNCopiesOfBook(gardensOfTheMoon, 2);
        assertCheckOutListEquals(); // empty checkout list
    }

    private void givenBookCopyEntryToTestLibraryMap(Book book, int value) {
        testBookCopies.put(book, value);
    }

    private void givenPatronEnrolledInLibrary(Patron testPatron) {
        testPatronList.add(testPatron);
    }

    private void assertLibraryHasNCopiesOfBook(Book book, int numberOfCopies) {
        assertTrue(testBookCopies.containsKey(book), "Test book not added to Map: " + book);
        assertEquals(numberOfCopies, testBookCopies.get(book), "Incorrect number of copies of " + book + " present");
    }

    private void assertCheckOutListEquals(Book... books) {
        List<Book> expected = new ArrayList<>(Arrays.asList(books));
        assertEquals(expected, testCheckOutList);
    }

    private void assertLibraryDoesNotCarryBook(Book book) {
        assertFalse(testBookCopies.containsKey(book));
    }

    private void assertEmptyCheckOutList() {
        assertTrue(testCheckOutList.isEmpty());
    }
}
