package coeait.g333.orlov.lab05_notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NoteActivity extends AppCompatActivity {

    EditText txt_title;
    EditText txt_content;

    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_title = findViewById(R.id.note_name_edit_txt);
        txt_content = findViewById(R.id.note_text_edit_txt);

        Intent i = getIntent();
        txt_title.setText(i.getStringExtra("note-title"));
        txt_content.setText(i.getStringExtra("note-content"));
        mode = i.getIntExtra("new-or-edit", 0);
    }

    public void on_cancel_click(View v)
    {
        finish();
    }

    public void on_save_click(View v)
    {
        Intent i = new Intent();
        i.putExtra("note-title", txt_title.getText().toString());
        i.putExtra("note-content", txt_content.getText().toString());
        i.putExtra("save-or-cancel", true);

        setResult(mode, i);

        finish();
    }
}