package HeatApp.service;

import HeatApp.exception.EntityNotFoundException;
import HeatApp.exception.InvalidPasswordException;
import HeatApp.exception.UsernameExistException;
import HeatApp.model.Food;
import HeatApp.model.User;
import HeatApp.model.UserPreference;
import HeatApp.model.requestModel.UserLoginRequest;
import HeatApp.model.requestModel.UserPreferenceRequest;
import HeatApp.model.requestModel.UserRegisterRequest;
import HeatApp.model.responseModel.*;
import HeatApp.repository.FoodRepository;
import HeatApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    private final RestTemplate restTemplate;

    @Override
    public UserResponse addUser(UserRegisterRequest user) {
        Optional<User> loaded = userRepository.findByUsername(user.getUsername());
        if (loaded.isPresent())
            throw new UsernameExistException(user.getUsername());

        User createdUser = User.builder().username(user.getUsername()).password(user.getPassword())
                .email(user.getEmail()).build();

        return new UserResponse(userRepository.save(createdUser).getId());
    }

    @Override
    public UserResponse loginUser(UserLoginRequest user) {
        Optional<User> loaded = userRepository.findByUsername(user.getUsername());
        if (loaded.isEmpty())
            throw new EntityNotFoundException(User.class.getName(), user.getUsername());
        if (!loaded.get().getPassword().equals(user.getPassword()))
            throw new InvalidPasswordException();
        return new UserResponse(loaded.get().getId());
    }

    @Override
    public UserResponse addUserPreference(UserPreferenceRequest user) {
        User userLoaded = checkUserId(user.getId());

        UserPreference userPreference = UserPreference.builder()
                .id(user.getId()).name(user.getName()).age(user.getAge())
                .height(user.getHeight()).weight(user.getWeight()).gender(user.getGender())
                .dietType(user.getDietType()).abstractGoal(user.getAbstractGoal()).activeLevel(user.getActiveLevel())
                .diseases(user.getDiseases()).ingredientAllergies(user.getIngredientAllergies()).build();

        userLoaded.setUserPreference(userPreference);
        userRepository.save(userLoaded);

        return new UserResponse(user.getId());
    }

    @Override
    public LikeResponse likeFood(Integer userId, Integer foodId) {

        User user = checkUserId(userId);
        Food food = checkFoodId(foodId);

        Set<Food> likedFood = user.getLikedFoods();

        LikeResponse status = new LikeResponse("liked!");

        if (likedFood.contains(food)) {
            likedFood.remove(food);
            status = new LikeResponse("unLiked!");
        } else likedFood.add(food);

        user.setLikedFoods(likedFood);
        userRepository.save(user);

        return status;
    }

    @Override
    public IsLikedResponse isLikedFood(Integer userId, Integer foodId) {
        User user = checkUserId(userId);
        Food food = checkFoodId(foodId);

        if (user.getLikedFoods().contains(food))
            return new IsLikedResponse(true);
        return new IsLikedResponse(false);
    }

    @Override
    public List<FoodSummary> getFoodLikes(Integer userId) {
        User user = checkUserId(userId);
        List<FoodSummary> foods = new ArrayList<>();
        user.getLikedFoods().forEach(food -> foods.add(food.summeryModel()));
        return foods;
    }

    @Override
    public UserPreferenceResponse getUserPreference(Integer userId) {
        User user = checkUserId(userId);

        return user.getUserPreference().responseModel();
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

        List<Food> foods = new ArrayList<>();
        foodRepository.findAll().forEach(foods::add);
        Random random = new Random();

        return new DayPlan(
                foods.get(random.nextInt(900)).summeryModel(),
                foods.get(random.nextInt(900)).summeryModel(),
                foods.get(random.nextInt(900)).summeryModel(),
                foods.get(random.nextInt(900)).summeryModel());
    }
}
