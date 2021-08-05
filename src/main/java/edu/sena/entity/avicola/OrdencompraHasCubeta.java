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

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_ordencompra_has_tbl_cubeta")
@NamedQueries({
    @NamedQuery(name = "OrdencompraHasCubeta.findAll", query = "SELECT o FROM OrdencompraHasCubeta o")})
public class OrdencompraHasCubeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orc_odencompracubetaid")
    private Integer orcOdencompracubetaid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "orc_valorcompra")
    private Double orcValorcompra;
    @Column(name = "orc_cantidadcomprada")
    private Integer orcCantidadcomprada;
    @JoinColumn(name = "fk_cubetas", referencedColumnName = "cub_cubetaid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cubeta fkCubetas;
    @JoinColumn(name = "fk_ordendecompra", referencedColumnName = "orc_ordencompraid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordencompra fkOrdendecompra;

    public OrdencompraHasCubeta() {
    }

    public OrdencompraHasCubeta(Integer orcOdencompracubetaid) {
        this.orcOdencompracubetaid = orcOdencompracubetaid;
    }

    public Integer getOrcOdencompracubetaid() {
        return orcOdencompracubetaid;
    }

    public void setOrcOdencompracubetaid(Integer orcOdencompracubetaid) {
        this.orcOdencompracubetaid = orcOdencompracubetaid;
    }

    public Double getOrcValorcompra() {
        return orcValorcompra;
    }

    public void setOrcValorcompra(Double orcValorcompra) {
        this.orcValorcompra = orcValorcompra;
    }

    public Integer getOrcCantidadcomprada() {
        return orcCantidadcomprada;
    }

    public void setOrcCantidadcomprada(Integer orcCantidadcomprada) {
        this.orcCantidadcomprada = orcCantidadcomprada;
    }

    public Cubeta getFkCubetas() {
        return fkCubetas;
    }

    public void setFkCubetas(Cubeta fkCubetas) {
        this.fkCubetas = fkCubetas;
    }

    public Ordencompra getFkOrdendecompra() {
        return fkOrdendecompra;
    }

    public void setFkOrdendecompra(Ordencompra fkOrdendecompra) {
        this.fkOrdendecompra = fkOrdendecompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orcOdencompracubetaid != null ? orcOdencompracubetaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdencompraHasCubeta)) {
            return false;
        }
        OrdencompraHasCubeta other = (OrdencompraHasCubeta) object;
        if ((this.orcOdencompracubetaid == null && other.orcOdencompracubetaid != null) || (this.orcOdencompracubetaid != null && !this.orcOdencompracubetaid.equals(other.orcOdencompracubetaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.OrdencompraHasCubeta[ orcOdencompracubetaid=" + orcOdencompracubetaid + " ]";
    }
    
}
