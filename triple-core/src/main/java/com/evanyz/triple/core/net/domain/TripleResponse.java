package com.evanyz.triple.core.net.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by evan on 2018/11/12.
 */
@Data
@AllArgsConstructor
public class TripleResponse {
    Object data;

    public static TripleResponse success(Object data) {
        return new TripleResponse(data);
    }

    public static TripleResponse fail(String reason) {
        return new TripleResponse(reason);
    }

}
