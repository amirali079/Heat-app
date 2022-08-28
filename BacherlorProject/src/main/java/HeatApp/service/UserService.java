package HeatApp.service;

import HeatApp.model.UserPreference;
import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.responseModel.*;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;

import java.util.List;

public interface UserService {

    UserResponseModel addUser(UserRegisterRequestModel user);
    UserResponseModel loginUser(UserLoginRequestModel user);

    UserResponseModel addUserPreference(UserPreferenceRequestModel user);
    UserPreferenceResponseModel getUserPreference(Integer userId);

    LikeResponseModel likeFood(Integer userId, Integer foodId);

    IsLikedResponseModel isLikedFood(Integer userId, Integer foodId);

    List<FoodSummaryModel> getFoodLikes(Integer userId);

    List<DayPlan> generatePlan(Integer userId, Integer day);
}
