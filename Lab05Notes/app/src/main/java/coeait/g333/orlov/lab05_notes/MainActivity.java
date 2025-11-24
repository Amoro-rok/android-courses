package coeait.g333.orlov.lab05_notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    ArrayAdapter <Note> adp;
    int sel = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adp = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1);

        ListView lst = findViewById(R.id.lst_notes);
        lst.setAdapter(adp);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                sel = position;
            }
        });
    }

    public void on_new_click(View v)
    {

        Intent i = new Intent(this, NoteActivity.class);
        i.putExtra("note-title", "New note");
        i.putExtra("note-content", "Some content");
        i.putExtra("new-or-edit", 1);


        startActivityForResult(i, 12345);

    }

    public void on_edit_click(View v)
    {
        if (sel == -1) {
            Toast.makeText(this, "Please select a note to edit", Toast.LENGTH_SHORT).show();
            return;
        }
        Note n = adp.getItem(sel);
        if (n != null){
            Intent i = new Intent(this, NoteActivity.class);
            i.putExtra("note-title", n.title);
            i.putExtra("note-content", n.content);
            i.putExtra("new-or-edit", 2);

            startActivityForResult(i, 12345);

        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 1) {
            if (data != null) {
                Note n = new Note(data.getStringExtra("note-title"), data.getStringExtra("note-content"));

                adp.add(n);
                sel = adp.getPosition(n);
            }
        }
        else if (resultCode == 2){
            if (data != null){
                Note n = adp.getItem(sel);

                n.title = data.getStringExtra("note-title");
                n.content = data.getStringExtra("note-content");
            }
        }
        adp.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void on_del_click(View v)
    {
        if (sel == -1) {
            Toast.makeText(this, "Please select a note to delete", Toast.LENGTH_SHORT).show();
            return;
        }
        adp.remove(adp.getItem(sel));
        sel = -1;
    }
}
