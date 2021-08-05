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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_cubeta")
@NamedQueries({
    @NamedQuery(name = "Cubeta.findAll", query = "SELECT c FROM Cubeta c")})
public class Cubeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cub_cubetaid")
    private Integer cubCubetaid;
    @Size(max = 45)
    @Column(name = "cub_tipo")
    private String cubTipo;
    @Size(max = 45)
    @Column(name = "cub_descripcion")
    private String cubDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cub_valor")
    private Double cubValor;
    @Column(name = "cun_cantidad")
    private Integer cunCantidad;
    @JoinTable(name = "tbl_cubeta_has_tbl_foto", joinColumns = {
        @JoinColumn(name = "fk_cubeta", referencedColumnName = "cub_cubetaid")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_foto", referencedColumnName = "fot_fotoid")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Foto> fotoCollection;
    @OneToMany(mappedBy = "fkCubetas", fetch = FetchType.LAZY)
    private Collection<OrdencompraHasCubeta> ordencompraHasCubetaCollection;

    public Cubeta() {
    }

    public Cubeta(Integer cubCubetaid) {
        this.cubCubetaid = cubCubetaid;
    }

    public Integer getCubCubetaid() {
        return cubCubetaid;
    }

    public void setCubCubetaid(Integer cubCubetaid) {
        this.cubCubetaid = cubCubetaid;
    }

    public String getCubTipo() {
        return cubTipo;
    }

    public void setCubTipo(String cubTipo) {
        this.cubTipo = cubTipo;
    }

    public String getCubDescripcion() {
        return cubDescripcion;
    }

    public void setCubDescripcion(String cubDescripcion) {
        this.cubDescripcion = cubDescripcion;
    }

    public Double getCubValor() {
        return cubValor;
    }

    public void setCubValor(Double cubValor) {
        this.cubValor = cubValor;
    }

    public Integer getCunCantidad() {
        return cunCantidad;
    }

    public void setCunCantidad(Integer cunCantidad) {
        this.cunCantidad = cunCantidad;
    }

    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    public Collection<OrdencompraHasCubeta> getOrdencompraHasCubetaCollection() {
        return ordencompraHasCubetaCollection;
    }

    public void setOrdencompraHasCubetaCollection(Collection<OrdencompraHasCubeta> ordencompraHasCubetaCollection) {
        this.ordencompraHasCubetaCollection = ordencompraHasCubetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cubCubetaid != null ? cubCubetaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cubeta)) {
            return false;
        }
        Cubeta other = (Cubeta) object;
        if ((this.cubCubetaid == null && other.cubCubetaid != null) || (this.cubCubetaid != null && !this.cubCubetaid.equals(other.cubCubetaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Cubeta[ cubCubetaid=" + cubCubetaid + " ]";
    }
    
}
