package HeatApp.service;

import HeatApp.model.Food;
import HeatApp.model.enums.Cuisine;
import HeatApp.model.enums.DietType;
import HeatApp.model.enums.MealType;
import HeatApp.model.requestModel.SearchRequestModel;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummeryModel;
import HeatApp.repository.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
            throw new EntityNotFoundException(Food.class.getName());

        return food.get().responseModel();
    }

    @Override
    public FoodSummeryModel getFoodSummeryInfo(Integer id) {
        Optional<Food> food= foodRepository.findById(id);
        if (food.isEmpty())
            throw new EntityNotFoundException(Food.class.getName());

        return food.get().summeryModel();
    }

    @Override
    public List<FoodSummeryModel> findFood(SearchRequestModel request) {

        List<FoodSummeryModel> foods = new ArrayList<>();
        foodRepository.findAll().forEach(food -> foods.add(food.summeryModel()));

        if (!request.getCuisine().equals(Cuisine.NONE))
            foods.removeIf(f->(!f.getCuisines().contains(request.getCuisine())));

        if (!request.getDietType().equals(DietType.NONE))
            foods.removeIf(f->(!f.getDietTypes().contains(request.getDietType())));

        if (!request.getMealType().equals(MealType.NONE))
            foods.removeIf(f->(!f.getMealTypes().contains(request.getMealType())));

        foods.removeIf(f->(f.getCalorie().getAmount()>request.getMaxCalorie()||
                f.getCalorie().getAmount()<request.getMinCalorie()));

        foods.removeIf(f->(f.getTitle().contains(request.getKeyword())));

        if (foods.isEmpty())
            throw new EntityNotFoundException(Food.class.getName());

        return foods;
    }
}
