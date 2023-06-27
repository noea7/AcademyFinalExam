package lt.techin.kristina.autoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairmanDto {

    private String firstName;

    private String lastName;

    private String specialization;

    private String city;
}
