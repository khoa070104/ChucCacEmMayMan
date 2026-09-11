/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public enum Course {
    JAVA("Java"),
    DOT_NET(".Net"),
    C_CPP("C/C++");
    private String language;

    private Course(String language) {
        this.language = language;
    }
// type này được truyền từ view, sau đó được truyền vào method getCourse để trả về enum;

    public static Course getCourse(int type) {
        switch (type) {
            case 1:
                return JAVA;
            case 2:
                return DOT_NET;
            case 3:
                return C_CPP;
            default:
                throw new AssertionError();
        }
    }
//vậy tôi nói là method getCourse này dùng static vì nó thuộc class chứ không thuộc đối tượng, trong các class khác có gọi 
//đến method getCourse này chỉ cần gọi tên class.method thôi chứ không phải tạo một object mới rồi mới gọi đến method, điều
//này sai vì bản chất của Course là enum chỉ cố định ba khoá học JAVA .NET và C/C++ thôi

    public String getLanguage() {
        return language;
    }

}
