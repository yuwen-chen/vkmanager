package com.vikde.vkmanager.common.exception;

import com.vikde.vkmanager.common.type.JsonResultType;

/**
 * Created on 2017/7/3.
 *
 * @author vikde
 */
public class NotFoundApiException extends ApiException {
    public NotFoundApiException() {
        super(JsonResultType.SYSTEM_API_NOT_FOUND);
    }
}
