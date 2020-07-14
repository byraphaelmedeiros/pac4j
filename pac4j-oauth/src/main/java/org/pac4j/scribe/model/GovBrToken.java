package org.pac4j.scribe.model;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * This class represents a specific Token for ORCiD using OAuth protocol version 2. It could be part of the Scribe library.
 *
 * @author Jens Tinglev
 * @since 1.6.0
 */
public class GovBrToken extends OAuth2AccessToken {

    private static final long serialVersionUID = 3129683748679852572L;
    private String idToken;

    public GovBrToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope, String idToken,
        String response) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, response);
        setIdToken(idToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GovBrToken that = (GovBrToken) o;

        return !(idToken != null ? !idToken.equals(that.idToken) : that.idToken != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idToken != null ? idToken.hashCode() : 0);
        return result;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
