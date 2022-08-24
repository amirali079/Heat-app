package HeatApp.model.requestModel;

import lombok.Value;

@Value
public class UserLoginRequestModel {
    String username;
    String password;
}
