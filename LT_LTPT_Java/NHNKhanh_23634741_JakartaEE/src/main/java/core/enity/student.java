package core.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Student")
public class student {
    @Id
    @Column(columnDefinition = "varchar(20)")
    private String mssv;
    private String ho;
    @Column(nullable = false)
    private String ten;
    private String gioiTinh;
    private LocalDate ngaySinh;
}
