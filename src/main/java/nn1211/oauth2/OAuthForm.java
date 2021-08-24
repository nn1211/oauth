package nn1211.oauth2;

import nn1211.http.URIEncodedString;

/**
 * A base class for all OAuth form.
 *
 * @author nn1211
 * @since 1.0
 */
public abstract class OAuthForm {

    /**
     *
     * @since 1.0
     */
    protected final URIEncodedString data;

    /**
     * Create an instance with a given {@link URIEncodedString}.
     *
     * @param data an {@link URIEncodedString}
     * @since 1.0
     */
    protected OAuthForm(URIEncodedString data) {
        this.data = data;
    }

    /**
     * client_id
     *
     * @since 1.0
     */
    protected static final String CLIENT_ID = "client_id";

    /**
     * redirect_uri
     *
     * @since 1.0
     */
    protected static final String REDIRECT_URI = "redirect_uri";

    /**
     * state
     *
     * @since 1.0
     */
    protected static final String STATE = "state";

    /**
     * Return the client id.
     *
     * @return the client id
     * @since 1.0
     */
    public final String clientId() {
        return data.get(CLIENT_ID);
    }

    /**
     * Set the client ID.
     *
     * @param value the client ID
     * @since 1.0
     */
    public final void clientId(String value) {
        data.set(CLIENT_ID, value);
    }
    
    /**
     * Get the value of a custom field.
     * 
     * @param name custom field's name
     * @return the value of a custom field
     * @since 1.0
     */
    public final String get(String name) {
        return data.get(name);
    }

    /**
     * Set the redirect URI.
     *
     * @param value redirect URI
     * @since 1.0
     */
    public final void redirectURI(String value) {
        data.set(REDIRECT_URI, value);
    }

    /**
     * Set a custom field.
     *
     * @param name custom field's name
     * @param value custom field's value
     * @since 1.0
     */
    public final void set(String name, String value) {
        data.set(name, value);
    }
}
