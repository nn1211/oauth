package nn1211.oauth2;

import nn1211.http.URIEncodedString;

/**
 * An exchange token form.
 *
 * @author nn1211
 * @since 1.0
 */
public class TokenForm extends OAuthForm {

    /**
     * code
     *
     * @since 1.0
     */
    public static final String CODE = "code";

    /**
     * grant_type
     *
     * @since 1.0
     */
    private static final String GRANT_TYPE = "grant_type";

    /**
     * Create an exchange token form for specific grant type.
     *
     * @param grantType grant type
     */
    public TokenForm(GrantType grantType) {
        super(new URIEncodedString());

        data.set(GRANT_TYPE, grantType.value);
    }

    /**
     * Create an exchange token from auth code form.
     *
     * @since 1.0
     */
    public TokenForm() {
        this(GrantType.CODE);
    }

    /**
     * Set the authorization code.
     *
     * @param value the authorization code
     * @return this
     * @since 1.0
     */
    public final TokenForm code(String value) {
        data.set(CODE, value);
        return this;
    }

    /**
     * Available grant type.
     *
     * @author nn1211
     * @since 1.0
     */
    public enum GrantType {
        /**
         * code
         *
         * @since 1.0
         */
        CODE("authorization_code");

        /**
         *
         * @since 1.0
         */
        private final String value;

        /**
         * Create an instance with a given code that is a lower string.
         *
         * @param value code
         * @since 1.0
         */
        GrantType(String value) {
            this.value = value;
        }

    }

}
