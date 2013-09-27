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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q"),
    @NamedQuery(name = "Quarto.findByIdQuarto", query = "SELECT q FROM Quarto q WHERE q.idQuarto = :idQuarto"),
    @NamedQuery(name = "Quarto.findByDescricaoQuarto", query = "SELECT q FROM Quarto q WHERE q.descricaoQuarto = :descricaoQuarto"),
    @NamedQuery(name = "Quarto.findByCapacidade", query = "SELECT q FROM Quarto q WHERE q.capacidade = :capacidade")})
public class Quarto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idQuarto")
    private Integer idQuarto;
    @Column(name = "descricaoQuarto")
    private String descricaoQuarto;
    @Column(name = "capacidade")
    private Integer capacidade;
    @JoinColumn(name = "tipo", referencedColumnName = "idTipo")
    @ManyToOne
    private TipoQuarto tipo;
    @OneToMany(mappedBy = "idQuarto")
    private List<Reserva> reservaList;

    public Quarto() {
    }

    public Quarto(Integer idQuarto) {
        this.idQuarto = idQuarto;
    }

    public Integer getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Integer idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getDescricaoQuarto() {
        return descricaoQuarto;
    }

    public void setDescricaoQuarto(String descricaoQuarto) {
        this.descricaoQuarto = descricaoQuarto;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuarto != null ? idQuarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.idQuarto == null && other.idQuarto != null) || (this.idQuarto != null && !this.idQuarto.equals(other.idQuarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoQuarto;
    }
    
}
