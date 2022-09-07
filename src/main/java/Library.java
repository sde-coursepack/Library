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
        if (bookCopies.containsKey(b)) {
            int currentCopies = bookCopies.get(b);
            bookCopies.put(b, currentCopies + copies);
        } else {
            bookCopies.put(b, copies);
        }
    }

    public void checkOut(Patron p, Book b) {
        int currentCopies = bookCopies.get(b);
        bookCopies.put(b, currentCopies - 1);
        p.addBookToCheckedOut(b);
    }

}
