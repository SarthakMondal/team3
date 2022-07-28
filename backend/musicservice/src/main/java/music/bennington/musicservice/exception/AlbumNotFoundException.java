package music.bennington.musicservice.exception;

public class AlbumNotFoundException extends Exception{
    public AlbumNotFoundException(String message){
        super(message);
    }
}
