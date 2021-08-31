/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Galpon;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface GalponFacadeLocal {

    void create(Galpon galpon);

    void edit(Galpon galpon);

    void remove(Galpon galpon);

    Galpon find(Object id);

    List<Galpon> findAll();

    List<Galpon> findRange(int[] range);

    int count();

    public boolean registroGalpon(Galpon galponIn);

    public List<Galpon> todosGalpones();

    public Galpon buscarGalponId(int galponId);

    public Galpon validarExistencia(String ubicacion);
    
}
