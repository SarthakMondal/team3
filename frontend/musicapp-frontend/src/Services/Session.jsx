class Session {

    loginUserAndAndCreateSession(id, role, token){
        sessionStorage.setItem("USER_ID", id);
        sessionStorage.setItem("USER_ROLE", role);
        sessionStorage.setItem("AUTH_TOKEN", token);
    }
 
    logoutUserAndAndDestroySession(){
        sessionStorage.removeItem("USER_ID");
        sessionStorage.removeItem("USER_ROLE");
        sessionStorage.removeItem("AUTH_TOKEN");
        sessionStorage.clear();
    }

    getUserId(){ return localStorage.getItem("USER_ID");}
    getUserRole(){ return localStorage.getItem("USER_ROLE");}
    getAuthToken(){ return localStorage.getItem("AUTH_TOKEN");}
}

export default Session