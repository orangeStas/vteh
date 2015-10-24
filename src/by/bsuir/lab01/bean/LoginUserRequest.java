package by.bsuir.lab01.bean;

/**
 * Created by stas- on 9/28/2015.
 */
public class LoginUserRequest extends Request {
    private String userData;

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}
