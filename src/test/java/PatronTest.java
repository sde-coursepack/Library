import org.junit.jupiter.api.*;

import java.util.*;

import static org.mockito.Mockito.*;

public class PatronTest {
    private Patron testPatron;
    private List<Book> mockList;

    private static final Book mistborn = new Book(3, "The Final Empire", "Brandon Sanderson");


    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setup() {
        mockList = mock(List.class);
        testPatron = new Patron(1, "Jane", "Doe", mockList);
    }

    @Test
    public void testCheckOutSuccessful() {
        //when you ask the mocklist if it contains Mistborn, it says "no"
        when(mockList.contains(mistborn)).thenReturn(false);

        //call the method addBookToCheckedOut(mistborn)
        testPatron.addBookToCheckedOut(mistborn);

        //verify that the mock list had add(mistborn) called on it
        verify(mockList).add(mistborn);
    }
}
