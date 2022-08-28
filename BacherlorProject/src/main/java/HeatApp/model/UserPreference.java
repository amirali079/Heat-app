package HeatApp.model;

import HeatApp.model.enums.*;
import HeatApp.model.responseModel.UserPreferenceResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Entity
@Table(name = "user_preference")
public class UserPreference {


    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    private User user;

    private String name;

    private Double weight;
    private Double height;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private ActiveLevel activeLevel;

    @Enumerated(EnumType.STRING)
    private AbstractGoal abstractGoal;

    @Enumerated(EnumType.STRING)
    private UserDietType dietType;

    @ElementCollection(targetClass = IngredientAllergy.class)
    @Enumerated(EnumType.STRING)
    private Set<IngredientAllergy> ingredientAllergies;

    @ElementCollection(targetClass = Disease.class)
    @Enumerated(EnumType.STRING)
    private Set<Disease> diseases;

    public UserPreferenceResponseModel responseModel(){
        return new UserPreferenceResponseModel(name,weight,height,age,gender,activeLevel,abstractGoal,dietType,ingredientAllergies,diseases);
    }





}
