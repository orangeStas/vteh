package by.bsuir.lab01.bean;

/**
 * Created by stas- on 9/28/2015.
 */
public class LoginUserResponse extends BookResponse {
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
