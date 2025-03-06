package org.example.Service;

import org.example.Model.Proveedores;
import org.example.Repository.ProveedoresDAO;

import java.util.List;

public class ProveedoresService {
    private ProveedoresDAO proveedoresDAO;

    public ProveedoresService(ProveedoresDAO proveedoresDAO) {
        this.proveedoresDAO = proveedoresDAO;
    }

    public void CrearProveedor(Proveedores proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().equals("")) {
            throw new RuntimeException("El nombre del proveedor es requerido");
        }
        if (proveedor.getTelefono() == null || proveedor.getTelefono().equals("")) {
            throw new RuntimeException("El teléfono del proveedor es requerido");
        }
        if (proveedor.getEmail() == null || proveedor.getEmail().equals("")) {
            throw new RuntimeException("El email del proveedor es requerido");
        }
        if (proveedor.getDireccion() == null || proveedor.getDireccion().equals("")) {
            throw new RuntimeException("La dirección del proveedor es requerida");
        }
        proveedoresDAO.CrearProveedor(proveedor);
    }

    public List<Proveedores> LeerProveedores() {
        return proveedoresDAO.LeerProveedores();
    }

    public void ActualizarProveedor(Integer idProveedor, String nombre, String telefono, String email, String direccion) {
        if (idProveedor == null || idProveedor <= 0) {
            throw new IllegalArgumentException("ID de proveedor no válido");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (telefono == null || telefono.length() < 10) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 10 dígitos");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        proveedoresDAO.ActualizarProveedor(idProveedor, nombre, telefono, email, direccion);
    }

    public void EliminarProveedor(Integer idProveedor) {
        if (idProveedor == null || idProveedor <= 0) {
            throw new IllegalArgumentException("ID de proveedor no válido");
        }
        proveedoresDAO.EliminarProveedor(idProveedor);
    }

}
