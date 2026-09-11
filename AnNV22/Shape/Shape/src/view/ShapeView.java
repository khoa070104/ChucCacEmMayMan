package view;

import dto.ShapeResponseDTO;

public class ShapeView {

    // Thuoc tinh class
    private ShapeResponseDTO dto;

    // Setter
    public void setShapeResponseDTO(ShapeResponseDTO dto) {
        this.dto = dto;
    }

    // Hien thi thong tin
    public void printResult() {
        System.out.println("-----Rectangle-----");
        System.out.println(dto.getRectangleInfo());
        System.out.println("-----Circle-----");
        System.out.println(dto.getCircleInfo());
        System.out.println("-----Triangle-----");
        System.out.println(dto.getTriangleInfo());
    }
}
