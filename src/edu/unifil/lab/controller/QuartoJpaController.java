/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.controller;

import edu.unifil.lab.controller.exceptions.NonexistentEntityException;
import edu.unifil.lab.entity.Quarto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unifil.lab.entity.TipoQuarto;
import edu.unifil.lab.entity.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class QuartoJpaController implements Serializable {

    public QuartoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Quarto quarto) {
        if (quarto.getReservaList() == null) {
            quarto.setReservaList(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoQuarto tipo = quarto.getTipo();
            if (tipo != null) {
                tipo = em.getReference(tipo.getClass(), tipo.getIdTipo());
                quarto.setTipo(tipo);
            }
            List<Reserva> attachedReservaList = new ArrayList<Reserva>();
            for (Reserva reservaListReservaToAttach : quarto.getReservaList()) {
                reservaListReservaToAttach = em.getReference(reservaListReservaToAttach.getClass(), reservaListReservaToAttach.getCodReserva());
                attachedReservaList.add(reservaListReservaToAttach);
            }
            quarto.setReservaList(attachedReservaList);
            em.persist(quarto);
            if (tipo != null) {
                tipo.getQuartoList().add(quarto);
                tipo = em.merge(tipo);
            }
            for (Reserva reservaListReserva : quarto.getReservaList()) {
                Quarto oldIdQuartoOfReservaListReserva = reservaListReserva.getIdQuarto();
                reservaListReserva.setIdQuarto(quarto);
                reservaListReserva = em.merge(reservaListReserva);
                if (oldIdQuartoOfReservaListReserva != null) {
                    oldIdQuartoOfReservaListReserva.getReservaList().remove(reservaListReserva);
                    oldIdQuartoOfReservaListReserva = em.merge(oldIdQuartoOfReservaListReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Quarto quarto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Quarto persistentQuarto = em.find(Quarto.class, quarto.getIdQuarto());
            TipoQuarto tipoOld = persistentQuarto.getTipo();
            TipoQuarto tipoNew = quarto.getTipo();
            List<Reserva> reservaListOld = persistentQuarto.getReservaList();
            List<Reserva> reservaListNew = quarto.getReservaList();
            if (tipoNew != null) {
                tipoNew = em.getReference(tipoNew.getClass(), tipoNew.getIdTipo());
                quarto.setTipo(tipoNew);
            }
            List<Reserva> attachedReservaListNew = new ArrayList<Reserva>();
            for (Reserva reservaListNewReservaToAttach : reservaListNew) {
                reservaListNewReservaToAttach = em.getReference(reservaListNewReservaToAttach.getClass(), reservaListNewReservaToAttach.getCodReserva());
                attachedReservaListNew.add(reservaListNewReservaToAttach);
            }
            reservaListNew = attachedReservaListNew;
            quarto.setReservaList(reservaListNew);
            quarto = em.merge(quarto);
            if (tipoOld != null && !tipoOld.equals(tipoNew)) {
                tipoOld.getQuartoList().remove(quarto);
                tipoOld = em.merge(tipoOld);
            }
            if (tipoNew != null && !tipoNew.equals(tipoOld)) {
                tipoNew.getQuartoList().add(quarto);
                tipoNew = em.merge(tipoNew);
            }
            for (Reserva reservaListOldReserva : reservaListOld) {
                if (!reservaListNew.contains(reservaListOldReserva)) {
                    reservaListOldReserva.setIdQuarto(null);
                    reservaListOldReserva = em.merge(reservaListOldReserva);
                }
            }
            for (Reserva reservaListNewReserva : reservaListNew) {
                if (!reservaListOld.contains(reservaListNewReserva)) {
                    Quarto oldIdQuartoOfReservaListNewReserva = reservaListNewReserva.getIdQuarto();
                    reservaListNewReserva.setIdQuarto(quarto);
                    reservaListNewReserva = em.merge(reservaListNewReserva);
                    if (oldIdQuartoOfReservaListNewReserva != null && !oldIdQuartoOfReservaListNewReserva.equals(quarto)) {
                        oldIdQuartoOfReservaListNewReserva.getReservaList().remove(reservaListNewReserva);
                        oldIdQuartoOfReservaListNewReserva = em.merge(oldIdQuartoOfReservaListNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = quarto.getIdQuarto();
                if (findQuarto(id) == null) {
                    throw new NonexistentEntityException("The quarto with id " + id + " no longer exists.");
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
            Quarto quarto;
            try {
                quarto = em.getReference(Quarto.class, id);
                quarto.getIdQuarto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The quarto with id " + id + " no longer exists.", enfe);
            }
            TipoQuarto tipo = quarto.getTipo();
            if (tipo != null) {
                tipo.getQuartoList().remove(quarto);
                tipo = em.merge(tipo);
            }
            List<Reserva> reservaList = quarto.getReservaList();
            for (Reserva reservaListReserva : reservaList) {
                reservaListReserva.setIdQuarto(null);
                reservaListReserva = em.merge(reservaListReserva);
            }
            em.remove(quarto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Quarto> findQuartoEntities() {
        return findQuartoEntities(true, -1, -1);
    }

    public List<Quarto> findQuartoEntities(int maxResults, int firstResult) {
        return findQuartoEntities(false, maxResults, firstResult);
    }

    private List<Quarto> findQuartoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Quarto.class));
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

    public Quarto findQuarto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Quarto.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuartoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Quarto> rt = cq.from(Quarto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
