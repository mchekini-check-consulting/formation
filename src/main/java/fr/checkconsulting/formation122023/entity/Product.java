package fr.checkconsulting.formation122023.entity;

import fr.checkconsulting.formation122023.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    @Enumerated(STRING)
    private Category category;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
