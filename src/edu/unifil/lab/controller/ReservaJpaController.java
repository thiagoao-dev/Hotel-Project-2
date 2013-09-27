/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.controller;

import edu.unifil.lab.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unifil.lab.entity.Visitante;
import edu.unifil.lab.entity.Quarto;
import edu.unifil.lab.entity.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Visitante idVisitante = reserva.getIdVisitante();
            if (idVisitante != null) {
                idVisitante = em.getReference(idVisitante.getClass(), idVisitante.getIdVisitante());
                reserva.setIdVisitante(idVisitante);
            }
            Quarto idQuarto = reserva.getIdQuarto();
            if (idQuarto != null) {
                idQuarto = em.getReference(idQuarto.getClass(), idQuarto.getIdQuarto());
                reserva.setIdQuarto(idQuarto);
            }
            em.persist(reserva);
            if (idVisitante != null) {
                idVisitante.getReservaList().add(reserva);
                idVisitante = em.merge(idVisitante);
            }
            if (idQuarto != null) {
                idQuarto.getReservaList().add(reserva);
                idQuarto = em.merge(idQuarto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getCodReserva());
            Visitante idVisitanteOld = persistentReserva.getIdVisitante();
            Visitante idVisitanteNew = reserva.getIdVisitante();
            Quarto idQuartoOld = persistentReserva.getIdQuarto();
            Quarto idQuartoNew = reserva.getIdQuarto();
            if (idVisitanteNew != null) {
                idVisitanteNew = em.getReference(idVisitanteNew.getClass(), idVisitanteNew.getIdVisitante());
                reserva.setIdVisitante(idVisitanteNew);
            }
            if (idQuartoNew != null) {
                idQuartoNew = em.getReference(idQuartoNew.getClass(), idQuartoNew.getIdQuarto());
                reserva.setIdQuarto(idQuartoNew);
            }
            reserva = em.merge(reserva);
            if (idVisitanteOld != null && !idVisitanteOld.equals(idVisitanteNew)) {
                idVisitanteOld.getReservaList().remove(reserva);
                idVisitanteOld = em.merge(idVisitanteOld);
            }
            if (idVisitanteNew != null && !idVisitanteNew.equals(idVisitanteOld)) {
                idVisitanteNew.getReservaList().add(reserva);
                idVisitanteNew = em.merge(idVisitanteNew);
            }
            if (idQuartoOld != null && !idQuartoOld.equals(idQuartoNew)) {
                idQuartoOld.getReservaList().remove(reserva);
                idQuartoOld = em.merge(idQuartoOld);
            }
            if (idQuartoNew != null && !idQuartoNew.equals(idQuartoOld)) {
                idQuartoNew.getReservaList().add(reserva);
                idQuartoNew = em.merge(idQuartoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reserva.getCodReserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getCodReserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Visitante idVisitante = reserva.getIdVisitante();
            if (idVisitante != null) {
                idVisitante.getReservaList().remove(reserva);
                idVisitante = em.merge(idVisitante);
            }
            Quarto idQuarto = reserva.getIdQuarto();
            if (idQuarto != null) {
                idQuarto.getReservaList().remove(reserva);
                idQuarto = em.merge(idQuarto);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva findReserva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
