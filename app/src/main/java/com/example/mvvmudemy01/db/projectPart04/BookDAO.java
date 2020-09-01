package com.example.mvvmudemy01.db.projectPart04;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmudemy01.model.projectBook.Category;
import com.example.mvvmudemy01.model.projectBook.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBooks();


    @Query("SELECT * FROM book_table WHERE category_id == :categoryId ")
    LiveData<List<Book>> getBooks(int categoryId);



}
