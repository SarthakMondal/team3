package music.bennington.userservice.service;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.exception.AccessDeniedException;
import music.bennington.userservice.exception.LoginException;
import music.bennington.userservice.model.LoginModel;
import music.bennington.userservice.repo.UserRepo;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

@Service
public class UserServiceImp implements UserService {

	private static final String ERRORMSG="You are allowed to Do This";
	private static final String USERNOTFOUND="User not found by given Id";
	private static final String ROLEADMIN="ROLE_ADMIN";
    @Autowired
    UserRepo userRepo;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImp.class);

    @Override
    public ResponseEntity<Object> signupUser(UserEntity userEntity) {
        ResponseEntity<Object> response = null;

        try {
            if (userEntity.getUserName().equals("") || userEntity.getEmailId().equals("")
                    || userEntity.getUserAge() == 0
                    || userEntity.getMobileNo().equals("") || userEntity.getPassword().equals("")) {
                throw new DataFormatException("All Fields are Mandatory");
            } else if (userRepo.findByEmailId(userEntity.getEmailId()).isPresent()) {
                throw new DataFormatException("Email Id is Already Present.. Try with another Email-Id");
            } else if (!this.isEmailValid(userEntity.getEmailId())) {
                throw new DataFormatException("Email Id is not in Valid Format");
            } else if (userEntity.getMobileNo().length() != 10) {
                throw new DataFormatException("Mobile Length must be 10");
            } else if (userEntity.getUserAge() < 10) {
                throw new DataFormatException("You must be 10 years old");
            } else if (userEntity.getPassword().length() < 6 || userEntity.getPassword().length() > 15) {
                throw new DataFormatException("Password Length must be between 6 to 15");
            }
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
            userRepo.save(userEntity);

            response = new ResponseEntity<>("SIGNEDUP", HttpStatus.OK);
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public ResponseEntity<Object> loginUser(LoginModel loginModel) {
        ResponseEntity<Object> response = null;
        ResponseEntity<String> loginRes = null;

        RestTemplate rest = new RestTemplate();
        String url = "http://localhost:8081/oauth/token";
        String authHeader = "Basic " + "QmVubmluZ3RvbmVfQ2xpZW50SWQ6YmVubmluZ3RvbmVfTFBAc2VjcmV0";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("username", loginModel.getEmailId());
        formData.add("password", loginModel.getPassword());

        HttpHeaders header = new HttpHeaders();
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        header.set("Authorization", authHeader);
        JSONObject temp = new JSONObject();
        HttpEntity<MultiValueMap<String, String>> data = new HttpEntity<>(formData, header);

        try {
            loginRes = rest.exchange(url, HttpMethod.POST, data, String.class);

            if (loginRes.getStatusCode().value() != 200) {
                throw new LoginException("");
            }
            temp = new JSONObject(loginRes.getBody());
            response = new ResponseEntity<>(temp.get("access_token"), HttpStatus.OK);
        }

        catch (LoginException e) {
            temp = new JSONObject(e.getMessage().substring(7, e.getMessage().length()));
            String error = temp.get("error") + ", " + temp.get("error_description");
            response = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewProfile() {
        String id = this.myUserDetailService.getLoggedInUserId();
        ResponseEntity<Object> response = null;

        try {
            if (userRepo.findById(id).isEmpty()) {
                throw new UsernameNotFoundException(USERNOTFOUND);
            }

            UserEntity user = userRepo.findById(id).orElse(null);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }

        catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> changeUserRoleOrActiveAccount(String id, UserEntity oldUserEntity) {
        ResponseEntity<Object> response = null;

        try {

            if (!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            } else if (userRepo.findById(id).isEmpty()) {
                throw new UsernameNotFoundException(USERNOTFOUND);
            } else {
                UserEntity newUserEntity = userRepo.findById(id).orElse(oldUserEntity);
                newUserEntity.setActive(oldUserEntity.isActive());
                newUserEntity.setRole(oldUserEntity.getRole());

                userRepo.save(newUserEntity);

                response = new ResponseEntity<>("USER-UPDATED", HttpStatus.OK);
            }
        }

        catch (AccessDeniedException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> updateProfile(UserEntity oldUserEntity) {
        ResponseEntity<Object> response = null;
        String id = myUserDetailService.getLoggedInUserId();

        try {
            if (userRepo.findById(id).isEmpty()) {
                throw new UsernameNotFoundException(USERNOTFOUND);
            } else if (oldUserEntity.getUserName().equals("") ||
                    oldUserEntity.getMobileNo().equals("") || oldUserEntity.getUserAge() == 0) {
                throw new DataFormatException("All Fields are Mandatory");
            } else if (oldUserEntity.getMobileNo().length() != 10) {
                throw new DataFormatException("Mobile Length must be 10");
            }

            else if (oldUserEntity.getUserAge() < 10) {
                throw new DataFormatException("You must be 10 years old");
            } else {
                UserEntity newUserEntity = userRepo.findById(id).orElse(oldUserEntity);

                newUserEntity.setUserName(oldUserEntity.getUserName());
                newUserEntity.setMobileNo(oldUserEntity.getMobileNo());
                newUserEntity.setUserGender(oldUserEntity.getUserGender());
                newUserEntity.setUserAge(oldUserEntity.getUserAge());

                userRepo.save(newUserEntity);

                response = new ResponseEntity<>("PROFILE-UPDATED", HttpStatus.OK);
            }
        }

        catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> removeUser(String id) {
        ResponseEntity<Object> response = null;

        try {

            if (!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            else if (userRepo.findById(id).isEmpty()) {
                throw new UsernameNotFoundException(USERNOTFOUND);
            }

            else {
                userRepo.deleteById(id);
                response = new ResponseEntity<>("USER-DELETED", HttpStatus.OK);
            }
        }

        catch (AccessDeniedException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewUser(String id) {
        ResponseEntity<Object> response = null;

        try {

            if (!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            else if (userRepo.findById(id).isEmpty()) {
                throw new UsernameNotFoundException(USERNOTFOUND);
            }

            else {
                UserEntity userEntity = userRepo.findById(id).orElse(null);
                response = new ResponseEntity<>(userEntity, HttpStatus.OK);
            }
        }

        catch (AccessDeniedException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewUsers() {
        ResponseEntity<Object> response = null;

        try {

            if (!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            else {
                List<UserEntity> userEntitys = userRepo.findAll();
                response = new ResponseEntity<>(userEntitys, HttpStatus.OK);
            }
        }

        catch (AccessDeniedException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (UsernameNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> getIdAndRoleOfLoggedInPerson() {
        ResponseEntity<Object> response = null;
        try {
            String idAndRole = myUserDetailService.getLoggedInUserId() + " "
                    + myUserDetailService.getLoggedInUserRole();
            response = new ResponseEntity<>(idAndRole, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

}
