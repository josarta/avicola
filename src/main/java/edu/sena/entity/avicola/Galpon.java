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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_galpon")
@NamedQueries({
    @NamedQuery(name = "Galpon.findAll", query = "SELECT g FROM Galpon g")})
public class Galpon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gal_galponid")
    private Integer galGalponid;
    @Column(name = "gal_cantidad")
    private Integer galCantidad;
    @Size(max = 255)
    @Column(name = "gal_ubicacion")
    private String galUbicacion;
    @OneToMany(mappedBy = "fkGalpon", fetch = FetchType.LAZY)
    private Collection<Lote> loteCollection;

    public Galpon() {
    }

    public Galpon(Integer galGalponid) {
        this.galGalponid = galGalponid;
    }

    public Integer getGalGalponid() {
        return galGalponid;
    }

    public void setGalGalponid(Integer galGalponid) {
        this.galGalponid = galGalponid;
    }

    public Integer getGalCantidad() {
        return galCantidad;
    }

    public void setGalCantidad(Integer galCantidad) {
        this.galCantidad = galCantidad;
    }

    public String getGalUbicacion() {
        return galUbicacion;
    }

    public void setGalUbicacion(String galUbicacion) {
        this.galUbicacion = galUbicacion;
    }

    public Collection<Lote> getLoteCollection() {
        return loteCollection;
    }

    public void setLoteCollection(Collection<Lote> loteCollection) {
        this.loteCollection = loteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (galGalponid != null ? galGalponid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Galpon)) {
            return false;
        }
        Galpon other = (Galpon) object;
        if ((this.galGalponid == null && other.galGalponid != null) || (this.galGalponid != null && !this.galGalponid.equals(other.galGalponid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Galpon[ galGalponid=" + galGalponid + " ]";
    }
    
}
