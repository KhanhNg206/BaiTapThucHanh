package models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReviewId implements Serializable {
    protected String book;
    private int person;
}
