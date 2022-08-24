package HeatApp.service;

import HeatApp.exception.UsernameExistException;
import HeatApp.model.User;
import HeatApp.exception.EntityNotFoundException;
import HeatApp.exception.InvalidPasswordException;
import HeatApp.model.Food;
import HeatApp.model.UserPreference;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;
import HeatApp.model.responseModel.DayPlan;
import HeatApp.model.responseModel.FoodSummeryModel;
import HeatApp.model.responseModel.IsLikedResponseModel;
import HeatApp.repository.FoodRepository;
import HeatApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Override
    public Integer addUser(UserRegisterRequestModel user) {
        Optional<User> loaded = userRepository.findByUsername(user.getUsername());
        if (loaded.isPresent())
            throw new UsernameExistException(user.getUsername());

        User createdUser = User.builder().username(user.getUsername()).password(user.getPassword())
                .email(user.getEmail()).build();

        return userRepository.save(createdUser).getId();
    }

    @Override
    public Integer loginUser(UserLoginRequestModel user) {
        Optional<User> loaded = userRepository.findByUsername(user.getUsername());
        if (loaded.isEmpty())
            throw new EntityNotFoundException(User.class.getName(), user.getUsername());
        if (!loaded.get().getPassword().equals(user.getPassword()))
            throw new InvalidPasswordException();
        return loaded.get().getId();
    }

    @Override
    public Integer addUserPreference(UserPreferenceRequestModel user) {
        User userLoaded = checkUserId(user.getId());

        UserPreference userPreference = UserPreference.builder()
                .id(user.getId()).name(user.getName()).age(user.getAge())
                .height(user.getHeight()).weight(user.getWeight()).gender(user.getGender())
                .dietType(user.getDietType()).abstractGoal(user.getAbstractGoal()).activeLevel(user.getActiveLevel())
                .diseases(user.getDiseases()).ingredientAllergies(user.getIngredientAllergies()).build();

        userLoaded.setUserPreference(userPreference);
        userRepository.save(userLoaded);

        return user.getId();
    }

    @Override
    public String likeFood(Integer userId, Integer foodId) {

        User user = checkUserId(userId);
        Food food = checkFoodId(foodId);

        Set<Food> likedFood = user.getLikedFoods();

        String status="liked!";

        if (likedFood.contains(food)){
            likedFood.remove(food);
            status="unLiked!";
        }
        else likedFood.add(food);

        user.setLikedFoods(likedFood);
        userRepository.save(user);

        return status;
    }

    @Override
    public IsLikedResponseModel isLikedFood(Integer userId, Integer foodId) {
        User user = checkUserId(userId);
        Food food = checkFoodId(foodId);

        if (user.getLikedFoods().contains(food))
            return new IsLikedResponseModel(true);
        return new IsLikedResponseModel(false);
    }

    @Override
    public List<FoodSummeryModel> getFoodLikes(Integer userId) {
        User user = checkUserId(userId);
        List<FoodSummeryModel> foods = new ArrayList<>();
        user.getLikedFoods().forEach(food -> foods.add(food.summeryModel()));
        return foods;
    }

    @Override
    public List<DayPlan> generatePlan(Integer userId, Integer day) {
        User user = checkUserId(userId);
        List<DayPlan> plans = new ArrayList<>();
        for (int i = 0; i < day; i++)
            plans.add(generatePlan(user));

        return plans;
    }

    private User checkUserId(Integer id) {
        Optional<User> loaded = userRepository.findById(id);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(User.class.getName(), id.toString());
        return loaded.get();
    }

    private Food checkFoodId(Integer id) {
        Optional<Food> loaded = foodRepository.findById(id);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Food.class.getName(), id.toString());
        return loaded.get();
    }

    private DayPlan generatePlan(User user) {
        //todo Clustering
        return null;
    }
}
