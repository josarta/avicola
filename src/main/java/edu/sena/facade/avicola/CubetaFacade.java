/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Cubeta;
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
public class CubetaFacade extends AbstractFacade<Cubeta> implements CubetaFacadeLocal {

    @PersistenceContext(unitName = "up_avicola")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CubetaFacade() {
        super(Cubeta.class);
    }

    @Override
    public boolean registroCubeta(Cubeta cubetaIn) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_cubeta (cub_tipo, cub_descripcion, cub_valor, cun_cantidad) VALUES (?, ?, ?, ?)");
            q.setParameter(1, cubetaIn.getCubTipo());
            q.setParameter(2, cubetaIn.getCubDescripcion());
            q.setParameter(3, cubetaIn.getCubValor());
            q.setParameter(4, cubetaIn.getCunCantidad());

            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean cargaFotoCubeta(int fotoIn , int cubetaIn) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_cubeta_has_tbl_foto (fk_cubeta,fk_foto) VALUES(?,?)");
            q.setParameter(2, fotoIn);
            q.setParameter(1, cubetaIn);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
      @Override
    public boolean removerFotoCubeta(int fotoIn , int cubetaIn) {
        try {
            Query q = em.createNativeQuery("DELETE FROM tbl_cubeta_has_tbl_foto WHERE fk_cubeta = ? AND fk_foto = ?");
            q.setParameter(2, fotoIn);
            q.setParameter(1, cubetaIn);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    

    @Override
    public List<Cubeta> todasCubetas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Cubeta u");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cubeta buscarCubetaId(int cubetaId) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Cubeta u WHERE u.cubCubetaid = :cubetaId");
            q.setParameter("cubetaId", cubetaId);
            return (Cubeta) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    @Override
    public Cubeta validarExistencia(String tipo) {
        try {
            Query q = em.createQuery("SELECT c FROM Cubeta c WHERE c.cubTipo LIKE CONCAT('%',:tipo,'%')");
            q.setParameter("tipo", tipo);
            List<Cubeta> listaG = q.getResultList();
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
