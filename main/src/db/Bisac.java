

package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bisac.findAll", query = "SELECT b FROM Bisac b"),
    @NamedQuery(name = "Bisac.findBySubject", query = "SELECT b FROM Bisac b WHERE b.subject = :subject"),
    @NamedQuery(name = "Bisac.findById", query = "SELECT b FROM Bisac b WHERE b.id = :id")})
public class Bisac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    private String subject;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @OneToMany(mappedBy = "bisac")
    private Collection<Racksection> racksectionCollection;

    public Bisac() {
    }

    public Bisac(Integer id) {
        this.id = id;
    }

    public Bisac(Integer id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Racksection> getRacksectionCollection() {
        return racksectionCollection;
    }

    public void setRacksectionCollection(Collection<Racksection> racksectionCollection) {
        this.racksectionCollection = racksectionCollection;
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
        if (!(object instanceof Bisac)) {
            return false;
        }
        Bisac other = (Bisac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Bisac[ id=" + id + " ]";
    }

}
