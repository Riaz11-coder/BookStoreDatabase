package dao;

import Reports.ExtentReportExtension;
import dao.impl.BookDAOImpl;
import model.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ExtentReportExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookDAOTest {
    private BookDAO bookDAO;

    @BeforeEach
    public void beforeClass(){
        ExtentReportExtension.getExtentTest().info("Test Reports");
    }

    @BeforeAll
    public void setup() {
        bookDAO = new BookDAOImpl();
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Sample Book");
        book.setSubTitle("A Sample Subtitle");
        book.setAuthor("John Doe");
        book.setPublish_date("2023-01-01");
        book.setPublisher("Sample Publisher");
        book.setPages(300);
        book.setDescription("A sample book for testing.");
        book.setWebsite("http://example.com");

        bookDAO.save(book);
        assertNotNull(book.getIsbn());
    }

    @Test
    public void testFindBookById() {
        Book book = bookDAO.findById("1234567890");
        assertNotNull(book);
        assertEquals("Sample Book", book.getTitle());
    }

    @Test
    public void testFindAllBooks() {
        List<Book> books = bookDAO.findAll();
        assertFalse(books.isEmpty());
    }

    @Test
    public void testUpdateBook() {
        Book book = bookDAO.findById("1234567890");
        book.setTitle("Updated Title");
        bookDAO.update(book);
        Book updatedBook = bookDAO.findById("1234567890");
        assertEquals("Updated Title", updatedBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        bookDAO.delete("1234567890");
        Book book = bookDAO.findById("1234567890");
        assertNull(book);
    }
}
