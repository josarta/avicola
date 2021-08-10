/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.avicola;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_rolid")
    private Integer rolRolid;
    @Size(max = 45)
    @Column(name = "rol_nombre")
    private String rolNombre;
    @Size(max = 45)
    @Column(name = "rol_descripcion")
    private String rolDescripcion;
    @Size(max = 150)
    @Column(name = "rol_icono")
    private String rolIcono;
    @JoinTable(name = "tbl_usuario_has_tbl_rol", joinColumns = {
        @JoinColumn(name = "fk_rolid", referencedColumnName = "rol_rolid")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_usuarioid", referencedColumnName = "usu_usuarioid")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Usuario> usuarioCollection;

    public Rol() {
    }

    public Rol(Integer rolRolid) {
        this.rolRolid = rolRolid;
    }

    public Integer getRolRolid() {
        return rolRolid;
    }

    public void setRolRolid(Integer rolRolid) {
        this.rolRolid = rolRolid;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }
    
    public String getRolIcono() {
        return rolIcono;
    }

    public void setRolIcono(String rolIcono) {
        this.rolIcono = rolIcono;
    }
    

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolRolid != null ? rolRolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolRolid == null && other.rolRolid != null) || (this.rolRolid != null && !this.rolRolid.equals(other.rolRolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Rol[ rolRolid=" + rolRolid + " ]";
    }

}
