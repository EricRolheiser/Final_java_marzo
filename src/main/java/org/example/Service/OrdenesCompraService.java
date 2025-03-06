package org.example.Service;

import org.example.Model.OrdenesCompra;
import org.example.Repository.OrdenesCompraDAO;

import java.time.LocalDateTime;
import java.util.List;

public class OrdenesCompraService {
    private OrdenesCompraDAO ordenesCompraDAO;

    public OrdenesCompraService(OrdenesCompraDAO ordenesCompraDAO) {
        this.ordenesCompraDAO = ordenesCompraDAO;
    }

    public List<OrdenesCompra> buscarOrdenesCompra(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado, String descripcion) {
        if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        if (estado != null && estado.equals("Activo")) {
            throw new IllegalArgumentException("El estado de la orden de compra no puede ser 'Activo'");
        }
        if (descripcion != null && descripcion.equals("")) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        return ordenesCompraDAO.buscarOrdenes(fechaInicio, fechaFin, estado, descripcion);
    }

    public void CrearOrdenCompra(OrdenesCompra ordenCompra) {
        if (ordenCompra.getFechaPedido() == null) {
            throw new IllegalArgumentException("La fecha de pedido es obligatoria");
        }
        if (ordenCompra.getEstado() == null || ordenCompra.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        if (ordenCompra.getDescripcionArticulo() == null || ordenCompra.getDescripcionArticulo().isEmpty()) {
            throw new IllegalArgumentException("La descripción del artículo es obligatoria");
        }
        ordenesCompraDAO.CrearOrdenCompra(ordenCompra);
    }
}
