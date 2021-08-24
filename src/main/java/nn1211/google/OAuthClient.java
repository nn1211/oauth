package nn1211.google;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import nn1211.oauth2.AuthForm;
import nn1211.oauth2.DesktopClient;
import nn1211.oauth2.OAuth2Client;
import nn1211.oauth2.TokenForm;

/**
 * A Google OAuth client.
 *
 * @author nn1211
 * @since 1.0
 */
public abstract class OAuthClient extends OAuth2Client {
    
    /**
     * client_secret
     * 
     * @since 1.0
     */
    protected static final String CLIENT_SECRECT = "client_secret";

    OAuthClient(String authURI, String tokenURI) {
        super(authURI, tokenURI);
    }

    public static OAuth2Client forDesktop() throws IOException {
        return new DefaultDesktopClient();
    }

    /**
     * Google OAuth client for desktop apps.
     *
     * @author nn1211
     * @since 1.0
     */
    static class DefaultDesktopClient extends DesktopClient {

        private final String clientId
                = "1089079727992-tas4mfkogmfs8r41boonaog89me0cb1f.apps.googleusercontent.com";

        private final String key = "bZfdwUnVFVF4mP6QMLQ7C15W";
        
        /**
         * Create a new instance.
         *
         * @throws IOException I/O exception
         * @since 1.0
         */
        DefaultDesktopClient() throws IOException {
            super("https://accounts.google.com/o/oauth2/v2/auth",
                    "https://oauth2.googleapis.com/token");
        }

        /**
         *
         * @since 1.0
         */
        @Override
        public void auth(AuthForm form) throws URISyntaxException, IOException {
            if (null == form.clientId()) {
                form.clientId(clientId);
            }

            if (null == form.state()) {
                form.state(new BigInteger(130, new SecureRandom()).toString(32));
            }

            if (null == form.scope()) {
                form.scope("profile", "email");
            }

            super.auth(form);
        }

        /**
         *
         * @since 1.0
         */
        @Override
        public void token(TokenForm form) throws IOException {
            if (null == form.clientId()) {
                form.clientId(clientId);
            }
            
            if (null == form.get(CLIENT_SECRECT)) {
                form.set(CLIENT_SECRECT, key);
            }
            
            super.token(form);
        }

    }

}
