
package music.bennington.userservice.service;

import music.bennington.userservice.exception.AccessDeniedException;
import music.bennington.userservice.model.MusicModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserCommentForMusicServiceImp implements UserCommentForMusicService{

	private String musicId="musicId";
    @Autowired
    RestTemplate restApi;

    @Autowired
    MyUserDetailService myUserDetailService;

    private static final Logger LOGGER = Logger.getLogger(UserCommentForMusicServiceImp.class);

    @Override
    public ResponseEntity<Object> addCommentByUser(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/commentonmusic";
            HttpHeaders headers = new HttpHeaders();
            String userId = myUserDetailService.getLoggedInUserId();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("accessedByLoggedInUser", "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put(musicId, musicModel.getMusicId());
            data.put("userId", userId);
            data.put("musicComment", musicModel.getComment());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.POST, entity, String.class);
            
            response = new ResponseEntity<>("COMMENT-ADDED", HttpStatus.OK);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewMyCommentsByUser(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/viewcommentsonmusic";
            HttpHeaders headers = new HttpHeaders();
            String userId = myUserDetailService.getLoggedInUserId();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("accessedByLoggedInUser", "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put(musicId, musicModel.getMusicId());
            data.put("userId", userId);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            response = restApi.exchange(url, HttpMethod.POST, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewAllCommentsByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals("ROLE_ADMIN")){
                throw new AccessDeniedException("You are allowed to Do This");
            }

            String url = "http://music-dashboard/backend/musicservice/viewalluserscommnetonmusic";
            HttpHeaders headers = new HttpHeaders();

            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("accessedByAdmin", "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put(musicId, musicModel.getMusicId());


            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            response = restApi.exchange(url, HttpMethod.POST, entity, Object.class);

        }

        catch (AccessDeniedException e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }
}
