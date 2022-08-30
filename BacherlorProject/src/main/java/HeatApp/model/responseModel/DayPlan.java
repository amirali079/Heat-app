package HeatApp.model.responseModel;

import lombok.Value;

@Value
public class DayPlan {

    FoodSummary breakFast;
    FoodSummary lunch;
    FoodSummary snack;
    FoodSummary dinner;
}
