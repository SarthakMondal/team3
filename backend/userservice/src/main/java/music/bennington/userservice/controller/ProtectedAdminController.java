package music.bennington.userservice.controller;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.model.UserModel;
import music.bennington.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicapp/backend/admin/protected")
public class ProtectedAdminController {
private static final String BADGATEWAY="BAD GATEWAY";
    @Autowired
    UserService userService;

    @PutMapping(path = "/updateuser/{id}")
    public ResponseEntity<Object> updateUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id, @RequestBody UserModel userModel)
    {
        if(!header.equals("TRUE"))
        {
            return new ResponseEntity<>(BADGATEWAY, HttpStatus.BAD_GATEWAY);
        }
        
        UserEntity userEntity = new UserEntity();
        
        userEntity.setActive(userModel.isActive());
        userEntity.setRole(userModel.getRole());
        
        return this.userService.changeUserRoleOrActiveAccount(id, userEntity);
    }

    @DeleteMapping(path = "/deleteuser/{id}")
    public ResponseEntity<Object> removeUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id)
    {
        if(!header.equals("TRUE"))
        {
            return new ResponseEntity<>(BADGATEWAY, HttpStatus.BAD_GATEWAY);
        }
        return this.userService.removeUser(id);
    }

    @GetMapping(path = "/getuser/{id}")
    public ResponseEntity<Object> viewUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id)
    {
        if(!header.equals("TRUE"))
        {
            return new ResponseEntity<>(BADGATEWAY, HttpStatus.BAD_GATEWAY);
        }
        return this.userService.viewUser(id);
    }

    @GetMapping(path = "/getusers")
    public ResponseEntity<Object> viewUserList(@RequestHeader(value = "accessedThroughGateway") String header)
    {
        if(!header.equals("TRUE"))
        {
            return new ResponseEntity<>(BADGATEWAY, HttpStatus.BAD_GATEWAY);
        }
        return this.userService.viewUsers();
    }
}
