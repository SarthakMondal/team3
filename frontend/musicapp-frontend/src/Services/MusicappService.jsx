import httpRequest from "axios";
import Session from "./Session";

const mainUrl ="http://localhost:8080/musicapp/backend"

class MusicappService{
  loginUser(loginModel){

    let config = {
        headers: {
            //authorization: "bearer " + token
        }
    }
    return httpRequest.post(mainUrl+"/user/public/login", loginModel, config);
  }

  getActiveIdAndRole(){
   
    const session = new Session();
    let token = session.getAuthToken();

    let config = {
        headers: {
            authorization: "bearer " + token
        }
    }
    return httpRequest.get(mainUrl+"/user/protected/getuseridandrole", config);
  }
}

export default MusicappService