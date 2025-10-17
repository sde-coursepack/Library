import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
        when(mockList.contains(mistborn)).thenReturn(false);
        testPatron.addBookToCheckedOut(mistborn);
        verify(mockList, times(1)).add(mistborn);
    }

    @Test
    public void testCheckOutFailure_alreadyHaveBook_timesZero() {
        when(mockList.contains(mistborn)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () ->
                testPatron.addBookToCheckedOut(mistborn));
        verify(mockList, times(0)).add(any());
    }

    @Test
    public void testCheckOutFailure_alreadyHaveBook_noMoreInteractions() {
        when(mockList.contains(mistborn)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () ->
                testPatron.addBookToCheckedOut(mistborn));
        verify(mockList).contains(mistborn);
        // I include this to illustrate "no more interactions", but I would discourage its use. THis is because doing literally **anything** 
        // else with this object will cause a test failure, even if it doesn't actually change any post-conditions.
        verifyNoMoreInteractions(mockList); 
    }
}
