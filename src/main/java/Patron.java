import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final int id;
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

    public void removeCheckedOutBook(Book b) {
        if (!booksCheckedOut.contains(b)) {
            throw new IllegalArgumentException(
                    generateRemoveCheckedOutBookError(b));
        }
        booksCheckedOut.remove(b);
    }

    public String generateRemoveCheckedOutBookError(Book b) {
        return "Patron cannot return book they have not checked out!\n" +
                "\t" + this +
                "\tCheck out list: " + this.booksCheckedOut +
                "\t" + b;
    }

    public int getNumberOfBooksCheckedOut() {
        return booksCheckedOut.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patron patron = (Patron) o;

        return id == patron.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Has checked out " + getNumberOfBooksCheckedOut() + " books" +
                '}';
    }

    public boolean hasCheckedOut(Book b) {
        return booksCheckedOut.contains(b);
    }
}
