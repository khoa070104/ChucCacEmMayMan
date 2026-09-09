package entity;

/**
 * Lớp Worker dùng để lưu trữ thông tin của một công nhân.
 *
 * @author
 */
public class Worker {

    // Mã công nhân.
    private String id;

    // Tên công nhân.
    private String name;

    // Tuổi công nhân.
    private int age;

    // Mức lương hiện tại.
    private double salary;

    // Nơi làm việc của công nhân.
    private String workLocation;

    /**
     * Khởi tạo công nhân rỗng.
     */
    public Worker() {}

    /**
     * Khởi tạo công nhân với đầy đủ thông tin.
     *
     * @param id mã công nhân
     * @param name tên công nhân
     * @param age tuổi công nhân
     * @param salary mức lương
     * @param workLocation nơi làm việc
     */
    public Worker(String id, String name, int age, double salary, String workLocation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workLocation = workLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    /**
     * Trả về chuỗi thông tin công nhân.
     *
     * @return thông tin công nhân
     */
    @Override
    public String toString() {
        return "Worker{"
                + "id: "
                + id
                + ", name: "
                + name
                + ", age: "
                + age
                + ", salary: "
                + salary
                + ", workLocation: "
                + workLocation
                + '}';
    }
}
