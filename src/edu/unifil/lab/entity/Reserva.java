/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Note
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByCodReserva", query = "SELECT r FROM Reserva r WHERE r.codReserva = :codReserva"),
    @NamedQuery(name = "Reserva.findByDataEntrada", query = "SELECT r FROM Reserva r WHERE r.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "Reserva.findByDataSaida", query = "SELECT r FROM Reserva r WHERE r.dataSaida = :dataSaida"),
    @NamedQuery(name = "Reserva.findByPagamento", query = "SELECT r FROM Reserva r WHERE r.pagamento = :pagamento"),
    @NamedQuery(name = "Reserva.findByStatus", query = "SELECT r FROM Reserva r WHERE r.status = :status")})
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codReserva")
    private Integer codReserva;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column(name = "data_saida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pagamento")
    private Double pagamento;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "idVisitante", referencedColumnName = "idVisitante")
    @ManyToOne
    private Visitante idVisitante;
    @JoinColumn(name = "idQuarto", referencedColumnName = "idQuarto")
    @ManyToOne
    private Quarto idQuarto;

    public Reserva() {
    }

    public Reserva(Integer codReserva) {
        this.codReserva = codReserva;
    }

    public Integer getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(Integer codReserva) {
        this.codReserva = codReserva;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Double getPagamento() {
        return pagamento;
    }

    public void setPagamento(Double pagamento) {
        this.pagamento = pagamento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Visitante getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(Visitante idVisitante) {
        this.idVisitante = idVisitante;
    }

    public Quarto getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Quarto idQuarto) {
        this.idQuarto = idQuarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codReserva != null ? codReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codReserva == null && other.codReserva != null) || (this.codReserva != null && !this.codReserva.equals(other.codReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unifil.lab.entity.Reserva[ codReserva=" + codReserva + " ]";
    }
    
}
