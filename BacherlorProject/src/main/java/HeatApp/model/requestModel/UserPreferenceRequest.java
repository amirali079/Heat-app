package HeatApp.model.requestModel;

import HeatApp.model.enums.*;
import lombok.Value;

import java.util.Set;

@Value
public class UserPreferenceRequest {

    Integer id;

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
