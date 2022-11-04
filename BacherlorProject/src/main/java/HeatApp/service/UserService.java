package HeatApp.service;

import HeatApp.model.requestModel.UserPreferenceRequest;
import HeatApp.model.responseModel.*;
import HeatApp.model.requestModel.UserLoginRequest;
import HeatApp.model.requestModel.UserRegisterRequest;

import java.util.List;

public interface UserService {

    UserResponse addUser(UserRegisterRequest user);
    UserResponse loginUser(UserLoginRequest user);

    UserResponse addUserPreference(UserPreferenceRequest user);
    UserPreferenceResponse getUserPreference(Integer userId);

    LikeResponse likeFood(Integer userId, Integer foodId);

    IsLikedResponse isLikedFood(Integer userId, Integer foodId);

    List<FoodSummary> getFoodLikes(Integer userId);

    List<DayPlan> generatePlan(Integer userId, Integer day);
}
