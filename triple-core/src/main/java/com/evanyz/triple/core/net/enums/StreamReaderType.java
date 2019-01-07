package com.evanyz.triple.core.net.enums;

/**
 * Created by evan on 2019/1/6.
 */
public enum StreamReaderType {

    TRIPLE_DEFAULT_TYPE(1, "默认流处理方式"),
    ;

    int type;
    String desc;

    StreamReaderType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
