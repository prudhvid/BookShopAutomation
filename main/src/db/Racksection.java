

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
    @NamedQuery(name = "Racksection.findAll", query = "SELECT r FROM Racksection r"),
    @NamedQuery(name = "Racksection.findById", query = "SELECT r FROM Racksection r WHERE r.id = :id")})
public class Racksection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "RACK", referencedColumnName = "NUMBER")
    @ManyToOne
    private Rack rack;
    @JoinColumn(name = "BISAC", referencedColumnName = "ID")
    @ManyToOne
    private Bisac bisac;

    public Racksection() {
    }

    public Racksection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Bisac getBisac() {
        return bisac;
    }

    public void setBisac(Bisac bisac) {
        this.bisac = bisac;
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
        if (!(object instanceof Racksection)) {
            return false;
        }
        Racksection other = (Racksection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Racksection[ id=" + id + " ]";
    }

}
