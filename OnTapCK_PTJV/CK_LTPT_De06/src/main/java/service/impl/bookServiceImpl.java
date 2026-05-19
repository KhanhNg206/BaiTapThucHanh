package service.impl;

import daos.bookDAO;
import dto.BookDTO;
import mappers.BookMapper;
import models.Book;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class bookServiceImpl implements service.bookService {
    private bookDAO bookDAO;

    public bookServiceImpl(){
        bookDAO = new bookDAO();
    }

    @Override
    public List<BookDTO> listRateBooks(String author, int rating) {
        return bookDAO.listRateBooks(author,rating)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String,Long> countBooksByAuthor(){
        return bookDAO.countBooksByAuthor();
    }

    @Override
    public boolean updateReviews(String isbn, String readerID, int rating, String comment){
        return bookDAO.updateReviews(isbn,readerID,rating,comment);
    }
}
