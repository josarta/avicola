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
@Table(name = "tbl_huevo")
@NamedQueries({
    @NamedQuery(name = "Huevo.findAll", query = "SELECT h FROM Huevo h")})
public class Huevo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hue_hievosid")
    private Integer hueHievosid;
    @Size(max = 45)
    @Column(name = "hue_clasificacion")
    private String hueClasificacion;
    @OneToMany(mappedBy = "fkHuevo", fetch = FetchType.LAZY)
    private Collection<Lote> loteCollection;

    public Huevo() {
    }

    public Huevo(Integer hueHievosid) {
        this.hueHievosid = hueHievosid;
    }

    public Integer getHueHievosid() {
        return hueHievosid;
    }

    public void setHueHievosid(Integer hueHievosid) {
        this.hueHievosid = hueHievosid;
    }

    public String getHueClasificacion() {
        return hueClasificacion;
    }

    public void setHueClasificacion(String hueClasificacion) {
        this.hueClasificacion = hueClasificacion;
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
        hash += (hueHievosid != null ? hueHievosid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Huevo)) {
            return false;
        }
        Huevo other = (Huevo) object;
        if ((this.hueHievosid == null && other.hueHievosid != null) || (this.hueHievosid != null && !this.hueHievosid.equals(other.hueHievosid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Huevo[ hueHievosid=" + hueHievosid + " ]";
    }
    
}
