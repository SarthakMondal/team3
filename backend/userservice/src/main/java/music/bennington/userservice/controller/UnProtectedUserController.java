package music.bennington.userservice.controller;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.model.LoginModel;
import music.bennington.userservice.model.UserModel;
import music.bennington.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicapp/backend/user/public")
public class UnProtectedUserController
{
    @Autowired
    UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<Object> signupUser(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody UserModel userModel)
    {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setEmailId(userModel.getEmailId());
    	userEntity.setUserName(userModel.getUserName());
    	userEntity.setMobileNo(userModel.getMobileNo());
    	userEntity.setPassword(userModel.getPassword());
    	userEntity.setUserAge(userModel.getUserAge());
    	userEntity.setUserGender(userModel.getUserGender());
    	
        return this.userService.signupUser(userEntity);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> loginUser(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody LoginModel loginModel)
    {
        return this.userService.loginUser(loginModel);
    }

}
