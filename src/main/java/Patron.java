import java.util.ArrayList;
import java.util.List;

public class Patron {
    private int id;
    private String firstName, lastName;
    private List<Book> booksCheckedOut;

    public Patron(int id, String firstName, String lastName, List<Book> booksCheckedOut) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksCheckedOut = booksCheckedOut;
    }

    public Patron(int id, String firstName, String lastName) {
        this(id, firstName, lastName, new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public void setBooksCheckedOut(List<Book> booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    public void addBookToCheckedOut(Book b) {
        booksCheckedOut.add(b);
    }

    public int getNumberOfBooksCheckedOut() {
        return booksCheckedOut.size();
    }
}
