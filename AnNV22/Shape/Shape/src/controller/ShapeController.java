package controller;

import dto.ShapeRequestDTO;
import dto.ShapeResponseDTO;
import repository.ShapeRepository;
import view.ShapeView;

public class ShapeController {

    // Goi Repository de luu tru du lieu
    private ShapeRepository shapeRepository;

    // Goi View de hien thi ket qua
    private ShapeView shapeView;

    // Khoi tao Repository va View
    public ShapeController() {
        shapeRepository = new ShapeRepository();
        shapeView = new ShapeView();
    }

    // Tao cac hinh tu DTO
    public void createShape(ShapeRequestDTO dto) {
        shapeRepository.createShapes(dto);
    }

    // Hien thi ket qua
    public void showShape() {
        // Lay du lieu tu Repository
        ShapeResponseDTO responseDTO = shapeRepository.getShapeResponse();
        // Truyen sang View de hien thi
        shapeView.setShapeResponseDTO(responseDTO);
        shapeView.printResult();
    }
}
