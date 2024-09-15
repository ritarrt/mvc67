import Model.AnimalDatabase;

public class Main {
    public static void main(String[] args) {
        AnimalDatabase model = new AnimalDatabase("data.csv"); // สร้างฐานข้อมูลของสัตว์
        AnimalView view = new AnimalView(); // สร้าง ui(view)
        AnimalController controller = new AnimalController(view, model); // สร้าง controller
        view.setController(controller); // connect controller&view
        view.setVisible(true); // ทำให้ ui สามารถมองเห็น
    }
}
