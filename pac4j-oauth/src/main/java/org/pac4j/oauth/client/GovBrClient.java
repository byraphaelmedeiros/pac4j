package org.pac4j.oauth.client;

import org.pac4j.oauth.exception.OAuthCredentialsException;
import org.pac4j.oauth.profile.govbr.GovBrProfileDefinition;
import org.pac4j.scribe.builder.api.GovBrApi20;

/**
 * <p>This class is the OAuth client to authenticate users in Gov.br.</p>
 * <p>It returns a {@link org.pac4j.oauth.profile.govbr.GovBrProfile}.</p>
 * <p>More information at https://manual-roteiro-integracao-login-unico.servicos.gov.br/pt/stable/</p>
 *
 * @author Raphael Medeiros
 * @since 4.0.3
 */
public class GovBrClient extends OAuth20Client {

    protected static final String DEFAULT_SCOPE = "/authenticate";

    public GovBrClient() {
        setScope(DEFAULT_SCOPE);
    }

    public GovBrClient(final String key, final String secret) {
        setScope(DEFAULT_SCOPE);
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected void clientInit() {
        configuration.setApi(new GovBrApi20());
        configuration.setProfileDefinition(new GovBrProfileDefinition());
        configuration.setTokenAsHeader(true);
        configuration.setHasBeenCancelledFactory(ctx -> {
            final String error = ctx.getRequestParameter(OAuthCredentialsException.ERROR).orElse(null);
            final String errorDescription = ctx.getRequestParameter(OAuthCredentialsException.ERROR_DESCRIPTION).orElse(null);
            // user has denied permissions
            if ("access_denied".equals(error) && "User denied access".equals(errorDescription)) {
                return true;
            } else {
                return false;
            }
        });

        super.clientInit();
    }

    public String getScope() {
        return getConfiguration().getScope();
    }

    public void setScope(final String scope) {
        getConfiguration().setScope(scope);
    }
}
