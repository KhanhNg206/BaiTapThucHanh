package dto;

import entity.DoseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO implements Serializable {
    private Long id;
    private LocalDate injectionDate;
    private int doseNumber;
    private DoseStatus status;
}
