/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.avicola;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_lote")
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l")})
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lot_loteid")
    private Integer lotLoteid;
    @Column(name = "lot_cantidad")
    private Integer lotCantidad;
    @Size(max = 45)
    @Column(name = "lot_color")
    private String lotColor;
    @JoinColumn(name = "fk_galpon", referencedColumnName = "gal_galponid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Galpon fkGalpon;
    @JoinColumn(name = "fk_huevo", referencedColumnName = "hue_hievosid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Huevo fkHuevo;
    @JoinColumn(name = "fk_produccion", referencedColumnName = "pro_produccionid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produccion fkProduccion;

    public Lote() {
    }

    public Lote(Integer lotLoteid) {
        this.lotLoteid = lotLoteid;
    }

    public Integer getLotLoteid() {
        return lotLoteid;
    }

    public void setLotLoteid(Integer lotLoteid) {
        this.lotLoteid = lotLoteid;
    }

    public Integer getLotCantidad() {
        return lotCantidad;
    }

    public void setLotCantidad(Integer lotCantidad) {
        this.lotCantidad = lotCantidad;
    }

    public String getLotColor() {
        return lotColor;
    }

    public void setLotColor(String lotColor) {
        this.lotColor = lotColor;
    }

    public Galpon getFkGalpon() {
        return fkGalpon;
    }

    public void setFkGalpon(Galpon fkGalpon) {
        this.fkGalpon = fkGalpon;
    }

    public Huevo getFkHuevo() {
        return fkHuevo;
    }

    public void setFkHuevo(Huevo fkHuevo) {
        this.fkHuevo = fkHuevo;
    }

    public Produccion getFkProduccion() {
        return fkProduccion;
    }

    public void setFkProduccion(Produccion fkProduccion) {
        this.fkProduccion = fkProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lotLoteid != null ? lotLoteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.lotLoteid == null && other.lotLoteid != null) || (this.lotLoteid != null && !this.lotLoteid.equals(other.lotLoteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Lote[ lotLoteid=" + lotLoteid + " ]";
    }
    
}
