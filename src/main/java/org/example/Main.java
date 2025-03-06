package org.example;

import org.example.Model.OrdenesCompra;
import org.example.Model.Proveedores;
import org.example.Repository.OrdenesCompraDAO;
import org.example.Repository.ProveedoresDAO;
import org.example.Service.OrdenesCompraService;
import org.example.Service.ProveedoresService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        ProveedoresService proveedoresService = new ProveedoresService(new ProveedoresDAO());
        OrdenesCompraService ordenesCompraService = new OrdenesCompraService(new OrdenesCompraDAO());

        try {
            Proveedores nuevoProveedor = new Proveedores("ElectroTech S.A.", "1144556677", "contacto@electrotech.com", "Av. Siempre Viva 1234");
            proveedoresService.CrearProveedor(nuevoProveedor);
            System.out.println("Proveedor creado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear proveedor: " + e.getMessage());
        }

        try {
            System.out.println("Listado de proveedores:");
            proveedoresService.LeerProveedores().forEach(proveedor -> {
                System.out.println(proveedor.getNombre());
            });
        } catch (Exception e) {
            System.out.println("Error al obtener proveedores: " + e.getMessage());
        }

        try {
            proveedoresService.ActualizarProveedor(1, "ElectroTech S.A.", "1144556677", "ventas@electrotech.com", "Av. Siempre Muerta 1234");
            System.out.println("Proveedor actualizado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }

        try {
            proveedoresService.EliminarProveedor(1);
            System.out.println("Proveedor eliminado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }

        try {
            // Crear y persistir el proveedor que se usará en la orden de compra
            Proveedores proveedor = new Proveedores("Distribuidora Delta", "1199887766", "ventas@deltadistribuidora.com", "Calle Principal 5678");
            proveedoresService.CrearProveedor(proveedor);

            // Convertir la fecha de String a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fechaPedido = LocalDateTime.parse("2024-12-15 08:00:00", formatter);

            OrdenesCompra nuevaOrdenCompra = new OrdenesCompra(proveedor, fechaPedido, "Televisores LED 50", 10, "Pendiente");

            ordenesCompraService.CrearOrdenCompra(nuevaOrdenCompra);
            System.out.println("Orden de compra creada con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear orden de compra: " + e.getMessage());
        }
    }
}
