package to;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class User {
    private long userId;
    private String fullName;
    private String billingAddress;
    private String login;
    private String password;

    public User() {
    }

    public User(long userId, String fullName, String billingAddress, String login, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.billingAddress = billingAddress;
        this.login = login;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUserId() != user.getUserId()) return false;
        if (getFullName() != null ? !getFullName().equals(user.getFullName()) : user.getFullName() != null)
            return false;
        if (getBillingAddress() != null ? !getBillingAddress().equals(user.getBillingAddress()) : user.getBillingAddress() != null)
            return false;
        if (!getLogin().equals(user.getLogin())) return false;
        return getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getBillingAddress() != null ? getBillingAddress().hashCode() : 0);
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
