package com.example.mosaab.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Add_edit_note_Activity extends AppCompatActivity {

    public static final String EXSTRA_ID =
            "com.example.mosaab.note.EXTRA_ID";

    public static final String EXSTRA_TITLE =
            "com.example.mosaab.note.EXTRA_TITLE";

    public static final String EXSTRA_DESCRIPTION =
            "com.example.mosaab.note.EXTRA_DESCRIPTION";

    public static final String EXSTRA_PRIORITY =
            "com.example.mosaab.note.EXTRA_PRIORITY";

    private TextView priorty_tv;
    private EditText title_edt,desc_edt;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_);

        Init();
    }

    private void Init() {

        priorty_tv = findViewById(R.id.pariorty_tv);
        title_edt = findViewById(R.id.edit_title_tv);
        desc_edt = findViewById(R.id.edit_desc_tv);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        if (getIntent().hasExtra(EXSTRA_ID))
        {
            setTitle("Edit Note");
            title_edt.setText(getIntent().getStringExtra(EXSTRA_TITLE));
            desc_edt.setText(getIntent().getStringExtra(EXSTRA_DESCRIPTION));
            numberPicker.setValue(getIntent().getIntExtra(EXSTRA_ID,1));

        }
        else
        {
            setTitle("Add Note");

        }
    }


    private void SaveNote() {
        String title = title_edt.getText().toString();
        String description = desc_edt.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "please fill Info", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXSTRA_TITLE,title);
        data.putExtra(EXSTRA_DESCRIPTION,description);
        data.putExtra(EXSTRA_PRIORITY,priority);


        int id = getIntent().getIntExtra(EXSTRA_ID,-1);
        if (id != -1)
        {
            data.putExtra(EXSTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.save_note:

                SaveNote();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
