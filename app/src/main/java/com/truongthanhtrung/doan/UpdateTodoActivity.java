package com.truongthanhtrung.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class UpdateTodoActivity extends AppCompatActivity {
    public static final String URL_UPDATE_TODO = "http://192.168.0.126/api/update_todo.php";
    private EditText etIdUpdate, etTodoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo_activity);
        etIdUpdate = findViewById(R.id.id_update);
        etTodoUpdate = findViewById(R.id.todo_update);
    }

    public void update_todo(View view) {
        final String id = etIdUpdate.getText().toString();
        final String todo = etTodoUpdate.getText().toString();

        class Update extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(UpdateTodoActivity.this);

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
                params.put("id", id);
                params.put("todo", todo);

                return requestHandler.sendPostRequest(URL_UPDATE_TODO, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(UpdateTodoActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateTodoActivity.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Update update = new Update();
        update.execute();
    }

}