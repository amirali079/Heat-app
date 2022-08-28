package HeatApp.model.responseModel;

import HeatApp.model.enums.*;
import lombok.Value;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Value
public class UserPreferenceResponseModel {
    String name;

    Double weight;
    Double height;
    Integer age;

    Gender gender;

    ActiveLevel activeLevel;

    AbstractGoal abstractGoal;

    UserDietType dietType;

    Set<IngredientAllergy> ingredientAllergies;

    Set<Disease> diseases;
}
