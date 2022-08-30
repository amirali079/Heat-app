package HeatApp.controller;

import HeatApp.model.requestModel.UserPreferenceRequest;
import HeatApp.model.requestModel.UserLoginRequest;
import HeatApp.model.requestModel.UserRegisterRequest;
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
// This program has a security problem because it does not use any authentication mechanism for requests.
    @PostMapping("/user/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return new ResponseEntity<>(userService.addUser(userRegisterRequest), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return new ResponseEntity<>( userService.loginUser(userLoginRequest), HttpStatus.OK);
    }

    @PostMapping("/user/addInfo")
    public ResponseEntity<UserResponse> saveUserPreference(@RequestBody UserPreferenceRequest userPreferenceRequest) {
        return new ResponseEntity<>(userService.addUserPreference(userPreferenceRequest), HttpStatus.CREATED);
    }

    @PatchMapping ("/user/{userId}/food/{foodId}/like")
    public ResponseEntity<LikeResponse> likeFood(@PathVariable Integer userId, @PathVariable Integer foodId) {
        return new ResponseEntity<>(userService.likeFood(userId,foodId), HttpStatus.OK);
    }

    @GetMapping ("/user/{userId}/food/{foodId}/isLiked")
    public ResponseEntity<IsLikedResponse> isLikedFood(@PathVariable Integer userId, @PathVariable Integer foodId) {
        return new ResponseEntity<>(userService.isLikedFood(userId,foodId),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserPreferenceResponse> getUserPreference(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserPreference(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/foodLikes")
    public ResponseEntity<List<FoodSummary>> getFoodLikes(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getFoodLikes(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/generatePlan/{day}")
    public ResponseEntity<List<DayPlan>> generatePlan(@PathVariable Integer id, @PathVariable Integer day){
        return new ResponseEntity<>(userService.generatePlan(id,day),HttpStatus.CREATED);
    }




}
