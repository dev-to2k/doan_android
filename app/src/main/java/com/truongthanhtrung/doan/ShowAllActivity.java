package com.truongthanhtrung.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowAllActivity extends AppCompatActivity {
    public static final String URL_SHOW_ALL_TODO = "http://192.168.0.126/api/get_all_todo.php";
    ArrayList<Todo> data;
    ListView lvprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
    }

    public void show_all_todo(View view) {
        class show_all_todo extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                return requestHandler.sendPostRequest(URL_SHOW_ALL_TODO, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    if (s.isEmpty()) {
                        Toast.makeText(ShowAllActivity.this, "S khong co gia tri !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        JSONArray jo = obj.getJSONArray("alldata");
                        data = new ArrayList<Todo>();

                        for (int i = 0; i < jo.length(); i++) {
                            data.add(new Todo(jo.getJSONObject(i).getString("todo")));
                        }
                        TodoAdapter adapter = new TodoAdapter(ShowAllActivity.this, R.layout.layout, data);
                        lvprod = findViewById(R.id.list);
                        lvprod.setAdapter(adapter);
                        Toast.makeText(ShowAllActivity.this, jo.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ShowAllActivity.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        show_all_todo show = new show_all_todo();
        show.execute();
    }
}