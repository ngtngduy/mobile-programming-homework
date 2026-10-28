package com.example.librarymanagementsystem

open class Person(val name: String,val id: String)

class Student(name: String,id: String) : Person(name,id) {
    private val _borrowedBooks = mutableListOf<LibraryItem>()

    val borrowedBooks: List<LibraryItem> = _borrowedBooks

    fun addBook(item: LibraryItem) { _borrowedBooks.add(item) }

    fun hasBorrowed(item: LibraryItem): Boolean {
        return _borrowedBooks.contains(item)
    }
}