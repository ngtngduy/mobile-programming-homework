package com.example.librarymanagementsystem

abstract class LibraryItem(val title: String) {
    abstract fun getInfo(): String
    private var isBorrowed = false

    fun borrow(): Boolean {
        if (isBorrowed) return false
        isBorrowed = true
        return true
    }

    fun giveBack() {
        isBorrowed = false
    }

    fun isAvailable(): Boolean {
        return !isBorrowed
    }
}

class Book(title: String, val author: String) : LibraryItem(title) {
    override fun getInfo(): String {
        val status = if (isAvailable()) "còn" else "đã mượn"
        return "Sách: $title - tác giả $author ($status)"
    }
}
class Magazine(title: String, val issue: String) : LibraryItem(title) {
    override fun getInfo(): String {
        val status = if (isAvailable()) "còn" else "đã mượn"
        return "Tạp chí: $title - số $issue ($status)"
    }
}

