/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.avicola;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_produccion")
@NamedQueries({
    @NamedQuery(name = "Produccion.findAll", query = "SELECT p FROM Produccion p")})
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_produccionid")
    private Integer proProduccionid;
    @Column(name = "pro_fecha")
    @Temporal(TemporalType.DATE)
    private Date proFecha;
    @Column(name = "pro_cantidadtotal")
    private Integer proCantidadtotal;
    @OneToMany(mappedBy = "fkProduccion", fetch = FetchType.LAZY)
    private Collection<Lote> loteCollection;

    public Produccion() {
    }

    public Produccion(Integer proProduccionid) {
        this.proProduccionid = proProduccionid;
    }

    public Integer getProProduccionid() {
        return proProduccionid;
    }

    public void setProProduccionid(Integer proProduccionid) {
        this.proProduccionid = proProduccionid;
    }

    public Date getProFecha() {
        return proFecha;
    }

    public void setProFecha(Date proFecha) {
        this.proFecha = proFecha;
    }

    public Integer getProCantidadtotal() {
        return proCantidadtotal;
    }

    public void setProCantidadtotal(Integer proCantidadtotal) {
        this.proCantidadtotal = proCantidadtotal;
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
        hash += (proProduccionid != null ? proProduccionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.proProduccionid == null && other.proProduccionid != null) || (this.proProduccionid != null && !this.proProduccionid.equals(other.proProduccionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Produccion[ proProduccionid=" + proProduccionid + " ]";
    }
    
}
