package music.bennington.userservice.service;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.model.LoginModel;
import org.springframework.http.ResponseEntity;

public interface UserService
{
    public ResponseEntity<Object> signupUser(UserEntity userEntity);
    public ResponseEntity<Object> loginUser(LoginModel loginModel);
    public ResponseEntity<Object> viewProfile();

    public ResponseEntity<Object> changeUserRoleOrActiveAccount(String id, UserEntity oldUserEntity);
    public ResponseEntity<Object> updateProfile(UserEntity oldUserEntity);
    public ResponseEntity<Object> removeUser(String id);
    public ResponseEntity<Object> viewUser(String id);
    public ResponseEntity<Object> viewUsers();

    public ResponseEntity<Object> getIdAndRoleOfLoggedInPerson();


}
