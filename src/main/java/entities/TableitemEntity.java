package entities;

import javax.persistence.*;

@Entity
@Table(name = "tableitem", schema = "public", catalog = "albertdatabase")
public class TableitemEntity {
    private int code;
    private String name;
    private String comment;
    private Integer surrogate;
    private GroupitemEntity groupitemByGroupitem;

    @Id
    @Column(name = "code", nullable = false)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "surrogate", nullable = true)
    public Integer getSurrogate() {
        return surrogate;
    }

    public void setSurrogate(Integer surrogate) {
        this.surrogate = surrogate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableitemEntity that = (TableitemEntity) o;

        if (code != that.code) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (surrogate != null ? !surrogate.equals(that.surrogate) : that.surrogate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (surrogate != null ? surrogate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "groupitem", referencedColumnName = "id")
    public GroupitemEntity getGroupitemByGroupitem() {
        return groupitemByGroupitem;
    }

    public void setGroupitemByGroupitem(GroupitemEntity groupitemByGroupitem) {
        this.groupitemByGroupitem = groupitemByGroupitem;
    }
}
