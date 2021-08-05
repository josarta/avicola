/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Lote;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface LoteFacadeLocal {

    void create(Lote lote);

    void edit(Lote lote);

    void remove(Lote lote);

    Lote find(Object id);

    List<Lote> findAll();

    List<Lote> findRange(int[] range);

    int count();
    
}
