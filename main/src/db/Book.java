/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import dbfunctions.Bookf;
import dbfunctions.Publisherf;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByEdition", query = "SELECT b FROM Book b WHERE b.edition = :edition"),
    @NamedQuery(name = "Book.findByCopiesBought", query = "SELECT b FROM Book b WHERE b.copiesBought = :copiesBought"),
    @NamedQuery(name = "Book.findByCopiesPresent", query = "SELECT b FROM Book b WHERE b.copiesPresent = :copiesPresent"),
    @NamedQuery(name = "Book.findByCopiesReserved", query = "SELECT b FROM Book b WHERE b.copiesReserved = :copiesReserved"),
    @NamedQuery(name = "Book.findBySellprice", query = "SELECT b FROM Book b WHERE b.sellprice = :sellprice"),
    @NamedQuery(name = "Book.findByBuyingprice", query = "SELECT b FROM Book b WHERE b.buyingprice = :buyingprice"),
    @NamedQuery(name = "Book.findByState", query = "SELECT b FROM Book b WHERE b.state = :state"),
    @NamedQuery(name = "Book.findByTitlenPublisher",query = "SELECT b FROM Book b WHERE b.publisher.name=:publisher AND b.title=:title")})
public class Book implements Serializable {
    protected Integer views;
    @Column(name = "COPIES_SOLD")
    protected Integer copiesSold;
    @OneToMany(mappedBy = "book")
    protected Collection<Bookauthor> bookauthorCollection;
    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Column(name = "ISBN")
    protected String isbn;
    @Basic(optional = false)
    @Column(name = "TITLE")
    protected String title;
    @Basic(optional = false)
    @Column(name = "EDITION")
    protected int edition;
    @Basic(optional = false)
    @Column(name = "COPIES_BOUGHT")
    protected int copiesBought;
    @Basic(optional = false)
    @Column(name = "REQUESTED_COPIES_PRESENT")
    protected int requestedCopiesPresent;
    
    @Basic(optional = false)
    @Column(name = "COPIES_PRESENT")
    protected int copiesPresent;
    @Basic(optional = false)
    @Column(name = "COPIES_RESERVED")
    protected int copiesReserved;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SELLPRICE")
    protected Float sellprice;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BUYINGPRICE")
    protected Float buyingprice;
    @Column(name = "STATE")
    protected Character state;
    @OneToMany(mappedBy = "book")
    protected Collection<Reserves> reservesCollection;
    @JoinColumn(name = "PUBLISHER", referencedColumnName = "ID")
    @ManyToOne
    protected Publisher publisher;
    @JoinColumn(name = "BISAC", referencedColumnName = "ID")
    @ManyToOne
    protected Bisac bisac;
    @OneToMany(mappedBy = "book")
    protected Collection<Invoice> invoiceCollection;
    @OneToMany(mappedBy = "book")
    protected Collection<Sales> salesCollection;

    public Book() {
        views=copiesBought=copiesPresent=copiesReserved=copiesSold=0;
        edition=1;
        state=Bookf.DATA_GIVEN;
        title="";
        sellprice=buyingprice=(float)0;
        isbn=null;
    }
    
    public Book(String titleString) {
        title=titleString.toUpperCase();
        views=copiesBought=copiesPresent=copiesReserved=copiesSold=0;
        edition=1;
        state=Bookf.DATA_GIVEN;
        sellprice=buyingprice=(float)0;
        publisher=Publisherf.getEmptyPublisher();
        isbn=null;
    }


    public  Book(Book b)
    {
        this.id = b.id;
        isbn=b.isbn;
        this.title = b.title;
        this.edition = b.edition;
        this.copiesBought = b.copiesBought;
        this.copiesPresent = b.copiesPresent;
        this.copiesReserved = b.copiesReserved;
        this.isbn=b.isbn;
        id=b.id;
        sellprice=b.sellprice;
        this.buyingprice=b.buyingprice;
        this.bisac=b.bisac;
        state=b.state;
        publisher=b.publisher;
        views=b.views;
        copiesSold=b.copiesSold;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title.toUpperCase();
    }

    public void setTitle(String title) {
        this.title = title.toUpperCase();
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
    public int getCopiesBought() {
        return copiesBought;
    }

    public void setCopiesBought(int copiesBought) {
        this.copiesBought = copiesBought;
    }

    public int getCopiesPresent() {
        return copiesPresent;
    }

    public void setCopiesPresent(int copiesPresent) {
        this.copiesPresent = copiesPresent;
    }

    public int getCopiesReserved() {
        return copiesReserved;
    }

    public void setCopiesReserved(int copiesReserved) {
        this.copiesReserved = copiesReserved;
    }
    public int getRequestedCopiesPresent(){
        return requestedCopiesPresent;
    }
    public void setRequestedCopiesPresent(int copies){
        requestedCopiesPresent=copies;
    }
    public Float getSellprice() {
        return sellprice;
    }

    public void setSellprice(Float sellprice) {
        this.sellprice = sellprice;
    }

    public Float getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Float buyingprice) {
        this.buyingprice = buyingprice;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }
    

    @XmlTransient
    public Collection<Reserves> getReservesCollection() {
        return reservesCollection;
    }

    public void setReservesCollection(Collection<Reserves> reservesCollection) {
        this.reservesCollection = reservesCollection;
    }

    public Publisher getPublisher() {
        if(publisher==null)
            return Publisherf.getEmptyPublisher();
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Bisac getBisac() {
        return bisac;
    }

    public void setBisac(Bisac bisac) {
        this.bisac = bisac;
    }
    
    
            
    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "id="+id+"\ntitle="+title+"\ncopiesreserved="+copiesReserved;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }
    public void setDescription(String desc){
        description=desc;
    }
    public String getDescription(){
        return description;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }

    @XmlTransient
    public Collection<Bookauthor> getBookauthorCollection() {
        return bookauthorCollection;
    }

    public void setBookauthorCollection(Collection<Bookauthor> bookauthorCollection) {
        this.bookauthorCollection = bookauthorCollection;
    }
    
    
}
