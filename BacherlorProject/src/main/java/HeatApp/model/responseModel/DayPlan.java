package HeatApp.model.responseModel;

import lombok.Value;

import java.util.ArrayList;

@Value
public class DayPlan {

    ArrayList<FoodSummeryModel> meals;
}
