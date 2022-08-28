package HeatApp.rest.dataCrawling;


import HeatApp.model.Food;
import HeatApp.model.Ingredient;
import HeatApp.model.InstructionStep;
import HeatApp.model.Nutrient;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import HeatApp.repository.FoodRepository;
import HeatApp.repository.IngredientRepository;
import HeatApp.repository.InstructionStepRepository;
import HeatApp.repository.NutrientRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class DataAdder {

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;
    private final NutrientRepository nutrientRepository;
    private final InstructionStepRepository instructionStepRepository;

   // @PostConstruct
    private void process() throws FileNotFoundException, InterruptedException {
        dataInputting();
    }

    private void dataInputting() throws FileNotFoundException, InterruptedException {


        Gson gson = new Gson();

        for (int i = 1; i <= 900; i++) {
            Scanner sc = new Scanner(new File("./data/json/d"+i+".json"));
            String sample = sc.nextLine();
            System.out.println(sample);
            JsonElement element = gson.fromJson(sample, JsonElement.class);
            System.out.println("----------------- "+i);

            generate(element);
            sc.close();
            Thread.sleep(10);
        }


    }

    private void generate( JsonElement element){

        Set<Cuisine> cuisines = new HashSet<>();
        Set<MealType> mealTypes = new HashSet<>();
        Set<DietType> dietTypes = new HashSet<>();
        Set<InstructionStep> instructionSteps = new HashSet<>();
        Set<Ingredient> ingredients = new HashSet<>();
        Set<Nutrient> nutrients = new HashSet<>();

        JsonArray nutrient = (JsonArray) element.getAsJsonObject().get("nutrition").getAsJsonObject().get("nutrients");
        for (JsonElement n : nutrient){
            Nutrient nutrient1 = Nutrient.builder().name(n.getAsJsonObject().get("name").getAsString()).amount(n.getAsJsonObject().get("amount").getAsDouble())
                    .unit(n.getAsJsonObject().get("unit").getAsString()).build();
            nutrientRepository.save(nutrient1);
            nutrients.add(nutrient1);}

        JsonArray ingredient = (JsonArray) element.getAsJsonObject().get("extendedIngredients");
        for (JsonElement n : ingredient) {
            String image = "null";
            JsonElement img = n.getAsJsonObject().get("image");
            if (!img.isJsonNull())image=img.getAsString();

            String aisle = "null";
            JsonElement ais = n.getAsJsonObject().get("aisle");
            if (!ais.isJsonNull())aisle=ais.getAsString();

            Ingredient ingredient1 = Ingredient.builder()
                    .id(n.getAsJsonObject().get("id").getAsInt())
                    .name(n.getAsJsonObject().get("name").getAsString())
                    .aisle(aisle)
                    .original(n.getAsJsonObject().get("original").getAsString())
                    .imageURL(image)
                    .build();

            ingredientRepository.save(ingredient1);
            ingredients.add(ingredient1);
        }

        if (element.getAsJsonObject().get("analyzedInstructions").getAsJsonArray().size()!=0) {
            JsonArray instructionStep = (JsonArray) element.getAsJsonObject().get("analyzedInstructions").getAsJsonArray().get(0).getAsJsonObject().get("steps");
            for (JsonElement n : instructionStep) {

                InstructionStep instructionStep1 = InstructionStep.builder()
                        .number(n.getAsJsonObject().get("number").getAsInt())
                        .step(n.getAsJsonObject().get("step").getAsString())
                        .build();

                instructionStepRepository.save(instructionStep1);

                instructionSteps.add(instructionStep1);
            }
        }

        JsonArray cuisine = (JsonArray) element.getAsJsonObject().get("cuisines");
        for (JsonElement n : cuisine){
            if (n.getAsString().equals("Eastern European"))
                cuisines.add(Cuisine.EasternEuropean);
            else if (n.getAsString().equals("Latin American"))
                cuisines.add(Cuisine.LatinAmerican);
            else if (n.getAsString().equals("Middle Eastern"))
                cuisines.add(Cuisine.MiddleEastern);
            else if (n.getAsString().equals("Central American"))
                cuisines.add(Cuisine.CentralAmerican);
            else if (n.getAsString().equals("South American"))
                cuisines.add(Cuisine.SouthAmerican);
            else
                cuisines.add(Cuisine.valueOf(n.getAsString()));
        }

        JsonArray mealType = (JsonArray) element.getAsJsonObject().get("dishTypes");
        for (JsonElement n : mealType) {
            switch (n.getAsString()) {
                case "main course":
                    mealTypes.add(MealType.MAIN_COURSE);
                    break;
                case "side dish":
                    mealTypes.add(MealType.SIDE_DISH);
                    break;
                case "dessert":
                    mealTypes.add(MealType.DESSERT);
                    break;
                case "appetizer":
                    mealTypes.add(MealType.APPETIZER);
                    break;
                case "salad":
                    mealTypes.add(MealType.SALAD);
                    break;
                case "bread":
                    mealTypes.add(MealType.BREAD);
                    break;
                case "breakfast":
                    mealTypes.add(MealType.BREAKFAST);
                    break;
                case "soup":
                    mealTypes.add(MealType.SOUP);
                    break;
                case "beverage":
                    mealTypes.add(MealType.BEVERAGE);
                    break;
                case "sauce":
                    mealTypes.add(MealType.SAUCE);
                    break;
                case "fingerfood":
                    mealTypes.add(MealType.FINGER_FOOD);
                    break;
                case "snack":
                    mealTypes.add(MealType.SNACK);
                    break;
                case "drink":
                    mealTypes.add(MealType.DRINK);
                    break;
            }
        }

        if (element.getAsJsonObject().get("vegetarian").getAsBoolean())
            dietTypes.add(DietType.VEGETARIAN);
        if (element.getAsJsonObject().get("vegan").getAsBoolean())
            dietTypes.add(DietType.VEGAN);
        if (element.getAsJsonObject().get("glutenFree").getAsBoolean())
            dietTypes.add(DietType.GLUTEN_FREE);
        if (element.getAsJsonObject().get("dairyFree").getAsBoolean())
            dietTypes.add(DietType.DAIRY_FREE);
        for (Ingredient ing : ingredients){
            if (ing.getName().contains("pork")||ing.getName().contains("Pork")||
                    ing.getName().contains("alcohol")|| ing.getName().contains("Alcoholic")|| ing.getName().contains("Alcohol")||
                    ing.getName().contains("blood")||
                    ing.getName().contains("shark")||
                    ing.getName().contains("lobster")|| ing.getName().contains("Lobster")||
                    ing.getName().contains("octopus")||
                    ing.getName().contains("eel")||
                    ing.getName().contains("frog")||
                    ing.getName().contains("shellfish")|| ing.getName().contains("Shellfish")||
                    ing.getAisle().contains("pork")|| ing.getAisle().contains("Pork")||
                    ing.getAisle().contains("alcohol")|| ing.getAisle().contains("Alcoholic")|| ing.getAisle().contains("Alcohol")||
                    ing.getAisle().contains("blood")||
                    ing.getAisle().contains("shark")||
                    ing.getAisle().contains("lobster")|| ing.getAisle().contains("Lobster")||
                    ing.getAisle().contains("octopus")||
                    ing.getAisle().contains("eel")||
                    ing.getAisle().contains("frog")||
                    ing.getAisle().contains("shellfish") || ing.getAisle().contains("Shellfish")
            ) dietTypes.add(DietType.NOT_HALAL);

            if (ing.getName().contains("pork")||ing.getName().contains("Pork")||
                    ing.getName().contains("rabbit")||
                    ing.getName().contains("horse")||
                    ing.getName().contains("shark")||
                    ing.getName().contains("bear")||
                    ing.getName().contains("octopus")||
                    ing.getName().contains("eel")||
                    ing.getName().contains("frog")||
                    ing.getName().contains("shellfish")|| ing.getName().contains("Shellfish")||
                    ing.getAisle().contains("pork")|| ing.getAisle().contains("Pork")||
                    ing.getAisle().contains("rabbit")||
                    ing.getAisle().contains("horse")||
                    ing.getAisle().contains("bear")||
                    ing.getAisle().contains("shark")||
                    ing.getAisle().contains("octopus")||
                    ing.getAisle().contains("eel")||
                    ing.getAisle().contains("frog")||
                    ing.getAisle().contains("shellfish")|| ing.getAisle().contains("Shellfish")
            )
                dietTypes.add(DietType.NOT_KOSHER);
        }


        String image = "null";
        JsonElement img = element.getAsJsonObject().get("image");
        try {
            if (!img.isJsonNull())image=img.getAsString();
        }catch (NullPointerException e){}

        Food food = Food.builder()
                .id(element.getAsJsonObject().get("id").getAsInt())
                .title(element.getAsJsonObject().get("title").getAsString())
                .summary(element.getAsJsonObject().get("summary").getAsString())
                .imageLink(image)
                .readyInMinute(element.getAsJsonObject().get("readyInMinutes").getAsString())
                .veryHealthy(element.getAsJsonObject().get("veryHealthy").getAsBoolean())
                .cuisines(cuisines)
                .dietTypes(dietTypes)
                .mealTypes(mealTypes)
                .ingredients(ingredients)
                .instructionSteps(instructionSteps)
                .nutrients(nutrients)
                .build();

        System.out.println(food);
        foodRepository.save(food);
    }
}
