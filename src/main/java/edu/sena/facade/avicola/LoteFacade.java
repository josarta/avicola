/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Lote;
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
public class LoteFacade extends AbstractFacade<Lote> implements LoteFacadeLocal {

    @PersistenceContext(unitName = "up_avicola")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoteFacade() {
        super(Lote.class);
    }
    
    

    
    @Override
    public boolean addLote(String cantidad, String color , int fk_huevo, int fk_galpon, int fk_produccion) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_lote (lot_cantidad, lot_color, fk_huevo, fk_galpon, fk_produccion) VALUES (?, ?, ?, ?, ?)");
            q.setParameter(1, cantidad);
            q.setParameter(2, color);
            q.setParameter(3, fk_huevo);
            q.setParameter(4, fk_galpon);
            q.setParameter(5, fk_produccion);            
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
      @Override
    public List<Lote> todosLotes() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Lote u");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    
    
}
