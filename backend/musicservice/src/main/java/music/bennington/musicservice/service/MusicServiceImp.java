package music.bennington.musicservice.service;

import music.bennington.musicservice.entity.MusicAlbumEntity;
import music.bennington.musicservice.entity.MusicEntity;
import music.bennington.musicservice.exception.AlbumNotFoundException;
import music.bennington.musicservice.exception.MusicNotFoundException;
import music.bennington.musicservice.repo.MusicAlbumRepo;
import music.bennington.musicservice.repo.MusicRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class MusicServiceImp implements MusicService {

	private static String errorMsg = "Music Album not found with requested Id";
	@Autowired
	MusicRepo musicRepo;

	@Autowired
	MusicAlbumRepo musicAlbumRepo;

	private static final Logger LOGGER = Logger.getLogger(MusicServiceImp.class);

	@Override
	public ResponseEntity<Object> insertMusicAlbum(MusicAlbumEntity musicAlbum) {
		ResponseEntity<Object> response = null;

		try {
			SimpleDateFormat dateFromat = new SimpleDateFormat("ddMMyyHHmmss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			dateFromat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

			String id = "Album_" + dateFromat.format(timestamp);
			musicAlbum.setAlbumId(id);

			musicAlbumRepo.save(musicAlbum);
			response = new ResponseEntity<>("ALBUM_ADDED", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> addMusicToAlbum(String albumId, MusicEntity musicEntity) {
		ResponseEntity<Object> response = null;
		try {
			if (musicAlbumRepo.findById(albumId).isEmpty()) {
				throw new AlbumNotFoundException(errorMsg);
			} else {
				SimpleDateFormat dateFromat = new SimpleDateFormat("ddMMyyHHmmss");
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				dateFromat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

				String id = "Music_" + dateFromat.format(timestamp);
				musicEntity.setMusicId(id);

				MusicAlbumEntity musicAlbumEntity = musicAlbumRepo.findById(albumId).orElse(null);
				musicEntity.setMusicAlbum(musicAlbumEntity);

				musicRepo.save(musicEntity);
				response = new ResponseEntity<>("MUSIC_ADDED", HttpStatus.OK);
			}

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> updateMusicAlbum(String albumId, MusicAlbumEntity oldMusicAlbum) {
		ResponseEntity<Object> response = null;
		try {
			if (musicAlbumRepo.findById(albumId).isEmpty()) {
				throw new AlbumNotFoundException(errorMsg);
			} else {
				MusicAlbumEntity newMusicAlbumEntity = musicAlbumRepo.findById(albumId).orElse(oldMusicAlbum);
				newMusicAlbumEntity.setAlbumName(oldMusicAlbum.getAlbumName());
				newMusicAlbumEntity.setAlbumImageUrl(oldMusicAlbum.getAlbumImageUrl());
				newMusicAlbumEntity.setPublishYear(oldMusicAlbum.getPublishYear());

				musicAlbumRepo.save(newMusicAlbumEntity);
				response = new ResponseEntity<>("ALBUM_UPDATED", HttpStatus.OK);
			}

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> updateMusic(String musicId, MusicEntity oldMusicEntity) {
		ResponseEntity<Object> response = null;
		try {
			if (musicRepo.findById(musicId).isEmpty()) {
				throw new MusicNotFoundException("Music not found with requested Id");
			} else {
				MusicEntity newMusicEntity = musicRepo.findById(musicId).orElse(oldMusicEntity);
				newMusicEntity.setMusicName(oldMusicEntity.getMusicName());
				newMusicEntity.setArtistName(oldMusicEntity.getArtistName());
				newMusicEntity.setMusicUrl(oldMusicEntity.getMusicUrl());
				musicRepo.save(newMusicEntity);
				response = new ResponseEntity<>("MUSIC_UPDATED", HttpStatus.OK);
			}

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
	public ResponseEntity<Object> removeMusicAlbum(String albumId) {
		ResponseEntity<Object> response = null;
		try {
			if (musicAlbumRepo.findById(albumId).isEmpty()) {
				throw new AlbumNotFoundException("Album not found with requested Id");
			} else {
				musicAlbumRepo.deleteById(albumId);
				response = new ResponseEntity<>("ALBUM_DELETED", HttpStatus.OK);
			}

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> removeMusic(String musicId) {
		ResponseEntity<Object> response = null;
		try {
			if (musicRepo.findById(musicId).isEmpty()) {
				throw new MusicNotFoundException("Music not found with requested Id");
			} else {
				Optional<MusicEntity>optional=musicRepo.findById(musicId);
				MusicAlbumEntity musicAlbumEntity=new MusicAlbumEntity();
				if(optional.isPresent()) {
					musicAlbumEntity = optional.get().getMusicAlbum();
				}
				
				List<MusicEntity> musicEntities = musicAlbumEntity.getAlbumMusics();
				musicEntities.remove(musicRepo.findById(musicId).orElse(null));
				musicRepo.deleteById(musicId);
				response = new ResponseEntity<>("MUSIC_DELETED", HttpStatus.OK);
			}

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
	public ResponseEntity<Object> viewAllAlbums() {
		ResponseEntity<Object> response = null;
		try {
			List<MusicAlbumEntity> musicAlbumEntityList = musicAlbumRepo.findAll();
			response = new ResponseEntity<>(musicAlbumEntityList, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> viewAlbumMusics(String albumId) {
		ResponseEntity<Object> response = null;
	
		try {
			if (musicAlbumRepo.findById(albumId).isEmpty()) {
				throw new AlbumNotFoundException(errorMsg);
			}
           else {
        	   Optional<MusicAlbumEntity>optional=musicAlbumRepo.findById(albumId);
        	   List<MusicEntity> musicEntityList=new ArrayList<>();
        	   if(optional.isPresent()) {
        		   musicEntityList = optional.get().getAlbumMusics();
        	   }
               
                response = new ResponseEntity<>(musicEntityList, HttpStatus.OK);
            }
			
	

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> viewAlbumDetails(String albumId) {
		ResponseEntity<Object> response = null;
		try {
			if (musicAlbumRepo.findById(albumId).isEmpty()) {
				throw new AlbumNotFoundException(errorMsg);
			} else {
				MusicAlbumEntity musicAlbumEntity = musicAlbumRepo.findById(albumId).orElse(null);
				response = new ResponseEntity<>(musicAlbumEntity, HttpStatus.OK);
			}

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> viewMusicDetails(String musicId) {
		ResponseEntity<Object> response = null;
		try {
			if (musicRepo.findById(musicId).isEmpty()) {
				throw new AlbumNotFoundException(errorMsg);
			} else {
				MusicEntity musicEntity = musicRepo.findById(musicId).orElse(null);
				response = new ResponseEntity<>(musicEntity, HttpStatus.OK);
			}

		}

		catch (AlbumNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> searchAlbum(String keyword) {
		ResponseEntity<Object> response = null;
		try {
			List<MusicAlbumEntity> musicAlbumEntityList = musicAlbumRepo.findByAlbumIdOrAlbumNameOrPublishYear(keyword,
					keyword, Integer.parseInt(keyword));
			response = new ResponseEntity<>(musicAlbumEntityList, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

	@Override
	public ResponseEntity<Object> searchMusic(String keyword) {
		ResponseEntity<Object> response = null;
		try {
			List<MusicEntity> musicAlbumEntityList = musicRepo.findByMusicIdOrMusicNameOrArtistName(keyword, keyword,
					keyword);
			response = new ResponseEntity<>(musicAlbumEntityList, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug(response);
		return response;
	}

}
