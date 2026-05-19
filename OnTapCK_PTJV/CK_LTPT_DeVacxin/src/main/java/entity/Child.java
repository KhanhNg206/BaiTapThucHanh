package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "children")
@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Child extends Person implements Serializable {

    @Column(name = "guardian_name")
    private String guardianName;
}
