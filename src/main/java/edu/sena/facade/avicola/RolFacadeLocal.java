/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.avicola;

import edu.sena.entity.avicola.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Josarta
 */
@Local
public interface RolFacadeLocal {

    void create(Rol rol);

    void edit(Rol rol);

    void remove(Rol rol);

    Rol find(Object id);

    List<Rol> findAll();

    List<Rol> findRange(int[] range);

    int count();

    public boolean removerRol(int usuarioid, int rolid);

    public boolean addRol(int usuarioid, int rolid);
    
}
