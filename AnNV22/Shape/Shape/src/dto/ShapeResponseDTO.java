package dto;

public class ShapeResponseDTO {

    private String rectangleInfo;
    private String circleInfo;
    private String triangleInfo;

    public ShapeResponseDTO(String rectangleInfo, String circleInfo, String triangleInfo) {
        this.rectangleInfo = rectangleInfo;
        this.circleInfo = circleInfo;
        this.triangleInfo = triangleInfo;
    }

    public String getRectangleInfo() {
        return rectangleInfo;
    }

    public String getCircleInfo() {
        return circleInfo;
    }

    public String getTriangleInfo() {
        return triangleInfo;
    }
}
