# Hovilo - Java OOP assignments

Author: Ho Vi Lo
Created: 09/09/2026

## Projects

- `S02`: Reverse the order of words in a string.
- `S03`: Convert binary, octal, and hexadecimal numbers to decimal.
- `S04`: Manage student marks, classification, and percentages.
- `S05`: Count occurrences of English letters.
- `S06`: Manage an integer array with a menu and bubble sort.
- `V01`: Calculate the weighted course score and minimum grade.
- `V02`: Manage doctors with a `HashMap`-style data structure.
- `L01`: Manage books and authors with `book.dat` and `author.dat`.

Each project separates data (`model`), processing/input (`controller`), display
(`view`), and startup (`main`) where the assignment permits it. V01 remains in
one source file because its specification explicitly requires `GradeStudent.java`.

## Compile and run

Example for S02 from the `Hovilo` folder:

```powershell
javac -encoding UTF-8 -d S02\build\classes (Get-ChildItem S02\src -Recurse -Filter *.java)
java -cp S02\build\classes main.Main
```

V01 uses a different main class:

```powershell
javac -encoding UTF-8 -d V01\build\classes V01\src\GradeStudent.java
java -cp V01\build\classes GradeStudent
```

Run L01 from inside its project directory so the program can find its data files:

```powershell
Set-Location L01
java -cp build\classes main.Main
```
