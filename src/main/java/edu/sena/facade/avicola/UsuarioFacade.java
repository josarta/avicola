/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Josarta
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "up_avicola")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario usuarioLogin(String usuCorreo, String usuClave) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuClave = :usuClave AND u.usuCorreoelectronico = :usuCorreo");
            q.setParameter("usuClave", usuClave);
            q.setParameter("usuCorreo", usuCorreo);
            return (Usuario) q.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean registroUsusario(Usuario usuReg) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_usuario (usu_tipodocumento, usu_numerodocumento, usu_nombres, usu_apellidos, usu_correoelectronico, usu_clave, usu_estado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            q.setParameter(1, usuReg.getUsuTipodocumento());
            q.setParameter(2, usuReg.getUsuNumerodocumento());
            q.setParameter(3, usuReg.getUsuNombres());
            q.setParameter(4, usuReg.getUsuApellidos());
            q.setParameter(5, usuReg.getUsuCorreoelectronico());
            q.setParameter(6, usuReg.getUsuClave());
            q.setParameter(7, usuReg.getUsuEstado());
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Usuario recuperarClave(String usuCorreo) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCorreoelectronico = :usuCorreo");
            q.setParameter("usuCorreo", usuCorreo);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Usuario> todosUsuarios() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioId(int usuarioId) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuUsuarioid = :usuarioId");
            q.setParameter("usuarioId", usuarioId);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario validarExistencia(String correoIn) {
        try {
            Query q = em.createQuery("SELECT c FROM Usuario c WHERE c.usuCorreoelectronico LIKE CONCAT('%',:correoIn,'%')");
            q.setParameter("correoIn", correoIn);
            List<Usuario> listaG = q.getResultList();
            if (listaG.isEmpty()) {
                return null;
            } else {
                return listaG.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

}
