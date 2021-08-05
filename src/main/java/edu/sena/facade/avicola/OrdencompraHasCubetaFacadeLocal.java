/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.OrdencompraHasCubeta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface OrdencompraHasCubetaFacadeLocal {

    void create(OrdencompraHasCubeta ordencompraHasCubeta);

    void edit(OrdencompraHasCubeta ordencompraHasCubeta);

    void remove(OrdencompraHasCubeta ordencompraHasCubeta);

    OrdencompraHasCubeta find(Object id);

    List<OrdencompraHasCubeta> findAll();

    List<OrdencompraHasCubeta> findRange(int[] range);

    int count();
    
}
