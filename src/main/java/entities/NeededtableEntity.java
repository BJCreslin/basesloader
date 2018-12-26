package entities;

import javax.persistence.*;

@Entity
@Table(name = "neededtable", schema = "public", catalog = "albertdatabase")
public class NeededtableEntity {
    private Integer code;
    private Integer quantity;

    public NeededtableEntity(int code, Integer needed) {
        this.code = code;
        this.quantity = needed;
    }

    public NeededtableEntity() {
    }

    @Id
    @Column(name = "code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NeededtableEntity that = (NeededtableEntity) o;

        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return quantity != null ? quantity.hashCode() : 0;
    }
}
