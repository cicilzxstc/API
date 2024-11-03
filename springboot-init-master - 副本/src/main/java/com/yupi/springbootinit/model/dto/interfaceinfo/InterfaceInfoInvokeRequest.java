package com.yupi.springbootinit.model.dto.interfaceinfo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * 调用接口请求体
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}
