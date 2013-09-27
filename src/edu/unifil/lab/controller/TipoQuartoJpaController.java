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
import edu.unifil.lab.entity.Quarto;
import edu.unifil.lab.entity.TipoQuarto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class TipoQuartoJpaController implements Serializable {

    public TipoQuartoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoQuarto tipoQuarto) {
        if (tipoQuarto.getQuartoList() == null) {
            tipoQuarto.setQuartoList(new ArrayList<Quarto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Quarto> attachedQuartoList = new ArrayList<Quarto>();
            for (Quarto quartoListQuartoToAttach : tipoQuarto.getQuartoList()) {
                quartoListQuartoToAttach = em.getReference(quartoListQuartoToAttach.getClass(), quartoListQuartoToAttach.getIdQuarto());
                attachedQuartoList.add(quartoListQuartoToAttach);
            }
            tipoQuarto.setQuartoList(attachedQuartoList);
            em.persist(tipoQuarto);
            for (Quarto quartoListQuarto : tipoQuarto.getQuartoList()) {
                TipoQuarto oldTipoOfQuartoListQuarto = quartoListQuarto.getTipo();
                quartoListQuarto.setTipo(tipoQuarto);
                quartoListQuarto = em.merge(quartoListQuarto);
                if (oldTipoOfQuartoListQuarto != null) {
                    oldTipoOfQuartoListQuarto.getQuartoList().remove(quartoListQuarto);
                    oldTipoOfQuartoListQuarto = em.merge(oldTipoOfQuartoListQuarto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoQuarto tipoQuarto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoQuarto persistentTipoQuarto = em.find(TipoQuarto.class, tipoQuarto.getIdTipo());
            List<Quarto> quartoListOld = persistentTipoQuarto.getQuartoList();
            List<Quarto> quartoListNew = tipoQuarto.getQuartoList();
            List<Quarto> attachedQuartoListNew = new ArrayList<Quarto>();
            for (Quarto quartoListNewQuartoToAttach : quartoListNew) {
                quartoListNewQuartoToAttach = em.getReference(quartoListNewQuartoToAttach.getClass(), quartoListNewQuartoToAttach.getIdQuarto());
                attachedQuartoListNew.add(quartoListNewQuartoToAttach);
            }
            quartoListNew = attachedQuartoListNew;
            tipoQuarto.setQuartoList(quartoListNew);
            tipoQuarto = em.merge(tipoQuarto);
            for (Quarto quartoListOldQuarto : quartoListOld) {
                if (!quartoListNew.contains(quartoListOldQuarto)) {
                    quartoListOldQuarto.setTipo(null);
                    quartoListOldQuarto = em.merge(quartoListOldQuarto);
                }
            }
            for (Quarto quartoListNewQuarto : quartoListNew) {
                if (!quartoListOld.contains(quartoListNewQuarto)) {
                    TipoQuarto oldTipoOfQuartoListNewQuarto = quartoListNewQuarto.getTipo();
                    quartoListNewQuarto.setTipo(tipoQuarto);
                    quartoListNewQuarto = em.merge(quartoListNewQuarto);
                    if (oldTipoOfQuartoListNewQuarto != null && !oldTipoOfQuartoListNewQuarto.equals(tipoQuarto)) {
                        oldTipoOfQuartoListNewQuarto.getQuartoList().remove(quartoListNewQuarto);
                        oldTipoOfQuartoListNewQuarto = em.merge(oldTipoOfQuartoListNewQuarto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoQuarto.getIdTipo();
                if (findTipoQuarto(id) == null) {
                    throw new NonexistentEntityException("The tipoQuarto with id " + id + " no longer exists.");
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
            TipoQuarto tipoQuarto;
            try {
                tipoQuarto = em.getReference(TipoQuarto.class, id);
                tipoQuarto.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoQuarto with id " + id + " no longer exists.", enfe);
            }
            List<Quarto> quartoList = tipoQuarto.getQuartoList();
            for (Quarto quartoListQuarto : quartoList) {
                quartoListQuarto.setTipo(null);
                quartoListQuarto = em.merge(quartoListQuarto);
            }
            em.remove(tipoQuarto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoQuarto> findTipoQuartoEntities() {
        return findTipoQuartoEntities(true, -1, -1);
    }

    public List<TipoQuarto> findTipoQuartoEntities(int maxResults, int firstResult) {
        return findTipoQuartoEntities(false, maxResults, firstResult);
    }

    private List<TipoQuarto> findTipoQuartoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoQuarto.class));
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

    public TipoQuarto findTipoQuarto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoQuarto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoQuartoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoQuarto> rt = cq.from(TipoQuarto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
