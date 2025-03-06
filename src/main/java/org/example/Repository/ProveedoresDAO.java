package org.example.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Model.Proveedores;
import org.example.connections.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProveedoresDAO {
    public void CrearProveedor(Proveedores proveedor) {
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(proveedor);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Proveedores> LeerProveedores() {
        try(Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Proveedores> cq = cb.createQuery(Proveedores.class);
            Root<Proveedores> root = cq.from(Proveedores.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    public void ActualizarProveedor(Integer idProveedor, String nombre, String telefono, String email, String direccion) {
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Proveedores proveedor = session.get(Proveedores.class, idProveedor);
            if(proveedor != null) {
                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                proveedor.setEmail(email);
                proveedor.setDireccion(direccion);
                session.update(proveedor);
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EliminarProveedor(Integer idProveedor) {
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Proveedores proveedor = session.get(Proveedores.class, idProveedor);
            if(proveedor != null) {
                session.delete(proveedor);
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
