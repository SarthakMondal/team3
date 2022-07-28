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
public class UserMusicServiceImp implements UserMusicService
{
	private static final String  ROLEADMIN="ROLE_ADMIN";
	private static final String  ADMINEMAIL="addedByAdminEmail";
	private static final String  LOGGEDINUSER="accessedByLoggedInUser";
	private static final String  ACCESSEDBYADMIN="accessedByAdmin";
	private static final String  ERRORMSG="You are not allowed to do This";
    @Autowired
    RestTemplate restApi;

    @Autowired
    MyUserDetailService myUserDetailService;

    private static final Logger LOGGER = Logger.getLogger(UserMusicServiceImp.class);

    @Override
    public ResponseEntity<Object> addAlbumByAdmin(MusicModel musicModel)
    {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/addmusicalbum";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put("albumName", musicModel.getAlbumName());
            data.put("publishYear", musicModel.getPublishYear());
            data.put("albumImageUrl", musicModel.getAlbumImageUrl());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.POST, entity, String.class);
            
            response = new ResponseEntity<>("ALBUM-ADDED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> addMusicToAlbumByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/addmusictoalbum/" + musicModel.getAlbumId();
            HttpHeaders headers = new HttpHeaders();
            String id = myUserDetailService.getLoggedInUserId();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put("musicName", musicModel.getMusicName());
            data.put("artistName", musicModel.getArtistName());
            data.put("musicUrl", musicModel.getMusicUrl());
            data.put(ADMINEMAIL, id);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.POST, entity, String.class);
            response = new ResponseEntity<>("Music-ADDED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> updateAlbumByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/updatealbum/" + musicModel.getAlbumId();
            HttpHeaders headers = new HttpHeaders();
            String id = myUserDetailService.getLoggedInUserId();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");


            Map<String, Object> data = new HashMap<>();
            data.put("albumName", musicModel.getAlbumName());
            data.put("publishYear", musicModel.getPublishYear());
            data.put("albumImageUrl", musicModel.getAlbumImageUrl());
            data.put(ADMINEMAIL, id);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.PUT, entity, String.class);
            
            response = new ResponseEntity<>("ALBUM-UPDATED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> updateMusicByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/updatemusic/" + musicModel.getMusicId();
            HttpHeaders headers = new HttpHeaders();
            String id = myUserDetailService.getLoggedInUserId();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");

            Map<String, Object> data = new HashMap<>();
            data.put("musicName", musicModel.getMusicName());
            data.put("artistName", musicModel.getArtistName());
            data.put("musicUrl", musicModel.getMusicUrl());
            data.put(ADMINEMAIL, id);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
            restApi.exchange(url, HttpMethod.PUT, entity, String.class);
            
            response = new ResponseEntity<>("Music-UPDATED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> removeAlbumByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/deletealbum/" + musicModel.getAlbumId();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            restApi.exchange(url, HttpMethod.DELETE, entity, String.class);
            
            response = new ResponseEntity<>("ALBUM-DELETED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> removeMusicByAdmin(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {
            if(!myUserDetailService.getLoggedInUserRole().equals(ROLEADMIN)) {
                throw new AccessDeniedException(ERRORMSG);
            }

            String url = "http://music-dashboard/backend/musicservice/deletemusic/" + musicModel.getMusicId();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(ACCESSEDBYADMIN, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            restApi.exchange(url, HttpMethod.DELETE, entity, String.class);
            
            response = new ResponseEntity<>("Music-DELETED", HttpStatus.OK);

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

    @Override
    public ResponseEntity<Object> viewAllAlbums() {
        ResponseEntity<Object> response = null;

        try {
            String url = "http://music-dashboard/backend/musicservice/viewallalbums";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewAlbumSongs(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/viewmusicsofalbum/" + musicModel.getAlbumId();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewAlbum(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/viewalbum/" + musicModel.getAlbumId();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewMusic(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/viewmusic/" + musicModel.getMusicId();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> searchAlbum(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/searchalbum/" + musicModel.getKeyword();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> searchMusic(MusicModel musicModel) {
        ResponseEntity<Object> response = null;

        try {

            String url = "http://music-dashboard/backend/musicservice/searchmusic/" + musicModel.getKeyword();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(LOGGEDINUSER, "TRUE");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
            response = restApi.exchange(url, HttpMethod.GET, entity, Object.class);

        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug(response);
        return response;
    }
}
