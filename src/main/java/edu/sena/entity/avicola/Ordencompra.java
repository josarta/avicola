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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Josarta
 */
@Entity
@Table(name = "tbl_ordencompra")
@NamedQueries({
    @NamedQuery(name = "Ordencompra.findAll", query = "SELECT o FROM Ordencompra o")})
public class Ordencompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orc_ordencompraid")
    private Integer orcOrdencompraid;
    @Column(name = "orc_fecha")
    @Temporal(TemporalType.DATE)
    private Date orcFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "orc_valortotal")
    private Double orcValortotal;
    @Column(name = "orc_impuesto")
    private Double orcImpuesto;
    @Size(max = 255)
    @Column(name = "orc_direccion")
    private String orcDireccion;
    @Size(max = 45)
    @Column(name = "orc_telefono")
    private String orcTelefono;
    @OneToMany(mappedBy = "fkOrdendecompra", fetch = FetchType.LAZY)
    private Collection<OrdencompraHasCubeta> ordencompraHasCubetaCollection;
    @JoinColumn(name = "fk_cliente", referencedColumnName = "usu_usuarioid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario fkCliente;

    public Ordencompra() {
    }

    public Ordencompra(Integer orcOrdencompraid) {
        this.orcOrdencompraid = orcOrdencompraid;
    }

    public Integer getOrcOrdencompraid() {
        return orcOrdencompraid;
    }

    public void setOrcOrdencompraid(Integer orcOrdencompraid) {
        this.orcOrdencompraid = orcOrdencompraid;
    }

    public Date getOrcFecha() {
        return orcFecha;
    }

    public void setOrcFecha(Date orcFecha) {
        this.orcFecha = orcFecha;
    }

    public Double getOrcValortotal() {
        return orcValortotal;
    }

    public void setOrcValortotal(Double orcValortotal) {
        this.orcValortotal = orcValortotal;
    }

    public Double getOrcImpuesto() {
        return orcImpuesto;
    }

    public void setOrcImpuesto(Double orcImpuesto) {
        this.orcImpuesto = orcImpuesto;
    }

    public String getOrcDireccion() {
        return orcDireccion;
    }

    public void setOrcDireccion(String orcDireccion) {
        this.orcDireccion = orcDireccion;
    }

    public String getOrcTelefono() {
        return orcTelefono;
    }

    public void setOrcTelefono(String orcTelefono) {
        this.orcTelefono = orcTelefono;
    }

    public Collection<OrdencompraHasCubeta> getOrdencompraHasCubetaCollection() {
        return ordencompraHasCubetaCollection;
    }

    public void setOrdencompraHasCubetaCollection(Collection<OrdencompraHasCubeta> ordencompraHasCubetaCollection) {
        this.ordencompraHasCubetaCollection = ordencompraHasCubetaCollection;
    }

    public Usuario getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Usuario fkCliente) {
        this.fkCliente = fkCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orcOrdencompraid != null ? orcOrdencompraid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordencompra)) {
            return false;
        }
        Ordencompra other = (Ordencompra) object;
        if ((this.orcOrdencompraid == null && other.orcOrdencompraid != null) || (this.orcOrdencompraid != null && !this.orcOrdencompraid.equals(other.orcOrdencompraid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityavicola.Ordencompra[ orcOrdencompraid=" + orcOrdencompraid + " ]";
    }
    
}
