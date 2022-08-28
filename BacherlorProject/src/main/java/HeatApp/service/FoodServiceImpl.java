package HeatApp.service;

import HeatApp.exception.EntityNotFoundException;
import HeatApp.model.Food;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import HeatApp.model.requestModel.SearchRequestModel;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummaryModel;
import HeatApp.repository.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;

    @Override
    public FoodResponseModel getFoodInfo(Integer id) {
        Optional<Food> food= foodRepository.findById(id);
        if (food.isEmpty())
            throw new EntityNotFoundException(Food.class.getName(),id.toString());

        return food.get().responseModel();
    }

    @Override
    public FoodSummaryModel getFoodSummeryInfo(Integer id) {
        Optional<Food> food= foodRepository.findById(id);
        if (food.isEmpty())
            throw new EntityNotFoundException(Food.class.getName(),id.toString());

        return food.get().summeryModel();
    }

    @Override
    public List<FoodSummaryModel> findFood(SearchRequestModel request) {

        List<FoodSummaryModel> foods = new ArrayList<>();
        foodRepository.findAll().forEach(food -> foods.add(food.summeryModel()));

        System.out.println("ALL: "+foods);

        if (!request.getCuisine().equals(Cuisine.NONE))
            foods.removeIf(f->(!f.getCuisines().contains(request.getCuisine())));

        System.out.println("Filter Cuisine: "+foods);

        if (!request.getDietType().equals(DietType.NONE))
            foods.removeIf(f->(!f.getDietTypes().contains(request.getDietType())));

        System.out.println("Filter DietType: "+foods);

        if (!request.getMealType().equals(MealType.NONE))
            foods.removeIf(f->(!f.getMealTypes().contains(request.getMealType())));

        System.out.println("Filter MealType: "+foods);

        foods.removeIf(f->(f.getCalorie().getAmount()>request.getMaxCalorie()||
                f.getCalorie().getAmount()<request.getMinCalorie()));

        System.out.println("Filter Calorie: "+foods);

        if (!request.getKeyword().equals(""))
         foods.removeIf(f->(!f.getTitle().contains(request.getKeyword())));

        if (foods.isEmpty())
            throw new EntityNotFoundException(Food.class.getName(),"none");

        return foods;
    }
}
