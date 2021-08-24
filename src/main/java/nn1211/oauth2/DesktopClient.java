package nn1211.oauth2;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import nn1211.http.Content;
import nn1211.http.Content.Form;
import nn1211.http.client.ClientRequest;
import nn1211.http.client.ClientResponse;
import nn1211.http.server.HttpServer;
import nn1211.http.server.ServerRequest;
import nn1211.http.server.ServerResponse;
import static nn1211.http.server.ServerResponse.ok;

/**
 * An OAuth2 desktop client.
 *
 * @author nn1211
 * @since 1.0
 */
public class DesktopClient extends OAuth2Client {

    /**
     * /oauth-result
     *
     * @since 1.0
     */
    private static final String REDIRECT_PATH = "/oauth-result";

    /**
     *
     * @since 1.0
     */
    private final HttpServer localhost = new HttpServer()
            .registerHandler("GET " + REDIRECT_PATH, this::onRedirection);

    /**
     *
     * @since 1.0
     */
    private final String redirectURI = "http://localhost:" + localhost.port()
            + REDIRECT_PATH;

    /**
     * Create an instance with given information.
     *
     * @param authURI authorization URI
     * @param tokenURI exchange access token URI
     * @throws IOException if couldn't listen localhost's redirection
     * @since 1.0
     */
    protected DesktopClient(String authURI, String tokenURI)
            throws IOException {

        super(authURI, tokenURI);

        localhost.start();
    }

    /**
     *
     * @since 1.0
     */
    @Override
    public void close() {
        localhost.stop();
    }

    /**
     *
     * @since 1.0
     */
    @Override
    public void auth(AuthForm form) throws URISyntaxException, IOException {
        form.redirectURI(redirectURI);

        Desktop.getDesktop().browse(new URI(authURI + form));
    }

    /**
     *
     * @since 1.0
     */
    @Override
    public void token(TokenForm form) throws IOException {
        form.redirectURI(redirectURI);

        System.out.println(form.data);

        ClientResponse resp = ClientRequest.newBuilder(tokenURI)
                .build()
                .post(Form.newBuilder(form.data).build());

        System.out.println(resp.statusCode());
        Content content = resp.body();
        if (null != content && content.isText()) {
            System.out.println(content.asText());
        }
    }

    /**
     * On redirection handler.
     *
     * @param req a {@link ServerRequest}
     * @return a {@link ServerRequest}
     */
    private ServerResponse onRedirection(ServerRequest req) throws IOException {
        code = req.param(TokenForm.CODE);
        if (null == code) {
            isAuthOK = false;
            return ok("Error occured");
        }

        isAuthOK = true;

        System.out.println(code);

        token(new TokenForm().code(code));

        return ok("You can close the browser tab now");
    }
}
