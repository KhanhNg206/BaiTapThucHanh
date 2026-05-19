package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "adults")
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Adult extends Person implements Serializable {

    @Column(name = "identity_number")
    private String identityNumber;
    private String occupation;

}
