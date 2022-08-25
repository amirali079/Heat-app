package HeatApp.service;

import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.responseModel.DayPlan;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;
import HeatApp.model.responseModel.FoodSummaryModel;
import HeatApp.model.responseModel.IsLikedResponseModel;

import java.util.List;

public interface UserService {

    Integer addUser(UserRegisterRequestModel user);
    Integer loginUser(UserLoginRequestModel user);

    Integer addUserPreference(UserPreferenceRequestModel user);

    String likeFood(Integer userId,Integer foodId);

    IsLikedResponseModel isLikedFood(Integer userId, Integer foodId);

    List<FoodSummaryModel> getFoodLikes(Integer userId);


    List<DayPlan> generatePlan(Integer userId, Integer day);
}
