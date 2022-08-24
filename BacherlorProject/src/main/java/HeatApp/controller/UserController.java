package HeatApp.controller;

import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;
import HeatApp.model.responseModel.FoodSummeryModel;
import HeatApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity saveUser(@RequestBody UserRegisterRequestModel userRegisterRequestModel) {
        return new ResponseEntity(userService.addUser(userRegisterRequestModel), HttpStatus.CREATED);
    }

    @PutMapping("/user/login")
    public ResponseEntity loginUser(@RequestBody UserLoginRequestModel userLoginRequestModel) {
        return new ResponseEntity( userService.loginUser(userLoginRequestModel), HttpStatus.OK);
    }

    @PostMapping("/user/{id}/addInfo")
    public ResponseEntity saveUserPreference(@PathVariable Integer id, @RequestBody UserPreferenceRequestModel userPreferenceRequestModel) {
        return new ResponseEntity(userService.addUserPreference(userPreferenceRequestModel), HttpStatus.CREATED);
    }

    @PatchMapping ("/user/{userId}/food/{foodId}/like")
    public ResponseEntity likeFood(@PathVariable Integer userId,@PathVariable Integer foodId) {
        return new ResponseEntity(userService.likeFood(userId,foodId), HttpStatus.OK);
    }

    @GetMapping ("/user/{userId}/food/{foodId}/isLiked")
    public Boolean isLikedFood(@PathVariable Integer userId,@PathVariable Integer foodId) {
        return userService.isLikedFood(userId,foodId);
    }

    @GetMapping("/user/{id}/foodLikes")
    public List<FoodSummeryModel> getFoodLikes(@PathVariable Integer id){
        return userService.getFoodLikes(id);
    }

    @GetMapping("/user/{id}/generatePlan/{day}")
    public ResponseEntity generatePlan(@PathVariable Integer id,@PathVariable Integer day){
        return new ResponseEntity( userService.generatePlan(id,day), HttpStatus.OK);
    }




}
