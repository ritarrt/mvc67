import Model.Animal;
import Model.AnimalDatabase;
import Model.Cow;
import Model.Goat;

public class AnimalController {
    // ประกาศตัวแปร
    private AnimalView view;
    private AnimalDatabase model;

    // สร้าง controller
    public AnimalController(AnimalView view, AnimalDatabase model) {
        this.view = view;
        this.model = model;
    }

    public String processId(String id) {
        // ตรวจสอบว่ารหัสเป็นตัวเลข 8 ตัว
        if (id.length() != 8 || !id.matches("\\d{8}")) {
            return "Invalid ID format: The ID must be exactly 8 digits.";
        }

        // ตรวจสอบว่ารหัสไม่เริ่มต้นด้วยเลข 0
        if (id.startsWith("0")) {
            return "Invalid ID format: The ID must not start with 0.";
        }

        Animal animal = model.findAnimal(id); // ค้นหา id สัตว์ใน data

        if (animal == null) {
            return "Animal not found";
        }

        // ตรวจสอบประเภทสัตว์และอัพเดต ui ตามสถานะ
        if (animal.getType().equals("cow")) {
            Cow cow = (Cow) animal;
            if (cow.canMilk()) {
                view.enableMilkButton(true); // เปิดปุ่มรีดนม
                return "Cow has 4 teats. You can now milk the cow.";
            } else {
                return "Cow has insufficient teats for milking.";
            }
        } else if (animal.getType().equals("goat")) {
            return "Goat detected. Preparing to send back to the mountains.";
        } else if (animal.getType().equals("removedGoat")) {
            return "This goat has been removed and cannot be milked.";
        }

        return "Unknown animal type";
    }

    //ไล่แพะ
    public String removeGoat(String id) {
        Animal animal = model.findAnimal(id);
        if (animal instanceof Goat) {
            Goat goat = (Goat) animal;
            if (goat.isRemoved()) {
                return "This goat has already been removed.";
            }
            model.removeGoat(goat);
            return "Goat removed from the milking machine.";
        }
        return "ID does not belong to a goat.";
    }

    //รีดนม
    public String milkCow(String id) {
        Animal animal = model.findAnimal(id);
        if (animal instanceof Cow) {
            Cow cow = (Cow) animal;
            String teatChangeMessage = cow.adjustTeats(); // ปรับจำนวนเต้านมและรับข้อความ
            double milkAmount = model.milkCow(cow);
            String result = "Cow milked: " + String.format("%.2f", milkAmount) + " liters.";
            if (!teatChangeMessage.isEmpty()) {
                result += " " + teatChangeMessage; // เพิ่มข้อความการเปลี่ยนแปลงเต้านม
            }
            // รีเซ็ตปุ่มหลังรีดนมเสร็จ
            view.enableMilkButton(false);
            return result;
        }
        return "ID does not belong to a cow.";
    }
}
