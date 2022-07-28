package music.bennington.musicservice.service;

import music.bennington.musicservice.exception.MusicNotFoundException;
import music.bennington.musicservice.model.LikeDislikeSendModel;
import music.bennington.musicservice.repo.MusicRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicCommentServiceImp implements MusicCommentService {

	private static final String ERRORMSG="Music not found with requested Id";
	private static final String MUSICID="MusicId";
    @Autowired
    RestTemplate restApi;

    @Autowired
    MusicRepo musicRepo;

    private static final Logger LOGGER = Logger.getLogger(MusicCommentServiceImp.class);

    @Override
    public ResponseEntity<Object> addCommentToMusic(HttpHeaders headers, LikeDislikeSendModel sendModel) {
        ResponseEntity<Object> response = null;

        try {
            String url = "http://likedislike-dashboard/backend/likedislikeservice/commentsong";
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
                throw new MusicNotFoundException(ERRORMSG);
            }

            Map<String, Object> data = new HashMap<>();
            data.put(MUSICID, sendModel.getMusicId());
            data.put("userId", sendModel.getUserId());
            data.put("musicComment", sendModel.getMusicComment());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.POST, entity, String.class);
            
            response = new ResponseEntity<>("COMMENT-ADDED", HttpStatus.OK);

        }

        catch (MusicNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewUsersCommentsOnMusic(HttpHeaders headers, LikeDislikeSendModel sendModel) {
        ResponseEntity<Object> response = null;

        try {
            String url = "http://likedislike-dashboard/backend/likedislikeservice/mycomments";
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
                throw new MusicNotFoundException(ERRORMSG);
            }

            Map<String, Object> data = new HashMap<>();
            data.put(MUSICID, sendModel.getMusicId());
            data.put("userId", sendModel.getUserId());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            response = restApi.exchange(url, HttpMethod.POST, entity, Object.class);

        }

        catch (MusicNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewAllCommentsOnMusic(HttpHeaders headers, LikeDislikeSendModel sendModel) {
        ResponseEntity<Object> response = null;

        try {
            String url = "http://likedislike-dashboard/backend/likedislikeservice/commentsonmusic";
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
                throw new MusicNotFoundException(ERRORMSG);
            }

            Map<String, Object> data = new HashMap<>();
            data.put(MUSICID, sendModel.getMusicId());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            response = restApi.exchange(url, HttpMethod.POST, entity, Object.class);

        }

        catch (MusicNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }
}
