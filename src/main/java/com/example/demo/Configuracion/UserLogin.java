package configuracion

public class UserLogin {
    
    private Int userid;

    private String username;

    private String hashed_password;

    private Instant token;

    private Instant token_expiration;

    public Int getUserId() {
        return userid;
    }
    public void setUserId(String userid){
        this.userid = user.id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }
    public void setHashed_password(String hashed_password){
        this.hashed_password = hashed_password;
    }

    public boolean isToken() {
        return token;
    }

    public boolean isToken_expired() {
        return ! Instant.now().isBefore(token_expires);
    }

}