package HeatApp.service;

import HeatApp.model.requestModel.SearchRequest;
import HeatApp.model.responseModel.FoodResponse;
import HeatApp.model.responseModel.FoodSummary;

import java.util.List;

public interface FoodService {

    FoodResponse getFoodInfo(Integer id);
    FoodSummary getFoodSummeryInfo(Integer id);

    List<FoodSummary> findFood(SearchRequest searchRequest);

}
