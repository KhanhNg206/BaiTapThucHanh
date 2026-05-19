package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "book_translations")
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class BookTranslation extends Book implements Serializable {

    @Column(name = "translate_name")
    private String translateName;
    private String language;
}
