package HeatApp.model.responseModel;

import HeatApp.model.Nutrient;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value
public class FoodSummeryModel {


    public FoodSummeryModel(Integer id, String title, String readyInMinute, String imageLink, LocalDate localDate,
                            Set<Nutrient> nutrients, Set<DietType> dietTypes, Set<Cuisine> cuisines, Set<MealType> mealTypes) {
        this.id = id;
        this.title = title;
        this.readyInMinute = readyInMinute;
        this.imageLink = imageLink;
        this.localDate = localDate;
        this.dietTypes = dietTypes;
        this.cuisines = cuisines;
        this.mealTypes = mealTypes;

        this.eaten = false;

        Nutrient temp = null;
        for (Nutrient n : nutrients)
            if (n.getName().equals("Calories"))
                temp = n;
        this.calorie = temp;
    }

    Integer id;
    String title;
    String readyInMinute;
    String imageLink;
    Boolean eaten;

    LocalDate localDate;

    Nutrient calorie;
    Set<DietType> dietTypes;
    Set<Cuisine> cuisines;
    Set<MealType> mealTypes;

}

