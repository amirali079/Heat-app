package HeatApp.controller;

import HeatApp.model.requestModel.SearchRequest;
import HeatApp.model.responseModel.FoodResponse;
import HeatApp.model.responseModel.FoodSummary;
import HeatApp.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/food/{id}")
    public ResponseEntity<FoodResponse> getFood(@PathVariable Integer id) {
        return new ResponseEntity<>(foodService.getFoodInfo(id), HttpStatus.OK);
    }

    @GetMapping("/foodSummery/{id}")
    public ResponseEntity<FoodSummary> getFoodSummery(@PathVariable Integer id) {
        return new ResponseEntity<>(foodService.getFoodSummeryInfo(id), HttpStatus.OK);
    }

    // this api is wrong, search api must be Get method and have not body !(search parameter must be in path)
    @PostMapping("/search")
    public ResponseEntity<List<FoodSummary>> getFoodLikes(@RequestBody SearchRequest searchRequest){
        return new ResponseEntity<>(foodService.findFood(searchRequest),HttpStatus.OK);
    }

}
