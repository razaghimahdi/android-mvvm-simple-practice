package com.example.mvvmudemy01.model.projectBook;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.mvvmudemy01.BR;

@Entity(tableName = "categories_table")
public class Category extends BaseObservable {/**NOTE: we use BaseObservable to binding*/


    /**NOTE: @Bindable will generate a field in the BR class to identify the field that has changed.*/

    /**NOTE: we use notifyPropertyChanged to passing BR value.*/

    /**NOTE: what is BR in notifyPropertyChanged?
     * The BR class is a generated class by the data binding library, it has generated IDs that are tied to the Bindable
     * annotated fields in the model.
     * */


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @ColumnInfo(name = "category_description")
    private String categoryDescription;


    @Ignore
    public Category() {
    }

    public Category(int id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.categoryDescription);
    }


    @Override
    public String toString() {
        return  this.categoryName ;
    }
}




