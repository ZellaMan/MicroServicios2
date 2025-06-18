package com.orden_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrdenRequest {
    private String cliente;
    private Map<Long, Integer> productos;
}
