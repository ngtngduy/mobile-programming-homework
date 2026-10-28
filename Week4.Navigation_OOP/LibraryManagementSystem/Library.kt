package com.example.librarymanagementsystem

class Library {
    val books:  List<LibraryItem> = listOf(
        Book("Sách 01", "Tác giả A"),
        Book("Sách 02","Tác giả B"),
        Magazine("Khoa học","12")
    )

    val students: List<Student> = listOf(
        Student("Nguyen Van A", "SV001"),
        Student("Tran Thi B","SV002")
    )

    fun lendBook(student: Student,item: LibraryItem): Boolean {
        if (item.borrow()) {
            student.addBook(item)
            return true
        }
        return false
    }
}