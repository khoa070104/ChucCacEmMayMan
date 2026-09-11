package repository;

import dto.ShapeRequestDTO;
import dto.ShapeResponseDTO;
import java.util.ArrayList;
import java.util.List;
import model.Circle;
import model.Rectangle;
import model.Shape;
import model.Triangle;

public class ShapeRepository {

    // Danh sach luu tru cac hinh
    private List<Shape> shapeList;

    // Khoi tao danh sach rong
    public ShapeRepository() {
        shapeList = new ArrayList<>();
    }

    // Tao cac doi tuong hinh tu DTO va luu vao danh sach
    public void createShapes(ShapeRequestDTO dto) {

        // Tao cac doi tuong hinh tu du lieu DTO
        Shape rectangle = new Rectangle(dto.getWidth(), dto.getLength());
        Shape circle = new Circle(dto.getRadius());
        Shape triangle = new Triangle(
                dto.getSideA(),
                dto.getSideB(),
                dto.getSideC());

        // Luu vao danh sach
        shapeList.add(rectangle);
        shapeList.add(circle);
        shapeList.add(triangle);
    }

    // Chuyen doi du lieu tu Model sang ResponseDTO
    public ShapeResponseDTO getShapeResponse() {

        String recInfo = "";
        String cirInfo = "";
        String triInfo = "";

        // Duyet qua danh sach cac hinh
        for (Shape shape : shapeList) {
            if (shape instanceof Rectangle) {
                recInfo = shape.toString();
            } else if (shape instanceof Circle) {
                cirInfo = shape.toString();
            } else if (shape instanceof Triangle) {
                triInfo = shape.toString();
            }
        }

        // Dong goi va tra ve ResponseDTO
        return new ShapeResponseDTO(recInfo, cirInfo, triInfo);
    }
}
