/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Galpon;
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
public class GalponFacade extends AbstractFacade<Galpon> implements GalponFacadeLocal {

    @PersistenceContext(unitName = "up_avicola")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GalponFacade() {
        super(Galpon.class);
    }

    @Override
    public boolean registroGalpon(Galpon galponIn) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_galpon (gal_cantidad, gal_ubicacion) VALUES (?, ?)");
            q.setParameter(1, galponIn.getGalCantidad());
            q.setParameter(2, galponIn.getGalUbicacion());
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Galpon> todosGalpones() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT g FROM Galpon g");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Galpon buscarGalponId(int galponId) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT g FROM Galpon g WHERE g.galGalponid = :galponId");
            q.setParameter("galponId", galponId);
            return (Galpon) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Galpon validarExistencia(String ubicacion) {
        try {
            Query q = em.createQuery("SELECT g FROM Galpon g WHERE g.galUbicacion LIKE CONCAT('%',:ubicacion,'%')");
            q.setParameter("ubicacion", ubicacion);
            List<Galpon> listaG = q.getResultList();
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
