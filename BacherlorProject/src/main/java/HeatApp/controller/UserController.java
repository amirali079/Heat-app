package HeatApp.controller;

import HeatApp.model.requestModel.UserPreferenceRequestModel;
import HeatApp.model.requestModel.UserLoginRequestModel;
import HeatApp.model.requestModel.UserRegisterRequestModel;
import HeatApp.model.responseModel.*;
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
    public ResponseEntity<UserResponseModel> saveUser(@RequestBody UserRegisterRequestModel userRegisterRequestModel) {
        return new ResponseEntity<>(userService.addUser(userRegisterRequestModel), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserResponseModel> loginUser(@RequestBody UserLoginRequestModel userLoginRequestModel) {
        return new ResponseEntity<>( userService.loginUser(userLoginRequestModel), HttpStatus.OK);
    }

    @PostMapping("/user/addInfo")
    public ResponseEntity<UserResponseModel> saveUserPreference(@RequestBody UserPreferenceRequestModel userPreferenceRequestModel) {
        return new ResponseEntity<>(userService.addUserPreference(userPreferenceRequestModel), HttpStatus.CREATED);
    }

    @PatchMapping ("/user/{userId}/food/{foodId}/like")
    public ResponseEntity<LikeResponseModel> likeFood(@PathVariable Integer userId, @PathVariable Integer foodId) {
        return new ResponseEntity<>(userService.likeFood(userId,foodId), HttpStatus.OK);
    }

    @GetMapping ("/user/{userId}/food/{foodId}/isLiked")
    public ResponseEntity<IsLikedResponseModel> isLikedFood(@PathVariable Integer userId, @PathVariable Integer foodId) {
        return new ResponseEntity<>(userService.isLikedFood(userId,foodId),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserPreferenceResponseModel> getUserPreference(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserPreference(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/foodLikes")
    public ResponseEntity<List<FoodSummaryModel>> getFoodLikes(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getFoodLikes(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/generatePlan/{day}")
    public ResponseEntity<List<DayPlan>> generatePlan(@PathVariable Integer id, @PathVariable Integer day){
        return new ResponseEntity<>(userService.generatePlan(id,day),HttpStatus.CREATED);
    }




}
