package com.example.student.nguyenthaithanhdat_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.helper.DBHelper;
import com.example.student.model.Book;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView lvBooks;
    Button btnThem;
    Button btnSua;
    Button btnXoa;
    EditText edtName;
    EditText edtPrice;
    private DBHelper myDB;
    boolean isBookSelected = false;
    Book bookSelected = new Book();
    ArrayList<Book> lstBooks;
    ArrayAdapter<Book> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        Button btnThem = (Button) findViewById(R.id.btnAdd);
        Button btnSua = (Button) findViewById(R.id.btnUpdate);
        Button btnXoa = (Button) findViewById(R.id.btnDelete);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        myDB = new DBHelper(this);


        lstBooks = myDB.getAllBooks();


        adapter = new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1, lstBooks);

        lvBooks.setAdapter(adapter);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Book b = new Book();
                b.setName(edtName.getText().toString());
                b.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                myDB.insertBook(b);
                lstBooks.clear();
                lstBooks.addAll(myDB.getAllBooks());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
            }
        });


        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                bookSelected = lstBooks.get(position);
                isBookSelected = true;
                Toast.makeText(MainActivity.this, lstBooks.get(position).getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBookSelected) {
                    Toast.makeText(MainActivity.this, "Chưa chọn sách để sửa", Toast.LENGTH_SHORT).show();
                    return;
                }
                Book b = new Book();
                b.setId(bookSelected.getId());
                b.setName(edtName.getText().toString());
                b.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                myDB.updateBook(b);
                lstBooks.clear();
                lstBooks.addAll(myDB.getAllBooks());
                adapter.notifyDataSetChanged();
                isBookSelected = false;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBookSelected) {
                    Toast.makeText(MainActivity.this, "Chưa chọn sách để xóa", Toast.LENGTH_SHORT).show();
                    return;
                }
                myDB.deleteBook(bookSelected.getId());
                lstBooks.clear();
                lstBooks.addAll(myDB.getAllBooks());
                adapter.notifyDataSetChanged();
                isBookSelected = false;
            }
        });


    }
}
