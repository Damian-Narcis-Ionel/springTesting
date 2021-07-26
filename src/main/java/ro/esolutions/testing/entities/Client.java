package ro.esolutions.testing.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    private Long id;

    private String name;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        LOYAL, DISLOYAL, NEW, VETERAN
    }

}
