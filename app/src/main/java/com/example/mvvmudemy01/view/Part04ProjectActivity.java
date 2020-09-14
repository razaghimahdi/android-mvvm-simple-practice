package com.example.mvvmudemy01.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.part04Project.BooksAdapter;
import com.example.mvvmudemy01.databinding.ActivityPart04Project2Binding;
import com.example.mvvmudemy01.model.projectBook.Book;
import com.example.mvvmudemy01.model.projectBook.Category;
import com.example.mvvmudemy01.model.projectBook.Part04Repository;
import com.example.mvvmudemy01.view.part04.Part04AddAndEditActivity;
import com.example.mvvmudemy01.viewmodel.Part04ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Part04ProjectActivity extends AppCompatActivity {

    private Part04ViewModel part04ViewModel;
    private ActivityPart04Project2Binding activityPart04Project2Binding;
    private Part04ClickHandlers handlers;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> booksList;
    private Category selectedCategory;
    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;
    private int selectedBookId;

    public static final int ADD_BOOK_REQUEST_CODE=1;
    public static final int EDIT_BOOK_REQUEST_CODE=2;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId=selectedCategory.getId();
        if (requestCode==ADD_BOOK_REQUEST_CODE && resultCode==RESULT_OK){
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(Part04AddAndEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(Part04AddAndEditActivity.UNIT_PRICE));
            part04ViewModel.addNewBook(book);
        }else if (requestCode==EDIT_BOOK_REQUEST_CODE&&resultCode==RESULT_OK){
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(Part04AddAndEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(Part04AddAndEditActivity.UNIT_PRICE));
            book.setBookId(selectedBookId);
            part04ViewModel.updateBook(book);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part04_project2);
        activityPart04Project2Binding = DataBindingUtil.setContentView(this,R.layout.activity_part04_project2);

        handlers = new Part04ClickHandlers();
        activityPart04Project2Binding.setClickHandlers(handlers);

        part04ViewModel = new ViewModelProvider(this).get(Part04ViewModel.class);


        part04ViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                categoriesList=(ArrayList<Category>)categories;

                for (Category category :
                        categories) {
                    Log.i("Category", "onChanged category: "+category.getCategoryName());
                }

                showOnSpinner();

            }
        });


    }

    private void loadBookArrayList(int categoryId){

        part04ViewModel.getBooksOfASelectedCategory(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {

                booksList=(ArrayList<Book>) books;
                loadRecyclerView();
            }
        });



    }


    private void loadRecyclerView(){
        recyclerView = activityPart04Project2Binding.part04SecondaryLayout.rvBooks;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        booksAdapter = new BooksAdapter();
        recyclerView.setAdapter(booksAdapter);

        booksAdapter.setBooks(booksList);
        booksAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId=book.getBookId();
                Intent intent = new Intent(Part04ProjectActivity.this, Part04AddAndEditActivity.class);
                intent.putExtra(Part04AddAndEditActivity.BOOK_ID,selectedBookId);
                intent.putExtra(Part04AddAndEditActivity.BOOK_NAME,book.getBookName());
                intent.putExtra(Part04AddAndEditActivity.UNIT_PRICE,book.getUnitPrice());
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Book bookToDelete=booksList.get(viewHolder.getAdapterPosition());
                part04ViewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(recyclerView);


    }


    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.spinner_item, categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityPart04Project2Binding.setSpinnerAdapter(categoryArrayAdapter);
    }


    public class Part04ClickHandlers{


        public void onFABClicked(View view){
            //Toast.makeText(Part04ProjectActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Part04ProjectActivity.this, Part04AddAndEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);
        }



        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();

            loadBookArrayList(selectedCategory.getId());

        }

    }



}