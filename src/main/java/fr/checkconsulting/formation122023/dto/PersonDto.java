package fr.checkconsulting.formation122023.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonDto {

    private String lastName;
    private String firstName;
    private String email;
    private Integer old;
}
