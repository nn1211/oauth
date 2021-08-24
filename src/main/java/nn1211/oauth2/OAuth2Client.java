package nn1211.oauth2;

import java.io.Closeable;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * An OAuth2Client client.
 *
 * @author nn1211
 * @since 1.0
 */
public abstract class OAuth2Client implements Closeable {

    /**
     * The URI of authentication step.
     *
     * @since 1.0
     */
    protected final String authURI;

    /**
     * The URI of get token from access code step.
     *
     * @since 1.0
     */
    protected final String tokenURI;

    /**
     * Authentication code or authorization code.
     *
     * @since 1.0
     */
    protected String code;

    /**
     * Determine the authorization process was OK or not.
     *
     * @since 1.0
     */
    protected boolean isAuthOK;

    /**
     * Create an instance with auth and token URIs.
     *
     * @param authURI
     * @param tokenURI
     * @since 1.0
     */
    protected OAuth2Client(String authURI, String tokenURI) {
        this.authURI = authURI;
        this.tokenURI = tokenURI;
    }

    /**
     * Get an OAuth2Client for desktop application.
     *
     * @param authURI
     * @param tokenURI
     * @return an OAuth2Client for desktop application
     * @throws java.io.IOException if couldn't listen localhost's redirection
     * @since 1.0
     */
    public static OAuth2Client forDesktop(String authURI, String tokenURI)
            throws IOException {

        return new DesktopClient(authURI, tokenURI);
    }

    /**
     * Send an authentication request to server.
     *
     * @param form query string data
     * @throws java.net.URISyntaxException Invalid URI syntax
     * @throws java.io.IOException I/O exception
     * @since 1.0
     */
    public abstract void auth(AuthForm form)
            throws URISyntaxException, IOException;

    /**
     * Get an access token from given code.
     *
     * @param form exchange token from authorization code form
     * @throws java.net.URISyntaxException Invalid URI syntax
     * @throws java.io.IOException I/O exception
     * @since 1.0
     */
    public abstract void token(TokenForm form)
            throws URISyntaxException, IOException;

    /**
     * Determine the authorization process was OK or not.
     *
     * @return true if auth process was OK, false otherwise
     * @since 1.0
     */
    public final boolean isAuthOK() {
        return isAuthOK;
    }

    /**
     * Release underlining used resources.
     *
     * @since 1.0
     */
    @Override
    public void close() {
    }
}
