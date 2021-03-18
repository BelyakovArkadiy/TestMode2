package test;


public class User  {


    private String login;
    private String password;
    private String status;



    public User() {

    }

    public User(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }


    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public void setLogin(String password) {

        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

