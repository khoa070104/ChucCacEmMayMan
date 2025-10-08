# J1.S.P0052 - Manage Geographic (East Asia Countries)

## Mô tả
Chương trình quản lý thông tin địa lý của các quốc gia Đông Á với các tính năng:
- Nhập thông tin 11 quốc gia Đông Á
- Hiển thị thông tin quốc gia vừa nhập
- Tìm kiếm quốc gia theo tên
- Sắp xếp quốc gia theo tên (tăng dần)

## Cấu trúc dự án
```
src/
├── common/
│   ├── Constants.java      # Các hằng số
│   └── Messages.java       # Các thông báo
├── controller/
│   ├── Inputter.java       # Xử lý nhập liệu
│   └── ManageEastAsiaCountries.java  # Logic quản lý
├── model/
│   ├── Country.java        # Lớp cơ sở
│   └── EastAsiaCountries.java  # Lớp kế thừa
├── view/
│   └── MainView.java       # Giao diện người dùng
└── main/
    └── Main.java           # Điểm khởi đầu
```

## Cách chạy
```bash
# Biên dịch
javac -cp src src/main/Main.java

# Chạy chương trình
java -cp src main.Main
```

## Tính năng
1. **Nhập thông tin quốc gia**: Mã quốc gia, tên, diện tích, địa hình
2. **Hiển thị thông tin**: Hiển thị quốc gia vừa nhập gần nhất
3. **Tìm kiếm**: Tìm kiếm quốc gia theo tên (không phân biệt hoa thường)
4. **Sắp xếp**: Sắp xếp các quốc gia theo tên tăng dần
5. **Thoát**: Kết thúc chương trình

## Yêu cầu
- Java 8 trở lên
- Diện tích quốc gia phải > 0
- Tối đa 11 quốc gia
