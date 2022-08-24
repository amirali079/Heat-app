package HeatApp.controller;

import HeatApp.model.requestModel.SearchRequestModel;
import HeatApp.model.responseModel.FoodResponseModel;
import HeatApp.model.responseModel.FoodSummeryModel;
import HeatApp.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/food/{id}")
    public ResponseEntity<FoodResponseModel> getFood(@PathVariable Integer id) {
        return new ResponseEntity<>(foodService.getFoodInfo(id), HttpStatus.FOUND);
    }

    @GetMapping("/foodSummery/{id}")
    public ResponseEntity<FoodSummeryModel> getFoodSummery(@PathVariable Integer id) {
        return new ResponseEntity<>(foodService.getFoodSummeryInfo(id), HttpStatus.FOUND);
    }

    @GetMapping("/search")
    public List<FoodSummeryModel> getFoodLikes(@RequestBody SearchRequestModel searchRequestModel){
        return foodService.findFood(searchRequestModel);
    }

}
