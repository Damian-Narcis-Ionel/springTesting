package ro.esolutions.testing.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.testing.entities.Client.Type;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientModel {

    private Long id;

    @NotEmpty
    private String name;

    private Type type;

}
