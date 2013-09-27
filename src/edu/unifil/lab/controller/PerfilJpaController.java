/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.controller;

import edu.unifil.lab.controller.exceptions.NonexistentEntityException;
import edu.unifil.lab.entity.Perfil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unifil.lab.entity.Permissao;
import edu.unifil.lab.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Note
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        if (perfil.getUsuarioList() == null) {
            perfil.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permissao permissaoidPermissao = perfil.getPermissaoidPermissao();
            if (permissaoidPermissao != null) {
                permissaoidPermissao = em.getReference(permissaoidPermissao.getClass(), permissaoidPermissao.getIdPermissao());
                perfil.setPermissaoidPermissao(permissaoidPermissao);
            }
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : perfil.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            perfil.setUsuarioList(attachedUsuarioList);
            em.persist(perfil);
            if (permissaoidPermissao != null) {
                permissaoidPermissao.getPerfilList().add(perfil);
                permissaoidPermissao = em.merge(permissaoidPermissao);
            }
            for (Usuario usuarioListUsuario : perfil.getUsuarioList()) {
                usuarioListUsuario.getPerfilList().add(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getIdPerfil());
            Permissao permissaoidPermissaoOld = persistentPerfil.getPermissaoidPermissao();
            Permissao permissaoidPermissaoNew = perfil.getPermissaoidPermissao();
            List<Usuario> usuarioListOld = persistentPerfil.getUsuarioList();
            List<Usuario> usuarioListNew = perfil.getUsuarioList();
            if (permissaoidPermissaoNew != null) {
                permissaoidPermissaoNew = em.getReference(permissaoidPermissaoNew.getClass(), permissaoidPermissaoNew.getIdPermissao());
                perfil.setPermissaoidPermissao(permissaoidPermissaoNew);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            perfil.setUsuarioList(usuarioListNew);
            perfil = em.merge(perfil);
            if (permissaoidPermissaoOld != null && !permissaoidPermissaoOld.equals(permissaoidPermissaoNew)) {
                permissaoidPermissaoOld.getPerfilList().remove(perfil);
                permissaoidPermissaoOld = em.merge(permissaoidPermissaoOld);
            }
            if (permissaoidPermissaoNew != null && !permissaoidPermissaoNew.equals(permissaoidPermissaoOld)) {
                permissaoidPermissaoNew.getPerfilList().add(perfil);
                permissaoidPermissaoNew = em.merge(permissaoidPermissaoNew);
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getPerfilList().remove(perfil);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getPerfilList().add(perfil);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getIdPerfil();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            Permissao permissaoidPermissao = perfil.getPermissaoidPermissao();
            if (permissaoidPermissao != null) {
                permissaoidPermissao.getPerfilList().remove(perfil);
                permissaoidPermissao = em.merge(permissaoidPermissao);
            }
            List<Usuario> usuarioList = perfil.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getPerfilList().remove(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
