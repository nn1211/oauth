package nn1211.oauth2;

import nn1211.http.QueryString;

/**
 * An OAuth authorization form.
 *
 * @author nn1211
 * @since 1.0
 */
public class AuthForm extends OAuthForm {

    /**
     * response_type
     *
     * @since 1.0
     */
    private static final String RESPONSE_TYPE = "response_type";

    /**
     * scope
     *
     * @since 1.0
     */
    private static final String SCOPE = "scope";

    /**
     * A space character (32)
     *
     * @since 1.0
     */
    private static final char SPACE = ' ';

    /**
     * Create a authorization form for specific response type.
     *
     * @param responseType response type
     * @since 1.0
     */
    public AuthForm(ResponseType responseType) {
        super(new QueryString());
        
        data.set(RESPONSE_TYPE, responseType.value);
    }

    /**
     * Create a code authorization form.
     *
     * @since 1.0
     */
    public AuthForm() {
        this(ResponseType.CODE);
    }

    /**
     * Return the response type.
     *
     * @return the response type
     * @since 1.0
     */
    public final String responseType() {
        return data.get(RESPONSE_TYPE);
    }

    /**
     * Return the scope(s)
     *
     * @return the scope(s)
     * @since 1.0
     */
    public final String scope() {
        return data.get(SCOPE);
    }

    /**
     * Set the scope(s).
     *
     * @param values scope(s)
     * @return this
     * @since 1.0
     */
    public final AuthForm scope(String... values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(value).append(SPACE);
        }

        sb.deleteCharAt(sb.length() - 1);

        data.set(SCOPE, sb.toString());
        return this;
    }

    /**
     * Return the state value.
     *
     * @return the state value
     * @since 1.0
     */
    public final String state() {
        return data.get(STATE);
    }

    /**
     * Set the state.
     *
     * @param value state value
     * @return this
     * @since 1.0
     */
    public final AuthForm state(String value) {
        data.set(STATE, value);
        return this;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Available response type.
     *
     * @author nn1211
     */
    public enum ResponseType {
        /**
         * code
         *
         * @since 1.0
         */
        CODE("code");

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
        ResponseType(String value) {
            this.value = value;
        }

    }

}
