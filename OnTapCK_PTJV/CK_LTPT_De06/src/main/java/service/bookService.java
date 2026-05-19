package service;

import dto.BookDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface bookService extends Remote {
    List<BookDTO> listRateBooks(String author, int rating);
    Map<String, Long> countBooksByAuthor();

    boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
