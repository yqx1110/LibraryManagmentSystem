package yqx1110.LibraryManagement.entity;

public abstract class User {
    protected int uuid;
    protected String name;

    public User(int uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
