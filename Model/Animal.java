package Model;
public abstract class Animal {
    private String id;

    public Animal(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String getType();
}
