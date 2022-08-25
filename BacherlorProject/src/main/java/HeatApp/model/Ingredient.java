package HeatApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Table
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Entity
public class Ingredient {

    @Id
    @Column(name = "id")
    private Integer id;

    private String name;
    private String aisle;
    private String original;
    private String imageURL;


}
