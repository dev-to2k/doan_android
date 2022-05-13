package com.truongthanhtrung.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsertProduct;
    Button btnShowAllProduct;
    Button btnShowProductDetails;
    Button btnUpdatePriceProduct;
    Button btnDeleteProduct;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertProduct = findViewById(R.id.btnInsertTodo);
        btnInsertProduct.setOnClickListener(this);

        btnShowAllProduct = findViewById(R.id.btnShowAllTodo);
        btnShowAllProduct.setOnClickListener(this);

        btnShowProductDetails = findViewById(R.id.btnShowTodoDetails);
        btnShowProductDetails.setOnClickListener(this);

        btnUpdatePriceProduct = findViewById(R.id.btnUpdateTodo);
        btnUpdatePriceProduct.setOnClickListener(this);

        btnDeleteProduct = findViewById(R.id.btnDeleteTodo);
        btnDeleteProduct.setOnClickListener(this);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsertTodo: {
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowAllTodo: {
                Intent i = new Intent(MainActivity.this, ShowAllActivity.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowTodoDetails: {
                Intent i = new Intent(MainActivity.this, ShowTodoDetails.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnUpdateTodo: {
                Intent i = new Intent(MainActivity.this, UpdateTodoActivity.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnDeleteTodo: {
                Intent i = new Intent(MainActivity.this, DeleteActivity.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnExit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }

}