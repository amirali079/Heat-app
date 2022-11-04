package HeatApp.model.requestModel;

import lombok.Value;

@Value
public class PlanGenerateRequest {
    static final class Meal{
        Integer calorie;
        Integer fat;
        Integer carbohydrates;
        Integer protein;
    }

    Meal breakfast;
    Meal lunch;
    Meal snack;
    Meal dinner;
}
