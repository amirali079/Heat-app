package HeatApp.model.requestModel;

import lombok.Value;

@Value
public class UserLoginRequest {
    String username;
    String password;
}
