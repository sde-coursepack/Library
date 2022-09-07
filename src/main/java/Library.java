import java.util.*;
import java.util.*;

public class Library {
    private Map<Book, Integer> bookCopies;
    private List<Patron> patrons;

    public Library(Map<Book, Integer> bookCopies, List<Patron> patrons) {
        this.bookCopies = bookCopies;
        this.patrons = patrons;
    }

    public Library() {
        this(new HashMap<>(), new ArrayList<>());
    }
}
