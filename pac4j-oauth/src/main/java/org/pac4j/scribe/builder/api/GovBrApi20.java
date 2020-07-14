package org.pac4j.scribe.builder.api;

import java.util.Map;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;
import org.pac4j.scribe.extractors.OrcidJsonExtractor;

/**
 * This class represents the OAuth API implementation for ORCiD using OAuth protocol version 2.
 *
 * @author Jens Tinglev
 * @since 1.6.0
 */
public class GovBrApi20 extends DefaultApi20 {

    private static final String AUTH_URL = "https://sso.staging.acesso.gov.br/authorize";
    private static final String TOKEN_URL = "https://sso.staging.acesso.gov.br/token";

    @Override
    public String getAccessTokenEndpoint() {
        return TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {

        return super.getAuthorizationUrl(responseType, apiKey, callback, scope, state, additionalParams);
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTH_URL;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OrcidJsonExtractor.instance();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }
}
