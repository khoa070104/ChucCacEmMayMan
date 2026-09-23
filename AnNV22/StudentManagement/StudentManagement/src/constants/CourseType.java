package constants;

// Enum chi cho phep 3 khoa hoc co dinh theo de bai
public enum CourseType {

    // Mon Java
    JAVA("Java"),
    // Mon .NET
    DOT_NET(".Net"),
    // Mon C++
    CPP("C/C++");

    private final String displayName;

    CourseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
