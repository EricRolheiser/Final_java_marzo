package org.example.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.Model.OrdenesCompra;
import org.hibernate.Session;
import org.example.connections.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;



public class OrdenesCompraDAO {
    public List<OrdenesCompra> buscarOrdenes(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado, String descripcion) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrdenesCompra> cq = cb.createQuery(OrdenesCompra.class);
            Root<OrdenesCompra> root = cq.from(OrdenesCompra.class);

            Predicate predicate = cb.conjunction();

            if (fechaInicio != null && fechaFin != null) {
                predicate = cb.and(predicate, cb.between(root.get("fechaPedido"), fechaInicio, fechaFin));
            }
            if (estado != null && !estado.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("estado"), estado));
            }
            if (descripcion != null && !descripcion.isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("descripcionArticulo"), "%" + descripcion + "%"));
            }

            cq.select(root).where(predicate);
            return session.createQuery(cq).getResultList();
        }
    }

    public void CrearOrdenCompra(OrdenesCompra ordenCompra) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(ordenCompra);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
