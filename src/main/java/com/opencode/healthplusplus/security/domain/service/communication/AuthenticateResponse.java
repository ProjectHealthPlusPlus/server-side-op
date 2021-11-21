package com.opencode.healthplusplus.security.domain.service.communication;

import com.opencode.healthplusplus.security.resource.AuthenticateResource;
import com.opencode.healthplusplus.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {

    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }

}
