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
public class MusicLikeDislikeServiceImp implements MusicLikeDislikeService {
 private static String errorMsg="Music not found with requested Id"; 
	private static String musicId = "musicId";
	@Autowired
	RestTemplate restApi;

	@Autowired
	MusicRepo musicRepo;

	private static final Logger LOGGER = Logger.getLogger(MusicLikeDislikeServiceImp.class);

	@Override
	public ResponseEntity<Object> likeOrOmitLike(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;

		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/addormonitlike";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());
			data.put("userId", sendModel.getUserId());

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
			restApi.exchange(url, HttpMethod.POST, entity, String.class);

			response = new ResponseEntity<>("STATUS-CHANGED", HttpStatus.OK);

		}

		catch (MusicNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> disLikeOrOmitDislike(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;
		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/addoromitdislike";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());
			data.put("userId", sendModel.getUserId());

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
			restApi.exchange(url, HttpMethod.POST, entity, String.class);

			response = new ResponseEntity<>("STATUS-CHANGED", HttpStatus.OK);

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
	public ResponseEntity<Object> getLikeCount(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;
		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/likecount";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());

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
	public ResponseEntity<Object> getDislikeCount(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;
		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/disLikeCount";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());

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
	public ResponseEntity<Object> getLikedUsers(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;
		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/showlikeduserslist";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());

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
	public ResponseEntity<Object> getDisLikedUsers(HttpHeaders headers, LikeDislikeSendModel sendModel) {
		ResponseEntity<Object> response = null;
		try {
			String url = "http://likedislike-dashboard/backend/likedislikeservice/showdislikeduserslist";
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			if (musicRepo.findById(sendModel.getMusicId()).isEmpty()) {
				throw new MusicNotFoundException(errorMsg);
			}

			Map<String, Object> data = new HashMap<>();
			data.put(musicId, sendModel.getMusicId());

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
}