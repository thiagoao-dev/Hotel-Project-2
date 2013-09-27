/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "permissao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissao.findAll", query = "SELECT p FROM Permissao p"),
    @NamedQuery(name = "Permissao.findByIdPermissao", query = "SELECT p FROM Permissao p WHERE p.idPermissao = :idPermissao"),
    @NamedQuery(name = "Permissao.findByCreate", query = "SELECT p FROM Permissao p WHERE p.create = :create"),
    @NamedQuery(name = "Permissao.findByRead", query = "SELECT p FROM Permissao p WHERE p.read = :read"),
    @NamedQuery(name = "Permissao.findByUpdate", query = "SELECT p FROM Permissao p WHERE p.update = :update"),
    @NamedQuery(name = "Permissao.findByDelete", query = "SELECT p FROM Permissao p WHERE p.delete = :delete")})
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPermissao")
    private Integer idPermissao;
    @Column(name = "create")
    private Boolean create;
    @Column(name = "read")
    private Boolean read;
    @Column(name = "update")
    private Boolean update;
    @Column(name = "delete")
    private Boolean delete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permissaoidPermissao")
    private List<Perfil> perfilList;

    public Permissao() {
    }

    public Permissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @XmlTransient
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermissao != null ? idPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.idPermissao == null && other.idPermissao != null) || (this.idPermissao != null && !this.idPermissao.equals(other.idPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unifil.lab.entity.Permissao[ idPermissao=" + idPermissao + " ]";
    }
    
}
