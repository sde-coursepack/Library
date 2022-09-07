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
}
