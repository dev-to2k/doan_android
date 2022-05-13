package com.truongthanhtrung.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ShowTodoDetails extends AppCompatActivity {
    public static final String URL_SHOW_TODO = "http://192.168.0.126/api/get_todo_details.php";
    private EditText etId;
    private TextView tvTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_todo_details);
        etId = findViewById(R.id.id);
        tvTodo = findViewById(R.id.showTodo);
    }


    public void show_todo(View view) {
        final String id = etId.getText().toString();

        class show_todo extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ShowTodoDetails.this);

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

                return requestHandler.sendPostRequest(URL_SHOW_TODO, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(ShowTodoDetails.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        tvTodo.setVisibility(View.VISIBLE);

                        tvTodo.setText("Todo: " + obj.getString("todo"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ShowTodoDetails.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        show_todo show = new show_todo();
        show.execute();
    }
}