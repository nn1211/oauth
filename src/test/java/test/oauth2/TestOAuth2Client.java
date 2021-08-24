package test.oauth2;

import java.io.IOException;
import java.net.URISyntaxException;
import nn1211.oauth2.AuthForm;
import nn1211.oauth2.OAuth2Client;

/**
 * OAuth2Client test cases.
 * 
 * @author nn1211
 * @since 1.0
 */
public class TestOAuth2Client {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String authURI = "https://accounts.google.com/o/oauth2/v2/auth";
        String clientId = "1089079727992-tas4mfkogmfs8r41boonaog89me0cb1f.apps.googleusercontent.com";
        OAuth2Client c  = OAuth2Client.forDesktop(authURI, null);
        AuthForm authForm = new AuthForm();
        authForm.clientId(clientId);
        
        c.auth(authForm);
        c.close();
    }
}
