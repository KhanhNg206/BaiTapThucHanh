package dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {
    protected String id;
    protected String fullName;
    protected LocalDate dob;
    protected String gender;
    protected double height;
    protected double weight;
}
