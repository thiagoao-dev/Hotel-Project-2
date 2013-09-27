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
import edu.unifil.lab.entity.Reserva;
import edu.unifil.lab.entity.Visitante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class VisitanteJpaController implements Serializable {

    public VisitanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Visitante visitante) {
        if (visitante.getReservaList() == null) {
            visitante.setReservaList(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservaList = new ArrayList<Reserva>();
            for (Reserva reservaListReservaToAttach : visitante.getReservaList()) {
                reservaListReservaToAttach = em.getReference(reservaListReservaToAttach.getClass(), reservaListReservaToAttach.getCodReserva());
                attachedReservaList.add(reservaListReservaToAttach);
            }
            visitante.setReservaList(attachedReservaList);
            em.persist(visitante);
            for (Reserva reservaListReserva : visitante.getReservaList()) {
                Visitante oldIdVisitanteOfReservaListReserva = reservaListReserva.getIdVisitante();
                reservaListReserva.setIdVisitante(visitante);
                reservaListReserva = em.merge(reservaListReserva);
                if (oldIdVisitanteOfReservaListReserva != null) {
                    oldIdVisitanteOfReservaListReserva.getReservaList().remove(reservaListReserva);
                    oldIdVisitanteOfReservaListReserva = em.merge(oldIdVisitanteOfReservaListReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Visitante visitante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Visitante persistentVisitante = em.find(Visitante.class, visitante.getIdVisitante());
            List<Reserva> reservaListOld = persistentVisitante.getReservaList();
            List<Reserva> reservaListNew = visitante.getReservaList();
            List<Reserva> attachedReservaListNew = new ArrayList<Reserva>();
            for (Reserva reservaListNewReservaToAttach : reservaListNew) {
                reservaListNewReservaToAttach = em.getReference(reservaListNewReservaToAttach.getClass(), reservaListNewReservaToAttach.getCodReserva());
                attachedReservaListNew.add(reservaListNewReservaToAttach);
            }
            reservaListNew = attachedReservaListNew;
            visitante.setReservaList(reservaListNew);
            visitante = em.merge(visitante);
            for (Reserva reservaListOldReserva : reservaListOld) {
                if (!reservaListNew.contains(reservaListOldReserva)) {
                    reservaListOldReserva.setIdVisitante(null);
                    reservaListOldReserva = em.merge(reservaListOldReserva);
                }
            }
            for (Reserva reservaListNewReserva : reservaListNew) {
                if (!reservaListOld.contains(reservaListNewReserva)) {
                    Visitante oldIdVisitanteOfReservaListNewReserva = reservaListNewReserva.getIdVisitante();
                    reservaListNewReserva.setIdVisitante(visitante);
                    reservaListNewReserva = em.merge(reservaListNewReserva);
                    if (oldIdVisitanteOfReservaListNewReserva != null && !oldIdVisitanteOfReservaListNewReserva.equals(visitante)) {
                        oldIdVisitanteOfReservaListNewReserva.getReservaList().remove(reservaListNewReserva);
                        oldIdVisitanteOfReservaListNewReserva = em.merge(oldIdVisitanteOfReservaListNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = visitante.getIdVisitante();
                if (findVisitante(id) == null) {
                    throw new NonexistentEntityException("The visitante with id " + id + " no longer exists.");
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
            Visitante visitante;
            try {
                visitante = em.getReference(Visitante.class, id);
                visitante.getIdVisitante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The visitante with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservaList = visitante.getReservaList();
            for (Reserva reservaListReserva : reservaList) {
                reservaListReserva.setIdVisitante(null);
                reservaListReserva = em.merge(reservaListReserva);
            }
            em.remove(visitante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Visitante> findVisitanteEntities() {
        return findVisitanteEntities(true, -1, -1);
    }

    public List<Visitante> findVisitanteEntities(int maxResults, int firstResult) {
        return findVisitanteEntities(false, maxResults, firstResult);
    }

    private List<Visitante> findVisitanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Visitante.class));
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

    public Visitante findVisitante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Visitante.class, id);
        } finally {
            em.close();
        }
    }

    public int getVisitanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Visitante> rt = cq.from(Visitante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
