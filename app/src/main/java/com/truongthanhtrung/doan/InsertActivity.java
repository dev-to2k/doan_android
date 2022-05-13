package com.truongthanhtrung.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InsertActivity extends AppCompatActivity {
    public static final String URL_ADD_TODO = "http://192.168.0.126/api/create_todo.php";
    private EditText etTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etTodo = findViewById(R.id.todo);
    }

    public void add_todo(View view) {
        final String todo = etTodo.getText().toString();

        class Todo extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(InsertActivity.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("todo", todo);

                return requestHandler.sendPostRequest(URL_ADD_TODO, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(InsertActivity.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Todo todo_exe = new Todo();
        todo_exe.execute();
    }
}