# Student and Short Course Management System

Complete Java console solution for LAB211 assignment `J1.L.P0039`.

## Build and run

```text
javac -d out src/model/*.java src/data/*.java src/util/*.java src/service/*.java src/app/*.java
java -cp out app.Main
```

The program automatically loads `Students.txt` and `Courses.txt` from its working directory.
Dates use `dd/MM/yyyy` as required by the supplied files.
