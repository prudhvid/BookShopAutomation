/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

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
 * @author Prudhvi
 */
@Entity
@Table(name = "RESERVES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserves.findAll", query = "SELECT r FROM Reserves r"),
    @NamedQuery(name = "Reserves.findById", query = "SELECT r FROM Reserves r WHERE r.id = :id"),
    @NamedQuery(name = "Reserves.findByType", query = "SELECT r FROM Reserves r WHERE r.type = :type"),
    @NamedQuery(name = "Reserves.findByCopies", query = "SELECT r FROM Reserves r WHERE r.copies = :copies"),
    @NamedQuery(name = "Reserves.findByDate", query = "SELECT r FROM Reserves r WHERE r.date = :date")})
public class Reserves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TYPE")
    private Character type;
    @Column(name = "COPIES")
    private Integer copies;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
    @ManyToOne
    private Customer customer;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne
    private Book book;

    public Reserves() {
    }

    public Reserves(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserves)) {
            return false;
        }
        Reserves other = (Reserves) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Reserves[ id=" + id + " ]";
    }
    
}
