package models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book implements Serializable {
    @Id
    @EqualsAndHashCode.Exclude
    protected String ISBN;
    protected  String name;

    @Column(name = "publish_year")
    protected int publishYear;

    @Column(name = "num_of_pages")
    protected  int numOfPages;
    protected double price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "ISBN"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ISBN","author"})
    )
    @Column(name = "author")
    protected Set<String> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    protected  Publisher publisher;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<Reviews> reviews;

}
