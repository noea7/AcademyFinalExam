package lt.techin.kristina.autoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoserviceDto {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private String manager;
}
