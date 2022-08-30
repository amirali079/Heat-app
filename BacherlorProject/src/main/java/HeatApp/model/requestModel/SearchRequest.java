package HeatApp.model.requestModel;

import HeatApp.model.enums.DietType;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.MealType;
import lombok.Value;

@Value
public class SearchRequest {

    String keyword;
    Double minCalorie;
    Double maxCalorie;

    Cuisine cuisine;
    MealType mealType;
    DietType dietType;

}
