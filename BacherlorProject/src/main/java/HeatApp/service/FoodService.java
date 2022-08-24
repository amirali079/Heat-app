package HeatApp.service;

import HeatApp.model.requestModel.SearchRequestModel;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummeryModel;

import java.util.List;

public interface FoodService {

    FoodResponseModel getFoodInfo(Integer id);
    FoodSummeryModel getFoodSummeryInfo(Integer id);

    List<FoodSummeryModel> findFood(SearchRequestModel searchRequestModel);

}
