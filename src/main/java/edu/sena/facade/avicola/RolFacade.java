/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Josarta
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "up_avicola")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public boolean addRol(int usuarioid, int rolid) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_usuario_has_tbl_rol (fk_usuarioid,fk_rolid) VALUES ( ?,?)");
            q.setParameter(1, usuarioid);
            q.setParameter(2, rolid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removerRol(int usuarioid, int rolid) {
        try {
            Query q = em.createNativeQuery("DELETE FROM tbl_usuario_has_tbl_rol WHERE fk_usuarioid = ? AND fk_rolid = ?");
            q.setParameter(1, usuarioid);
            q.setParameter(2, rolid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
