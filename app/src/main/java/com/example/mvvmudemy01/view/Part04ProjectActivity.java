package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.databinding.ActivityPart04Project2Binding;
import com.example.mvvmudemy01.model.projectBook.Book;
import com.example.mvvmudemy01.model.projectBook.Category;
import com.example.mvvmudemy01.model.projectBook.Part04Repository;
import com.example.mvvmudemy01.viewmodel.Part04ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Part04ProjectActivity extends AppCompatActivity {

    private Part04ViewModel part04ViewModel;
    private ActivityPart04Project2Binding activityPart04Project2Binding;
    private Part04ClickHandlers handlers;
    private ArrayList<Category> categoriesList;
    private Category selectedCategory;


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


        part04ViewModel.getBooksOfASelectedCategory(3).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                for (Book book :
                        books) {
                    Log.i("Book", "onChanged book: "+book.getBookName());
                }
            }
        });



    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.spinner_item, categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityPart04Project2Binding.setSpinnerAdapter(categoryArrayAdapter);
    }


    public class Part04ClickHandlers{


        public void onFABClicked(View view){
            Toast.makeText(Part04ProjectActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
        }



        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();


        }

    }



}