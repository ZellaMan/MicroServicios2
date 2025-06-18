package com.orden_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orden_service.client.ProductoClient;
import com.orden_service.dto.OrdenRequest;
import com.orden_service.model.Orden;
import com.orden_service.repository.OrdenRepository;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenRepository repository;
    private final ProductoClient productoClient;

    public OrdenController(OrdenRepository repository, ProductoClient productoClient) {
        this.repository = repository;
        this.productoClient = productoClient;
    }

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody OrdenRequest request) {
        Map<Long, Integer> productos = request.getProductos();

        for (Map.Entry<Long, Integer> entry : productos.entrySet()) {
            Long productoId = entry.getKey();
            Integer cantidad = entry.getValue();

            var producto = productoClient.getProductoById(productoId);

            if (producto == null) {
                return ResponseEntity.badRequest().body("Producto ID " + productoId + " no encontrado.");
            }

            if (producto.getStock() < cantidad) {
                return ResponseEntity.badRequest().body("Stock insuficiente para: " + producto.getNombre());
            }
        }

        Orden orden = Orden.builder()
                .cliente(request.getCliente())
                .fecha(LocalDateTime.now())
                .productos(productos)
                .build();

        return ResponseEntity.ok(repository.save(orden));
    }

    @GetMapping
    public ResponseEntity<?> listarOrdenes() {
        return ResponseEntity.ok(repository.findAll());
    }
}
