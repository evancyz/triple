package com.evanyz.triple.core.net.domain;

import lombok.Data;

/**
 * Created by evan on 2018/11/12.
 */
@Data
public class TripleRequest {
    public String serviceName;
    public String methodName;
    public Object[] params;
}
