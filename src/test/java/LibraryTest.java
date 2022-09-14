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
    public void testAddBooksForBookNotInLibrary() {
        testLibrary.addBooks(gardensOfTheMoon, 2);

        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 2);
    }

    @Test
    public void testAddBooksForBookAlreadyInLibrary() {
        givenBookCopyEntryInTestLibraryMap(gardensOfTheMoon, 2);

        testLibrary.addBooks(gardensOfTheMoon, 2);

        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 4);
    }

    @Test
    public void testCheckOutEquivalence() {
        givenBookCopyEntryInTestLibraryMap(gardensOfTheMoon, 2);
        givenPatronEnrolledInTestLibrary(testPatron);

        testLibrary.checkOut(testPatron, gardensOfTheMoon);

        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 1);
        assertCheckOutListEqualsListOf(testCheckOutList, gardensOfTheMoon);
    }

    @Test
    public void testCheckOutLibraryHasNoMoreCopies() {
        givenBookCopyEntryInTestLibraryMap(gardensOfTheMoon, 0);
        givenPatronEnrolledInTestLibrary(testPatron);

        assertThrows(RuntimeException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));


        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 0);
        assertTestCheckOutListIsEmpty(testCheckOutList);
    }

    @Test
    public void testCheckOutLibraryDoesNotCarryBook() {
        givenPatronEnrolledInTestLibrary(testPatron);

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));

        assertTestLibraryDoesNotCarryBook(gardensOfTheMoon);
        assertTestCheckOutListIsEmpty(testCheckOutList);
    }

    @Test
    public void testCheckOutInvalidPatron() {
        givenBookCopyEntryInTestLibraryMap(gardensOfTheMoon, 2);

        assertThrows(IllegalArgumentException.class, () ->
                testLibrary.checkOut(testPatron, gardensOfTheMoon));

        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 2);
        assertTestCheckOutListIsEmpty(testCheckOutList);
    }

    @Test
    public void testCheckOutWhenPatronAlreadyHasACopyTest() {
        givenBookCopyEntryInTestLibraryMap(gardensOfTheMoon, 2);
        givenPatronEnrolledInTestLibrary(testPatron);
        givenCheckOutListContainsBooks(testCheckOutList, gardensOfTheMoon);

        assertThrows(RuntimeException.class, () ->
            testLibrary.checkOut(testPatron, gardensOfTheMoon));

        assertTestLibraryHasNCopiesOfBook(gardensOfTheMoon, 2);
        assertCheckOutListEqualsListOf(testCheckOutList, gardensOfTheMoon);
    }



    private void givenBookCopyEntryInTestLibraryMap(Book book, int value) {
        testBookCopies.put(book, value);
    }

    private void givenPatronEnrolledInTestLibrary(Patron testPatron) {
        testPatronList.add(testPatron);
    }

    private void givenCheckOutListContainsBooks(List<Book> testCheckOutList, Book...books) {
        testCheckOutList.addAll(convertArrayToArrayList(books));
    }

    private void assertTestLibraryHasNCopiesOfBook(Book book, int numberOfCopies) {
        assertTrue(testBookCopies.containsKey(book), "Test book not added to Map: " + book);
        assertEquals(numberOfCopies, testBookCopies.get(book), "Incorrect number of copies of " + book + " present");
    }

    private void assertTestLibraryDoesNotCarryBook(Book book) {
        assertFalse(testBookCopies.containsKey(book), "Library should not be carrying " + book);
    }

    private void assertCheckOutListEqualsListOf(List<Book> testCheckOutList, Book... books) {
        List<Book> expected = convertArrayToArrayList(books);
        assertEquals(expected, testCheckOutList, "Check Out List is not as expected.");
    }

    private void assertTestCheckOutListIsEmpty(List<Book> testCheckOutList) {
        assertTrue(testCheckOutList.isEmpty());
    }


    private ArrayList<Book> convertArrayToArrayList(Book[] bookArray) {
        List<Book> bookList = Arrays.asList(bookArray);
        return new ArrayList<>(bookList);
    }
}
