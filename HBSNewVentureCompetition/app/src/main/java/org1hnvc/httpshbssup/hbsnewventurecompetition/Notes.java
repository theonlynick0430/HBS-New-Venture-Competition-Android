package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;

public class Notes extends AppCompatActivity {

    private EditText notesTV;
    private String companyID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        companyID = b.getString("CompanyID");

        notesTV = findViewById(R.id.notesTV);

        fetchNotes();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (notesTV.getText() != null){
            FirebaseManager.manager.addNotes(companyID, notesTV.getText().toString(), new FirebaseManager.CompletionHandler() {
                @Override
                public void onSuccess() {}

                @Override
                public void onError(String error) {
                    Toast.makeText(getApplicationContext(), "An error occurred while saving your notes",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchNotes(){
        FirebaseManager.manager.fetchNotes(companyID, new FirebaseManager.NotesCallback() {
            @Override
            public void onSuccess(String notes) {
                notesTV.setText(notes);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "An error occurred while fetching your notes",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
