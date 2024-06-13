package dao.impl;

import dao.BookDAO;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    private SessionFactory sessionFactory;

    public BookDAOImpl() {

        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void save(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    @Override
    public Book findById(String isbn) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, isbn);
        session.close();
        return book;
    }

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("from Book", Book.class).list();
        session.close();
        return books;
    }

    @Override
    public void update(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(String isbn) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, isbn);
        if (book != null) {
            session.delete(book);
        }
        transaction.commit();
        session.close();
    }
}
