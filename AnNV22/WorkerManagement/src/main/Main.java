package main;

import constants.Message;
import controller.WorkerController;
import dto.WorkerRequestDTO;
import java.util.Scanner;
import utils.Validation;

public class Main {

    public static void main(String[] args) {
        
        //Khai bao dau vao nhap lieu
        Scanner sc = new Scanner(System.in);
        
        //Khai bao controller
        WorkerController controller = new WorkerController();

        // Tao vong den khi thoa man cac dieu kien ngoai le
        while (true) {
            System.out.println(Message.MENU);
            System.out.print(Message.ENTER_CHOICE);

            try {
                // Goi Validation check nhap so tu 1 den 5
                int choice = Validation.getChoice(sc.nextLine(), 1, 5);
                
                switch (choice) {
                    case 1:
                        //Khai bao Request
                        WorkerRequestDTO addDto = new WorkerRequestDTO();
                        System.out.println(Message.TITLE_ADD_WORKER);
                        //Nhap id 
                        System.out.print(Message.ENTER_CODE);
                        addDto.setId(Validation.getString(sc.nextLine()));
                        //Nhap ten 
                        System.out.print(Message.ENTER_NAME);
                        addDto.setName(Validation.getString(sc.nextLine()));
                        //Nhap tuoi
                        System.out.print(Message.ENTER_AGE);
                        addDto.setAge(Validation.getAge(sc.nextLine()));
                        //Nhap luong
                        System.out.print(Message.ENTER_SALARY);
                        addDto.setSalary(Validation.getPositiveDouble(sc.nextLine()));
                        
                        //Nhap dia diem
                        System.out.print(Message.ENTER_LOCATION);
                        addDto.setLocation(Validation.getString(sc.nextLine()));
                        
                        // Controller xu ly them moi
                        controller.addWorker(addDto);
                        break;
                        
                    case 2:
                        System.out.println(Message.TITLE_SALARY);
                        //Nhap id
                        System.out.print(Message.ENTER_CODE);
                        String incCode = Validation.getString(sc.nextLine());
                        
                        
                        System.out.print(Message.ENTER_SALARY);
                        double incAmount = Validation.getPositiveDouble(sc.nextLine());
                        
                        // Controller xu ly tang luong
                        controller.increaseSalary(incCode, incAmount);
                        break;
                        
                    case 3:
                        System.out.println(Message.TITLE_SALARY);
                        System.out.print(Message.ENTER_CODE);
                        String decCode = Validation.getString(sc.nextLine());
                        System.out.print(Message.ENTER_SALARY);
                        double decAmount = Validation.getPositiveDouble(sc.nextLine());
                        
                        // Controller xu ly giam luong
                        controller.decreaseSalary(decCode, decAmount);
                        break;
                        
                    case 4:
                        System.out.println(Message.TITLE_DISPLAY);
                        // Controller in ra man hinh
                        controller.displaySalaryHistory();
                        break;
                        
                    case 5:
                        // Thoat chuong trinh
                        return;
                }
            } catch (Exception e) {
                // In loi bat ky 
                System.out.println(e.getMessage());
            }
        }
    }
}