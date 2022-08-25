package HeatApp.model.responseModel;

import HeatApp.model.Ingredient;
import HeatApp.model.InstructionStep;
import HeatApp.model.Nutrient;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import lombok.Value;
import java.util.Set;

@Value
public class FoodResponseModel {

     Boolean veryHealthy;
     String title;
     String readyInMinute;
     String summary;
     String imageLink;

     Set<Cuisine> cuisines;

     Set<MealType> mealTypes;

     Set<DietType> dietTypes;

     Set<InstructionStep> instructionSteps;

     Set<Ingredient> ingredients;

     Set<Nutrient> nutrients;
}
