package ie.cduffy.trackmypints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credentials")
public class Consumer {

    @Id
    private String id;

    private String username;
    private String hashedPassword;
    private boolean verified;

    public Consumer(String username, String hashedPassword){
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.verified = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
