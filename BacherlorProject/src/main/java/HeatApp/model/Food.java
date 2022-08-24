package HeatApp.model;

import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummeryModel;
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
    private String summery;
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

    @OneToMany
    private Set<Ingredient> ingredients;

    @OneToMany
    private Set<Nutrient> nutrients;

    public FoodResponseModel responseModel() {
        return new FoodResponseModel(veryHealthy, title, readyInMinute, summery, imageLink, cuisines, mealTypes, dietTypes,
                instructionSteps, ingredients, nutrients);
    }

    public FoodSummeryModel summeryModel(){
        return new FoodSummeryModel(id,title,readyInMinute,imageLink,LocalDate.now(),nutrients,dietTypes,cuisines,mealTypes);
    }
}
