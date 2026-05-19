package dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {
    private String ISBN;
    private String name;
    private int publishYear;
    private int numOfPages;
    private double price;
    private Set<String> authors;
    private String publisherName;
}
