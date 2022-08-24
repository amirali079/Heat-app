package HeatApp.model.responseModel;

import lombok.Value;

@Value
public class DayPlan {

    FoodSummeryModel breakFast;
    FoodSummeryModel lunch;
    FoodSummeryModel snack;
    FoodSummeryModel dinner;
}
