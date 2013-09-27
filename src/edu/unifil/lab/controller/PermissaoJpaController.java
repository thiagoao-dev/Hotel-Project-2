/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.controller;

import edu.unifil.lab.controller.exceptions.IllegalOrphanException;
import edu.unifil.lab.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unifil.lab.entity.Perfil;
import edu.unifil.lab.entity.Permissao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class PermissaoJpaController implements Serializable {

    public PermissaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permissao permissao) {
        if (permissao.getPerfilList() == null) {
            permissao.setPerfilList(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : permissao.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getIdPerfil());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            permissao.setPerfilList(attachedPerfilList);
            em.persist(permissao);
            for (Perfil perfilListPerfil : permissao.getPerfilList()) {
                Permissao oldPermissaoidPermissaoOfPerfilListPerfil = perfilListPerfil.getPermissaoidPermissao();
                perfilListPerfil.setPermissaoidPermissao(permissao);
                perfilListPerfil = em.merge(perfilListPerfil);
                if (oldPermissaoidPermissaoOfPerfilListPerfil != null) {
                    oldPermissaoidPermissaoOfPerfilListPerfil.getPerfilList().remove(perfilListPerfil);
                    oldPermissaoidPermissaoOfPerfilListPerfil = em.merge(oldPermissaoidPermissaoOfPerfilListPerfil);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permissao permissao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permissao persistentPermissao = em.find(Permissao.class, permissao.getIdPermissao());
            List<Perfil> perfilListOld = persistentPermissao.getPerfilList();
            List<Perfil> perfilListNew = permissao.getPerfilList();
            List<String> illegalOrphanMessages = null;
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Perfil " + perfilListOldPerfil + " since its permissaoidPermissao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getIdPerfil());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            permissao.setPerfilList(perfilListNew);
            permissao = em.merge(permissao);
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    Permissao oldPermissaoidPermissaoOfPerfilListNewPerfil = perfilListNewPerfil.getPermissaoidPermissao();
                    perfilListNewPerfil.setPermissaoidPermissao(permissao);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                    if (oldPermissaoidPermissaoOfPerfilListNewPerfil != null && !oldPermissaoidPermissaoOfPerfilListNewPerfil.equals(permissao)) {
                        oldPermissaoidPermissaoOfPerfilListNewPerfil.getPerfilList().remove(perfilListNewPerfil);
                        oldPermissaoidPermissaoOfPerfilListNewPerfil = em.merge(oldPermissaoidPermissaoOfPerfilListNewPerfil);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permissao.getIdPermissao();
                if (findPermissao(id) == null) {
                    throw new NonexistentEntityException("The permissao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permissao permissao;
            try {
                permissao = em.getReference(Permissao.class, id);
                permissao.getIdPermissao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permissao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Perfil> perfilListOrphanCheck = permissao.getPerfilList();
            for (Perfil perfilListOrphanCheckPerfil : perfilListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Permissao (" + permissao + ") cannot be destroyed since the Perfil " + perfilListOrphanCheckPerfil + " in its perfilList field has a non-nullable permissaoidPermissao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(permissao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permissao> findPermissaoEntities() {
        return findPermissaoEntities(true, -1, -1);
    }

    public List<Permissao> findPermissaoEntities(int maxResults, int firstResult) {
        return findPermissaoEntities(false, maxResults, firstResult);
    }

    private List<Permissao> findPermissaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permissao.class));
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

    public Permissao findPermissao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permissao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermissaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Permissao> rt = cq.from(Permissao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
