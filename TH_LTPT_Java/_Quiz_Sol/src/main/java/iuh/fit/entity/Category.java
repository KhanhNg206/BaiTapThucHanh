package iuh.fit.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private String categoryId;
    private String name;
}
