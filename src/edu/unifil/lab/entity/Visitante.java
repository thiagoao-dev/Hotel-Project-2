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
@Table(name = "visitante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visitante.findAll", query = "SELECT v FROM Visitante v"),
    @NamedQuery(name = "Visitante.findByIdVisitante", query = "SELECT v FROM Visitante v WHERE v.idVisitante = :idVisitante"),
    @NamedQuery(name = "Visitante.findByNome", query = "SELECT v FROM Visitante v WHERE v.nome = :nome"),
    @NamedQuery(name = "Visitante.findByTelefone", query = "SELECT v FROM Visitante v WHERE v.telefone = :telefone"),
    @NamedQuery(name = "Visitante.findByEmail", query = "SELECT v FROM Visitante v WHERE v.email = :email")})
public class Visitante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVisitante")
    private Integer idVisitante;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "idVisitante")
    private List<Reserva> reservaList;

    public Visitante() {
    }

    public Visitante(Integer idVisitante) {
        this.idVisitante = idVisitante;
    }

    public Integer getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(Integer idVisitante) {
        this.idVisitante = idVisitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (idVisitante != null ? idVisitante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitante)) {
            return false;
        }
        Visitante other = (Visitante) object;
        if ((this.idVisitante == null && other.idVisitante != null) || (this.idVisitante != null && !this.idVisitante.equals(other.idVisitante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
