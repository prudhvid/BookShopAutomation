

package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookauthor.findAll", query = "SELECT b FROM Bookauthor b"),
    @NamedQuery(name = "Bookauthor.findById", query = "SELECT b FROM Bookauthor b WHERE b.id = :id")})
public class Bookauthor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne
    private Book book;
    @JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
    @ManyToOne
    private Author author;

    public Bookauthor() {
    }

    public Bookauthor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
        if (!(object instanceof Bookauthor)) {
            return false;
        }
        Bookauthor other = (Bookauthor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Bookauthor[ id=" + id + " ]";
    }

}
