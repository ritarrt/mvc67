package Model;
public class Cow extends Animal {
    private int ageYears;
    private int ageMonths;
    private int teats;

    public Cow(String id, int ageYears, int ageMonths, int teats) {
        super(id);
        this.ageYears = ageYears;
        this.ageMonths = ageMonths;
        this.teats = teats;
    }

    // เช็คว่าให้นมได้เปล่า
    public boolean canMilk() {
        return teats == 4;
    }

    // ปรับเต้า
    public String adjustTeats() {
        String message = "";
        if (teats == 4 && Math.random() < 0.05) {
            teats--; // ลดเต้าลง 1 เต้า
            message = "Teat count decreased to " + teats + ".";
        } else if (teats == 3 && Math.random() < 0.20) {
            teats++; // เพิ่มเต้า 1 เต้า
            message = "Teat count increased to " + teats + ".";
        }
        return message;
    }

    public double calculateMilkAmount() {
        return ageYears + (ageMonths / 12.0); // คำนวณน้ำนมที่ผลิตได้
    }

    @Override
    public String getType() {
        return "cow";
    }

    public int getTeats() {
        return teats;
    }
}
