package com.opencode.healthplusplus.security.domain.service.communication;

import com.opencode.healthplusplus.security.resource.UserSecResource;
import com.opencode.healthplusplus.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserSecResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserSecResource resource) {
        super(resource);
    }
}
