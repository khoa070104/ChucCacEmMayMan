# Lab 02: Báo cáo chương trình quản lý đăng ký leo núi

## I. Decomposition

### 1.1 Phân chia theo kiến trúc MVC:
- **Model**: `Student.java`, `Mountain.java` - đại diện cho dữ liệu
- **View**: `StudentView.java` - giao diện người dùng và menu
- **Controller**: `StudentController.java`, `Inputter.java` - xử lý logic nghiệp vụ

### 1.2 Phân chia theo chức năng:
- **Quản lý sinh viên**: Thêm, sửa, xóa, tìm kiếm thông tin sinh viên
- **Quản lý núi**: Đọc danh sách núi từ file CSV
- **Xử lý dữ liệu**: Validation, tính toán học phí, thống kê
- **Lưu trữ**: Đọc/ghi file dữ liệu

### 1.3 Phân chia theo luồng xử lý:
- **Khởi tạo**: Load dữ liệu núi từ CSV
- **Tương tác**: Menu-driven interface
- **Xử lý**: Validation và business logic
- **Lưu trữ**: Ghi dữ liệu ra file

## II. Pattern Recognition

### 2.1 MVC Pattern:
- Tách biệt rõ ràng giữa Model (dữ liệu), View (giao diện), Controller (logic)
- `Student` và `Mountain` là Model classes
- `StudentView` là View class xử lý hiển thị
- `StudentController` là Controller xử lý business logic

### 2.2 Validation Pattern:
- Sử dụng regex patterns trong `Inputter` class
- Tách biệt validation rules thành constants
- Có 2 loại input: required và optional

### 2.3 Data Access Pattern:
- CRUD operations cho Student entity
- File I/O operations cho persistence
- Search và filter operations

## III. Abstraction

### 3.1 Entity Abstraction:
- `Student` entity: Đại diện cho thông tin sinh viên đăng ký leo núi
- `Mountain` entity: Đại diện cho thông tin các ngọn núi có thể leo

### 3.2 Service Abstraction:
- `StudentController`: Service layer xử lý tất cả business logic
- `Inputter`: Utility class xử lý input validation

### 3.3 Data Abstraction:
- Sử dụng List để quản lý collections
- File I/O abstraction cho persistence
- Menu-driven interface abstraction

## IV. Algorithm Design

### 4.1 Thuật toán chính - Main Menu Loop:
```
1. Hiển thị menu
2. Nhận input từ user
3. Switch case xử lý từng chức năng
4. Lặp lại cho đến khi user chọn exit
```

### 4.2 Thuật toán thêm sinh viên mới:
```
1. Validate student ID (không trùng)
2. Validate các thông tin khác (name, phone, email)
3. Validate mountain code (phải tồn tại)
4. Tính toán học phí (giảm 35% nếu phone đặc biệt)
5. Tạo Student object và thêm vào list
```

### 4.3 Thuật toán tìm kiếm:
```
1. Linear search qua list Student
2. So sánh điều kiện tìm kiếm
3. Thêm vào result list nếu match
4. Trả về result list
```

### 4.4 Thuật toán thống kê:
```
1. Tạo Set chứa các mountain code unique
2. Với mỗi mountain code:
   - Đếm số sinh viên
   - Tính tổng chi phí
3. Hiển thị kết quả dạng bảng
```

## V. Class Diagram

```plantuml
@startuml
package model {
    class Student {
        -String studenId
        -String name
        -String phoneNumber
        -String email
        -String mountainCode
        -double tutioneFee
        +Student(String, String, String, String, String, double)
        +getStudenId(): String
        +setStudenId(String): void
        +getName(): String
        +setName(String): void
        +getPhoneNumber(): String
        +setPhoneNumber(String): void
        +getEmail(): String
        +setEmail(String): void
        +getMountainCode(): String
        +setMountainCode(String): void
        +getTutioneFee(): double
        +setTutioneFee(double): void
        +toString(): String
    }
    
    class Mountain {
        -String code
        -String mountain
        -String province
        -String description
        +Mountain(String, String, String, String)
        +getCode(): String
        +setCode(String): void
        +getMountain(): String
        +setMountain(String): void
        +getProvince(): String
        +setProvince(String): void
        +getDescription(): String
        +setDescription(String): void
        +toString(): String
    }
}

package controller {
    class StudentController {
        -List<Student> listStudent
        -List<Mountain> listMountain
        +getStudentById(String): Student
        +getMountainById(String): Mountain
        +readMountainList(String): void
        +addNewRegistration(): void
        +updateRegistration(): void
        +deleteRegister(): void
        +printList(List<Student>, String): void
        +searchByName(): List<Student>
        +filterByCampus(): List<Student>
        +printStatist(): void
        +saveToFile(String): void
        +convertMountainCode(String): String
    }
    
    class Inputter {
        +String STUDENT_ID_VALIDATE
        +String NAME_VALIDATE
        +String PHONE_NUMBER_VALIDATE
        +String EMAIL_VALIDATE
        +String MOUNTAIN_CODE_VALIDATE
        +String TUTION_FEE_VALIDATE
        +String YES_NO_VALIDATE
        -Scanner sc
        +input(String): String
        +inputRequired(String, String): String
        +inputOptional(String, String): String
    }
}

package view {
    class StudentView {
        +menu(): void
        +main(String[]): void
        -newRegister(StudentController): void
        -displayRegister(StudentController): void
        -updateRegister(StudentController): void
        -deleteRegister(StudentController): void
        -filterRegister(StudentController): void
        -statistRegister(StudentController): void
        -searchByName(StudentController): void
        -saveFile(StudentController): boolean
    }
}

StudentController --> Student : manages
StudentController --> Mountain : manages
StudentController --> Inputter : uses
StudentView --> StudentController : uses
StudentView --> Inputter : uses
@enduml
```

## VI. Flowchart

```plantuml
@startuml
start
:Khởi tạo StudentController;
:Đọc danh sách núi từ CSV;
:Hiển thị menu chính;

repeat
  :Nhận lựa chọn từ user;
  
  switch (choice)
  case (1 - New Registration)
    :Validate Student ID;
    :Validate Name, Phone, Email;
    :Validate Mountain Code;
    :Tính toán học phí;
    :Tạo Student object;
    :Thêm vào list;
  case (2 - Update Registration)
    :Tìm Student theo ID;
    if (Student tồn tại?) then (yes)
      :Validate thông tin mới;
      :Cập nhật thông tin;
      :Tính lại học phí nếu cần;
    else (no)
      :Hiển thị lỗi;
    endif
  case (3 - Display List)
    :Hiển thị danh sách sinh viên;
  case (4 - Delete Registration)
    :Tìm Student theo ID;
    if (Student tồn tại?) then (yes)
      :Hiển thị thông tin chi tiết;
      :Xác nhận xóa;
      if (Xác nhận?) then (yes)
        :Xóa khỏi list;
      endif
    else (no)
      :Hiển thị lỗi;
    endif
  case (5 - Search by Name)
    :Nhập tên cần tìm;
    :Tìm kiếm trong list;
    :Hiển thị kết quả;
  case (6 - Filter by Campus)
    :Nhập campus code;
    :Lọc theo campus;
    :Hiển thị kết quả;
  case (7 - Statistics)
    :Tạo Set mountain codes;
    :Đếm số sinh viên theo núi;
    :Tính tổng chi phí;
    :Hiển thị bảng thống kê;
  case (8 - Save to File)
    :Ghi dữ liệu ra file;
    :Đặt flag = true;
  case (9 - Exit)
    if (Có thay đổi chưa lưu?) then (yes)
      :Xác nhận thoát;
      if (Xác nhận?) then (yes)
        :Lưu dữ liệu;
        :Thoát;
      else (no)
        :Quay lại menu;
      endif
    else (no)
      :Thoát;
    endif
  endswitch
repeat while (choice != 9)
stop
@enduml
```

---

**Ghi chú**: 
- Chương trình sử dụng kiến trúc MVC để tách biệt rõ ràng các thành phần
- Validation được thực hiện thông qua regex patterns
- Dữ liệu được lưu trữ trong memory và có thể export ra file
- Giao diện menu-driven đơn giản và dễ sử dụng

