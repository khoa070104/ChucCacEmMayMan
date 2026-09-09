# Student and Short Course Management System

Java console application for LAB211 assignment `J1.L.P0039`.

## Run

Run with NetBeans or execute `ant run` from this directory. The program loads
`Students.txt` and `Courses.txt` at startup and writes both files through menu option 11.

## Sample architecture

- `Entity`: `Student`, `Course`
- `DataObject`: file persistence and DAO classes
- `Utilities`: console input and validation
- `Program`: menu workflow and `Main`

The program implements all 12 specified menu functions. Invalid file rows and duplicate
IDs are ignored during loading so malformed input cannot stop the application.
