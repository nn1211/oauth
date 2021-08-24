package test.oauth2;

import java.io.IOException;
import java.net.URISyntaxException;
import nn1211.google.OAuthClient;
import nn1211.oauth2.AuthForm;
import nn1211.oauth2.OAuth2Client;

/**
 * Google OAuth client test cases.
 * 
 * @author nn1211
 */
public class TestGGAuthClient {

    public static void main(String[] args) throws IOException, URISyntaxException {
        OAuth2Client c = OAuthClient.forDesktop();
        
        AuthForm authForm = new AuthForm();
        c.auth(authForm);
        
        
    }
}
