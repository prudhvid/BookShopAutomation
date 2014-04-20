/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.io.Serializable;
import java.util.Collection;
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
 * @author Prudhvi
 */
@Entity
@Table(name = "PUBLISHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publisher.findAll", query = "SELECT p FROM Publisher p"),
    @NamedQuery(name = "Publisher.findById", query = "SELECT p FROM Publisher p WHERE p.id = :id"),
    @NamedQuery(name = "Publisher.findByName", query = "SELECT p FROM Publisher p WHERE p.name = :name"),
    @NamedQuery(name = "Publisher.findByVendor", query = "SELECT p FROM Publisher p WHERE p.vendor = :vendor"),
    @NamedQuery(name = "Publisher.findByAddress", query = "SELECT p FROM Publisher p WHERE p.address = :address"),
    @NamedQuery(name = "Publisher.findByPhone", query = "SELECT p FROM Publisher p WHERE p.phone = :phone"),
    @NamedQuery(name = "Publisher.findByMail", query = "SELECT p FROM Publisher p WHERE p.mail = :mail"),
    @NamedQuery(name = "Publisher.findByOrdernum", query = "SELECT p FROM Publisher p WHERE p.ordernum = :ordernum")})
public class Publisher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "VENDOR")
    private String vendor;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MAIL")
    private String mail;
    @Basic(optional = false)
    @Column(name = "ORDERNUM")
    private int ordernum;
    @OneToMany(mappedBy = "publisher")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "publisher")
    private Collection<Book> bookCollection;

    public Publisher() {
        vendor=name="";
    }

    public Publisher(String nameString) {
        this.name=nameString.toUpperCase();
        vendor="UNKNOWN";
    }

    public Publisher(Integer id, String name, String vendor, int ordernum) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.ordernum = ordernum;
    }
    public Publisher(Publisher p){
        this.id = p.id;
        this.name = p.name;
        this.vendor = p.vendor;
        address=p.address;
        phone=p.phone;
        mail=p.mail;
        this.ordernum = p.ordernum;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
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
        if (!(object instanceof Publisher)) {
            return false;
        }
        Publisher other = (Publisher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Publisher[ id=" + id + " ] \n name="+name+"\n vendor="+vendor+"\nphone="+phone;
    }
    
}
