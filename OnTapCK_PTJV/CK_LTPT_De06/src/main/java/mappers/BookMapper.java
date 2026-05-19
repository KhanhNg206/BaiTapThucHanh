package mappers;

import dto.BookDTO;
import models.Book;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getISBN(),
                book.getName(),
                book.getPublishYear(),
                book.getNumOfPages(),
                book.getPrice(),
                book.getAuthors(),
                book.getPublisher() != null ? book.getPublisher().getName() : null
        );
    }
}
