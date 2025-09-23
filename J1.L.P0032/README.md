Michael BMW Showroom Management (J1.L.P0032)

Mô tả
- Ứng dụng console quản lý showroom xe BMW, đọc/ghi dữ liệu từ `brands.txt` và `cars.txt`. Áp dụng OOP, validate dữ liệu theo đề bài.

Kiến trúc
- `model/Brand.java`, `model/Car.java`: mô hình dữ liệu.
- `controller/ShowRoomManagement.java`: nghiệp vụ quản lý brand/car, đọc/ghi file.
- `controller/Inputter.java`: tiện ích nhập liệu và validate regex.
- `common/Constants.java`, `common/Messages.java`: hằng số file, regex, và thông báo.
- `view/MainView.java`: menu chính và vòng lặp chương trình.

Chức năng
1. Liệt kê tất cả Brands (bảng có header).
2. Thêm Brand mới: id, tên, âm thanh, giá (tỉ) – validate và thông báo.
3. Tìm Brand theo ID: báo "This brand does not exist!" nếu không có.
4. Cập nhật Brand theo ID: bỏ trống để bỏ qua trường.
5. Liệt kê Brand có giá ≤ input (đơn vị tỉ, ví dụ 3.749).
6. Liệt kê Car theo Brand name tăng dần; cùng Brand thì giá giảm dần.
7. Tìm Car theo chuỗi con tên Brand (ví dụ 320i).
8. Thêm Car: id, chọn brand bằng ID, màu, frameId (F00000, unique), engineId (E00000, unique).
9. Xóa Car theo ID: báo nếu không tồn tại.
10. Cập nhật Car theo ID: bỏ trống để bỏ qua, validate unique frame/engine.
11. Liệt kê Car theo màu.
12. Lưu dữ liệu ra file; thông báo sau khi lưu.
13. Thoát: tự động lưu nếu có thay đổi.

Định dạng dữ liệu
- `brands.txt`: `BrandId, Brand Name, Sound: 3.749B`
  - Lưu nội bộ: 3.749B -> 3749 (x1000), xuất ra định dạng `#.###B`.
- `cars.txt`: `CarId, BrandId, color, F00000, E00000`

Cách chạy
- Chạy `view.MainView` (hàm `main`).
- File dữ liệu đặt cùng thư mục project: `brands.txt`, `cars.txt`.

Lưu ý/Validate
- Brand: id unique, name & sound không rỗng, price > 0.
- Car: id có thể trùng theo đề, brandId phải tồn tại; color không rỗng; frameId/engineId theo regex và unique.

Mở rộng
- Bổ sung lưu định dạng cột đẹp hơn, i18n thông báo.
- Thêm test đơn vị cho parser/formatter giá.

\

BÁO CÁO (Theo yêu cầu cuối đề)
- Source code: trong thư mục `src/` theo cấu trúc ở trên.
- Diagram: cung cấp dưới dạng mã PlantUML (có thể render bằng bất kỳ PlantUML renderer nào).
- Flow-chart: mô tả vòng lặp menu chính dưới dạng PlantUML.

Sơ đồ lớp (Class Diagram) — PlantUML
```plantuml
@startuml
skinparam classAttributeIconSize 0

class Brand {
  - brandId: String
  - brandName: String
  - brandSound: String
  - price: double // lưu dạng 3749 = 3.749B
}

class Car {
  - carId: String
  - color: String
  - frameId: String
  - engineId: String
  - brand: Brand
}

class ShowRoomManagement {
  - listBrand: List<Brand>
  - listCar: List<Car>
  - brandsChanged: boolean
  - carsChanged: boolean
  + readBrandFile(fileName: String)
  + readCarFile(fileName: String)
  + listAllBrands()
  + addNewBrand()
  + searchBrandById()
  + updateBrandById()
  + printBrandsByPrice()
  + listAllCarsSorted()
  + searchCarsByBrandNameLike()
  + addNewCar()
  + removeCar(): boolean
  + updateCar()
  + listCarsByColor()
  + saveBrandsToFile(fileName: String)
  + saveCarsToFile(fileName: String)
  + saveAll()
}

class Inputter {
  {static} VALIDATE_FRAME_ID: String
  {static} VALIDATE_ENGINE_ID: String
  {static} inputRequired(label: String): String
  {static} inputRequired(label: String, regex: String): String
  {static} inputOptional(label: String): String
  {static} inputOptional(label: String, regex: String): String
}

class MainView {
  + main(args: String[]): void
}

class Constants {
  {static} BRAND_FILE: String
  {static} CAR_FILE: String
  {static} REGEX_FRAME_ID: String
  {static} REGEX_ENGINE_ID: String
}

class Messages {
  {static} ERR_... / MSG_...
}

ShowRoomManagement "1" *-- "*" Brand
ShowRoomManagement "1" *-- "*" Car
Car "*" --> "1" Brand
MainView --> ShowRoomManagement
ShowRoomManagement ..> Inputter
ShowRoomManagement ..> Constants
ShowRoomManagement ..> Messages

@enduml
```

Sơ đồ trình tự (Sequence) — Thêm mới Car
```plantuml
@startuml
actor User
participant MainView
participant ShowRoomManagement as SRM
participant Inputter

User -> MainView: Chọn "8. Add a new car"
MainView -> SRM: addNewCar()
SRM -> Inputter: inputRequired("Enter ID")
Inputter --> SRM: carId
SRM -> SRM: getCarById(carId) == null?
SRM -> Inputter: inputRequired("Enter Brand Id")
Inputter --> SRM: brandId
SRM -> SRM: getBrandById(brandId)
SRM -> Inputter: inputRequired("Enter Engine Id", regex)
Inputter --> SRM: engineId
SRM -> SRM: isUniqueEngineId(engineId)?
SRM -> Inputter: inputRequired("Enter Frame Id", regex)
Inputter --> SRM: frameId
SRM -> SRM: isUniqueFrameId(frameId)?
SRM -> Inputter: inputRequired("Enter Color")
Inputter --> SRM: color
SRM -> SRM: new Car(...); listCar.add(car); carsChanged=true
SRM --> MainView: hoàn tất
MainView --> User: Thông báo thành công
@enduml
```

Flow-chart (Activity) — Vòng lặp Menu chính
```plantuml
@startuml
start
:Load brands.txt, cars.txt;
repeat
  :Hiển thị Menu;
  :Nhập lựa chọn 1..13;
  switch (Lựa chọn)
    case (1)
      :listAllBrands();
      break
    case (2)
      :addNewBrand();
      break
    case (3)
      :searchBrandById();
      break
    case (4)
      :updateBrandById();
      break
    case (5)
      :printBrandsByPrice();
      break
    case (6)
      :listAllCarsSorted();
      break
    case (7)
      :searchCarsByBrandNameLike();
      break
    case (8)
      :addNewCar();
      break
    case (9)
      :removeCar();
      break
    case (10)
      :updateCar();
      break
    case (11)
      :listCarsByColor();
      break
    case (12)
      :saveAll();
      break
    case (13)
      :saveAll();
      stop
  endswitch
repeat while (choice != 13)
@enduml
```


