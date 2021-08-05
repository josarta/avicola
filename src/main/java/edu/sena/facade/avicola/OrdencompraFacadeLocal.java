/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Ordencompra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface OrdencompraFacadeLocal {

    void create(Ordencompra ordencompra);

    void edit(Ordencompra ordencompra);

    void remove(Ordencompra ordencompra);

    Ordencompra find(Object id);

    List<Ordencompra> findAll();

    List<Ordencompra> findRange(int[] range);

    int count();
    
}
