package pl.pollub.cs.pentalearn.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
