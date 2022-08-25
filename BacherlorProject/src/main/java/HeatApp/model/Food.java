package HeatApp.model;

import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummaryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Table
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Entity
public class Food {

    @Id
    @Column(name = "id")
    private Integer id;


    private Boolean veryHealthy;
    private String title;
    private String readyInMinute;
    @Column(length = 100000)
    private String summary;
    private String imageLink;

    @ElementCollection(targetClass = Cuisine.class)
    @Enumerated(EnumType.STRING)
    private Set<Cuisine> cuisines;

    @ElementCollection(targetClass = MealType.class)
    @Enumerated(EnumType.STRING)
    private Set<MealType> mealTypes;

    @ElementCollection(targetClass = DietType.class)
    @Enumerated(EnumType.STRING)
    private Set<DietType> dietTypes;

    @OneToMany
    private Set<InstructionStep> instructionSteps;

    @ManyToMany
    @JoinTable(
            name = "food_ingredient",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    @OneToMany
    private Set<Nutrient> nutrients;

    public FoodResponseModel responseModel() {
        return new FoodResponseModel(veryHealthy, title, readyInMinute, summary, imageLink, cuisines, mealTypes, dietTypes,
                instructionSteps, ingredients, nutrients);
    }

    public FoodSummaryModel summeryModel(){
        return new FoodSummaryModel(id,title,readyInMinute,imageLink,LocalDate.now(),nutrients,dietTypes,cuisines,mealTypes);
    }
}
