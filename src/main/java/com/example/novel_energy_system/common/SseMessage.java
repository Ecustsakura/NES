package com.example.novel_energy_system.common;
import lombok.Data;

@Data
public class SseMessage {

    /**
     * 状态码 200为成功，其他为失败
     * HttpServletResponse.SC_OK
     * HttpServletResponse.SC_INTERNAL_SERVER_ERROR
     */
    private Integer code;

    /**
     * 消息类型
     *
     */
    private Integer type;
    /**
     * 数据
     */
    private String data;

    /**
     * 推送时间
     */
    private String putTime;
}
