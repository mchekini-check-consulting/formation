package fr.checkconsulting.formation122023.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SearchCriteriaDto {

    private String lastName;
    private String firstName;
    private String minAge;
    private String maxAge;
}
