package HeatApp.service;

import HeatApp.model.requestModel.SearchRequestModel;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummaryModel;

import java.util.List;

public interface FoodService {

    FoodResponseModel getFoodInfo(Integer id);
    FoodSummaryModel getFoodSummeryInfo(Integer id);

    List<FoodSummaryModel> findFood(SearchRequestModel searchRequestModel);

}
