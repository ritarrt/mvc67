import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalView extends JFrame {
    private JTextField idField;
    private JButton submitButton; // ปุ่มส่งข้อมูล
    private JButton removeGoatButton; // ปุ่มไล่แพะ
    private JButton milkCowButton; // ปุ่มรีดนม
    private JLabel resultLabel; // แสดงผลลัพธ์
    private AnimalController controller;

    public AnimalView() {
        // ตั้งค่าหน้าต่าง
        setTitle("Animal Milking System");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // สร้าง+จัด components
        JLabel idLabel = new JLabel("Enter Animal ID:");
        idLabel.setBounds(10, 10, 120, 25);
        add(idLabel);

        // ช่องกรอก ID
        idField = new JTextField();
        idField.setBounds(140, 10, 130, 25);
        add(idField);

        // ปุ่มส่งข้อมูล
        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 40, 80, 25);
        add(submitButton);

        //ปุ่มลบแพะ
        removeGoatButton = new JButton("Remove Goat");
        removeGoatButton.setBounds(80, 70, 140, 25);
        removeGoatButton.setEnabled(false); // เริ่มต้นปุ่มปิด ไม่ให้ใช้
        add(removeGoatButton);

        // ปุ่มรีดนม
        milkCowButton = new JButton("Milk Cow");
        milkCowButton.setBounds(80, 100, 140, 25);
        milkCowButton.setEnabled(false); // เริ่มต้นปุ่มปิด ไม่ให้ใช้
        add(milkCowButton);

        // แสดงผลลัพธ์
        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 130, 250, 25);
        add(resultLabel);

        // จัดการการทำงาน submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String result = controller.processId(id);
                resultLabel.setText(result);
                
                // เปิดใช้งาน removegoat และ milk cow (ตามสถานะ)
                if (result.contains("Cow has 4 teats")) {
                    milkCowButton.setEnabled(true);
                } else if (result.contains("Goat detected") || result.contains("removedGoat")) {
                    removeGoatButton.setEnabled(true);
                } else {
                    milkCowButton.setEnabled(false);
                }
            }
        });

        // ไล่แพะ
        removeGoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String result = controller.removeGoat(id);
                resultLabel.setText(result);
                
                // ปิดปุ่ม remove goat หลังกดไปแล้ว
                removeGoatButton.setEnabled(false);
            }
        });

        // รีดนม
        milkCowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String result = controller.milkCow(id);
                resultLabel.setText(result);
                
                // ปิดปุ่ม milk cow หลังกดไปแล้ว
                milkCowButton.setEnabled(false);
            }
        });
    }

    // connect controller&view
    public void setController(AnimalController controller) {
        this.controller = controller;
    }

    // open/close รีดนม
    public void enableMilkButton(boolean enabled) {
        milkCowButton.setEnabled(enabled);
    }
}
