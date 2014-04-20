

package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rack.findAll", query = "SELECT r FROM Rack r"),
    @NamedQuery(name = "Rack.findByNumber", query = "SELECT r FROM Rack r WHERE r.number = :number"),
    @NamedQuery(name = "Rack.findByIntitial", query = "SELECT r FROM Rack r WHERE r.intitial = :intitial"),
    @NamedQuery(name = "Rack.findByFinal1", query = "SELECT r FROM Rack r WHERE r.final1 = :final1")})
public class Rack implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer number;
    private Character intitial;
    @Column(name = "FINAL")
    private Character final1;
    @OneToMany(mappedBy = "rack")
    private Collection<Racksection> racksectionCollection;

    public Rack() {
    }

    public Rack(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Character getIntitial() {
        return intitial;
    }

    public void setIntitial(Character intitial) {
        this.intitial = intitial;
    }

    public Character getFinal1() {
        return final1;
    }

    public void setFinal1(Character final1) {
        this.final1 = final1;
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
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rack)) {
            return false;
        }
        Rack other = (Rack) object;
        if ((this.number == null && other.number != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Rack[ number=" + number + " ]";
    }

}
