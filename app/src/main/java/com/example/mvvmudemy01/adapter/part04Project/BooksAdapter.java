package com.example.mvvmudemy01.adapter.part04Project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.databinding.BookListItemBinding;
import com.example.mvvmudemy01.model.projectBook.Book;

import java.util.ArrayList;

public class BooksAdapter extends  RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    private OnItemClickListener listener;
    private ArrayList<Book> books=new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BookListItemBinding bookListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_list_item,parent,false);

        return new BookViewHolder(bookListItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks) {
        //this.books = books;
        //notifyDataSetChanged();
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BooksDiffCallback(books,newBooks), false);
        books=newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        private BookListItemBinding bookListItemBinding;
        private ArrayList<Book> books = new ArrayList<>();

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding=bookListItemBinding;

            bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPostion = getAdapterPosition();

                    if (listener!=null && clickPostion!=RecyclerView.NO_POSITION){
                        listener.onItemClick(books.get(clickPostion));
                    }

                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
