package com.example.mvvmudemy01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmudemy01.model.projectBook.Book;
import com.example.mvvmudemy01.model.projectBook.Category;
import com.example.mvvmudemy01.model.projectBook.Part04Repository;

import java.util.List;

public class Part04ViewModel extends AndroidViewModel {//we want to use the application context so we should extend AndroidViewModel

    private Part04Repository part04Repository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Book>> booksOfASelectedCategory;


    public Part04ViewModel(@NonNull Application application) {
        super(application);

        part04Repository=new Part04Repository(application);
    }



    public LiveData<List<Category>> getAllCategories() {

        allCategories=part04Repository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getBooksOfASelectedCategory(int categoryId) {

        booksOfASelectedCategory=part04Repository.getBooks(categoryId);
        return booksOfASelectedCategory;
    }

    public void addNewBook(Book book){
        part04Repository.insertBook(book);
    }

    public void updateBook(Book book){
        part04Repository.updateBook(book);
    }

    public void deleteBook(Book book){
        part04Repository.deleteBook(book);
    }



}
