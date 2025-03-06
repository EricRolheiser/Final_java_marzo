package org.example.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "OrdenesCompra")
public class OrdenesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdenCompra;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedores proveedor;

    private LocalDateTime fechaPedido;
    private String descripcionArticulo;
    private int cantidad;
    private  String estado;


    public OrdenesCompra(Proveedores proveedor, LocalDateTime fechaPedido, String descripcionArticulo, int cantidad, String estado) {
        this.proveedor = proveedor;
        this.fechaPedido = fechaPedido;
        this.descripcionArticulo = descripcionArticulo;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public OrdenesCompra() {
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
