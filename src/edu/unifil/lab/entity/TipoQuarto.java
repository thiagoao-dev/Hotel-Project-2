/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Note
 */
@Entity
@Table(name = "tipo_quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoQuarto.findAll", query = "SELECT t FROM TipoQuarto t"),
    @NamedQuery(name = "TipoQuarto.findByIdTipo", query = "SELECT t FROM TipoQuarto t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoQuarto.findByDescricao", query = "SELECT t FROM TipoQuarto t WHERE t.descricao = :descricao")})
public class TipoQuarto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo")
    private Integer idTipo;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "tipo")
    private List<Quarto> quartoList;

    public TipoQuarto() {
    }

    public TipoQuarto(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Quarto> getQuartoList() {
        return quartoList;
    }

    public void setQuartoList(List<Quarto> quartoList) {
        this.quartoList = quartoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoQuarto)) {
            return false;
        }
        TipoQuarto other = (TipoQuarto) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
