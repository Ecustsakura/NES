package com.example.novel_energy_system.common;

import lombok.Getter;

public class SseException extends RuntimeException {

    @Getter
    private Integer code;

    private String msg;

    public SseException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public SseException(String msg) {
        this(1,msg);
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
