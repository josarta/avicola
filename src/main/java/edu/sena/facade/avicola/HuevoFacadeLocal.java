/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Huevo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface HuevoFacadeLocal {

    void create(Huevo huevo);

    void edit(Huevo huevo);

    void remove(Huevo huevo);

    Huevo find(Object id);

    List<Huevo> findAll();

    List<Huevo> findRange(int[] range);

    int count();
    
}
