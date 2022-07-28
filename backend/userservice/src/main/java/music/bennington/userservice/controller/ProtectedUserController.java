package music.bennington.userservice.controller;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.model.UserModel;
import music.bennington.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicapp/backend/user/protected")
public class ProtectedUserController
{
    @Autowired
    UserService userService;

    @GetMapping(path = "/getuseridandrole")
    public ResponseEntity<Object> getUserIdAndRole(@RequestHeader(value = "accessedThroughGateway") String header)
    {
        return this.userService.getIdAndRoleOfLoggedInPerson();
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<Object> userProfile(@RequestHeader(value = "accessedThroughGateway") String header)
    {
        return this.userService.viewProfile();
    }

    @PutMapping(path = "/updateprofile")
    public ResponseEntity<Object> updateUser(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody UserModel userModel)
    {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setEmailId(userModel.getEmailId());
    	userEntity.setUserName(userModel.getUserName());
    	userEntity.setMobileNo(userModel.getMobileNo());
    	userEntity.setPassword(userModel.getPassword());
    	userEntity.setUserAge(userModel.getUserAge());
    	userEntity.setUserGender(userModel.getUserGender());
        return this.userService.updateProfile(userEntity);
    }

}
