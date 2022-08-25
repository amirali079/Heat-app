package HeatApp.model.responseModel;

import HeatApp.model.Nutrient;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value
public class FoodSummaryModel {


    public FoodSummaryModel(Integer id, String title, String readyInMinute, String imageLink, LocalDate localDate,
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

        Nutrient calorieTemp = null, fatTemp = null, carbohydratesTemp = null, proteinTemp = null;
        for (Nutrient n : nutrients) {
            switch (n.getName()) {
                case "Calories":
                    calorieTemp = n;
                    break;
                case "Fat":
                    fatTemp = n;
                    break;
                case "Carbohydrates":
                    carbohydratesTemp = n;
                    break;
                case "Protein":
                    proteinTemp = n;
                    break;
            }
        }

        this.calorie = calorieTemp;
        this.fat = fatTemp;
        this.carbohydrates = carbohydratesTemp;
        this.protein = proteinTemp;
    }

    Integer id;
    String title;
    String readyInMinute;
    String imageLink;
    Boolean eaten;

    LocalDate localDate;

    Nutrient calorie;
    Nutrient fat;
    Nutrient carbohydrates;
    Nutrient protein;
    Set<DietType> dietTypes;
    Set<Cuisine> cuisines;
    Set<MealType> mealTypes;

}

