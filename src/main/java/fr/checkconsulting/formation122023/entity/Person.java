package fr.checkconsulting.formation122023.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    private String email;
    private String lastName;
    private String firstName;
    private Integer old;
    @OneToMany(mappedBy = "person")
    private List<Product> productList;
}
