package Models;

public class ItemTable {

    private int code;
    private String name;
    private Integer needed;
    private Integer central;
    private Integer vystavka;
    private String groupname;


    public ItemTable(int code, Integer needed, String name) {
        this.code = code;
        this.name = name;
        this.needed = needed;
    }

    public ItemTable() {
    }

    public ItemTable(int code, int quantityCentral, int quantityVystavka, String name) {
        this.code=code;
        this.central=quantityCentral;
        this.vystavka=quantityVystavka;
        this.name=name;
    }

    @Override
    public String toString() {
        return "ItemTable{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", needed=" + needed +
                ", central=" + central +
                ", vystavka=" + vystavka +
                ", groupname='" + groupname + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    public Integer getCentral() {
        return central;
    }

    public void setCentral(Integer central) {
        this.central = central;
    }

    public Integer getVystavka() {
        return vystavka;
    }

    public void setVystavka(Integer vystavka) {
        this.vystavka = vystavka;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
