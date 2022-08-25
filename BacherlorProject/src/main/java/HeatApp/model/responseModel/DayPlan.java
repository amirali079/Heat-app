package HeatApp.model.responseModel;

import lombok.Value;

@Value
public class DayPlan {

    FoodSummaryModel breakFast;
    FoodSummaryModel lunch;
    FoodSummaryModel snack;
    FoodSummaryModel dinner;
}
