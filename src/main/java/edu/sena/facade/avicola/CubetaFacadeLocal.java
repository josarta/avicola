/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Cubeta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface CubetaFacadeLocal {

    void create(Cubeta cubeta);

    void edit(Cubeta cubeta);

    void remove(Cubeta cubeta);

    Cubeta find(Object id);

    List<Cubeta> findAll();

    List<Cubeta> findRange(int[] range);

    int count();
    
}
