package com.example.mvvmudemy01.adapter.part04Project;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.mvvmudemy01.model.projectBook.Book;

import java.util.ArrayList;

public class BooksDiffCallback extends DiffUtil.Callback {

    ArrayList<Book> oldBooksList;
    ArrayList<Book> newBooksList;

    public BooksDiffCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBooksList) {
        this.oldBooksList = oldBooksList;
        this.newBooksList = newBooksList;
    }

    @Override
    public int getOldListSize() {
        return oldBooksList == null ? 0 : oldBooksList.size();
    }

    @Override
    public int getNewListSize() {
        return newBooksList == null ? 0 : newBooksList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBooksList.get(oldItemPosition).getBookId() == newBooksList.get(newItemPosition).getBookId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {/**NOTE: this always return false, because the default equals method only checks the position of the memory of two objects.*/
        return oldBooksList.get(oldItemPosition).equals(newBooksList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {/**NOTE:if areItemTheSame return true and areContentTheSame return false then DiffUtil calls this method to get a playload about the change.*/
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
