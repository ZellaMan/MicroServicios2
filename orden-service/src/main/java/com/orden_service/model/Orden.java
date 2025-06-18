package com.orden_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private LocalDateTime fecha;

    @ElementCollection
    @CollectionTable(
        name = "orden_productos",
        joinColumns = @JoinColumn(name = "orden_id")
    )
    @MapKeyColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<Long, Integer> productos;
}