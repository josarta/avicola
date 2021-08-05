/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Produccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface ProduccionFacadeLocal {

    void create(Produccion produccion);

    void edit(Produccion produccion);

    void remove(Produccion produccion);

    Produccion find(Object id);

    List<Produccion> findAll();

    List<Produccion> findRange(int[] range);

    int count();
    
}
