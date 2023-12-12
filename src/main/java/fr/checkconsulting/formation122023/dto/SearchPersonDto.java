package fr.checkconsulting.formation122023.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchPersonDto {

    private String lastName;
    private String firstName;
    private String email;
    private Integer productNumber;
    private String poductsNames;
}
