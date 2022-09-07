import java.util.*;
import java.util.*;

public class Library {
    public static final int MAX_BOOKS_PER_PATRON = 3;

    private Map<Book, Integer> bookCopies;
    private List<Patron> patrons;

    public Library(Map<Book, Integer> bookCopies, List<Patron> patrons) {
        this.bookCopies = bookCopies;
        this.patrons = patrons;
    }

    public Library() {
        this(new HashMap<>(), new ArrayList<>());
    }

    public boolean isPatron(Patron patron) {
        return patrons.contains(patron);
    }

    public void addPatron(Patron patron) {
        if (!isPatron(patron))
        patrons.add(patron);
    }

    public int getNumCopies(Book b) {
        return bookCopies.get(b);
    }

    public void addBooks(Book b, int copies) {

    }

}
