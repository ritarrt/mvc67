package Model;
import java.io.*;
import java.util.*;

public class AnimalDatabase {
    // ประกาศตัวแปร
    private List<Animal> animals; // สองสัตว์
    private Set<String> removedGoats; // แพะที่ถูกไล่ออก

    public AnimalDatabase(String filePath) {
        this.animals = new ArrayList<>();
        this.removedGoats = new HashSet<>();
        loadData(filePath);
    }

    // โหลดข้อมูลจากไฟล์
    private void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // อ่านข้อมูลจากไฟล์
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // แยกด้วย ,
                if (fields[0].equals("id")) continue; // skip header
                String id = fields[0];
                String type = fields[1];
                int ageYears = Integer.parseInt(fields[2]);
                int ageMonths = Integer.parseInt(fields[3]);
                int teats = Integer.parseInt(fields[4]);

                if (type.equals("cow")) {
                    animals.add(new Cow(id, ageYears, ageMonths, teats));
                } else if (type.equals("goat")) {
                    animals.add(new Goat(id));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ค้นหาสัตว์ตาม id
    public Animal findAnimal(String id) {
        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                if (animal instanceof Goat) {
                    Goat goat = (Goat) animal;
                    if (goat.isRemoved()) {
                        return goat;
                    }
                }
                return animal;
            }
        }
        return null;
    }

    // รีดนม
    public double milkCow(Cow cow) {
        cow.adjustTeats();
        return cow.calculateMilkAmount();
    }

    // ลบแพะ
    public void removeGoat(Goat goat) {
        goat.setRemoved(true);
        removedGoats.add(goat.getId());
    }
}
