package com.orden_service.client;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {

    private final RestTemplate restTemplate;

    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductoDto getProductoById(Long id) {
        return restTemplate.getForObject("http://producto-service/api/productos/" + id, ProductoDto.class);
    }

    @Data
    public static class ProductoDto {
        private Long id;
        private String nombre;
        private String descripcion;
        private Double precio;
        private Integer stock;
    }
}
