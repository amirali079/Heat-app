package HeatApp.service;

import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.responseModel.DayPlan;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;
import HeatApp.model.responseModel.FoodSummeryModel;

import java.util.List;

public interface UserService {

    String addUser(UserRegisterRequestModel user);
    String loginUser(UserLoginRequestModel user);

    String addUserPreference(UserPreferenceRequestModel user);

    String likeFood(Integer userId,Integer foodId);

    Boolean isLikedFood(Integer userId,Integer foodId);

    List<FoodSummeryModel> getFoodLikes(Integer userId);


    List<DayPlan> generatePlan(Integer userId, Integer day);
}
