package HeatApp.model.requestModel;

import HeatApp.model.enums.*;
import lombok.Value;

import java.util.Set;

@Value
public class UserPreferenceRequestModel {

    Integer id;

    String name;
    Double weight;
    Double height;
    Integer age;

    Gender gender;

    ActiveLevel activeLevel;

    AbstractGoal abstractGoal;

    DietType dietType;

    Set<IngredientAllergy> ingredientAllergies;

    Set<Disease> diseases;
}
