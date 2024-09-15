package Model;
public class Goat extends Animal {
    private boolean removed;

    public Goat(String id) {
        super(id);
        this.removed = false; // แพะยังไม่ถูกไล่ออก
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String getType() {
        return removed ? "removedGoat" : "goat";
    }
}
