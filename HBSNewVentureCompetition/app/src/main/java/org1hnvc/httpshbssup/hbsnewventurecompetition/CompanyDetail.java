package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyMemberAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.CompanyMember;

public class CompanyDetail extends AppCompatActivity {

    private List<CompanyMember> companyMembers;
    private CompanyMemberAdapter companyMemberAdapter;
    private Company company;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_detail);

        companyMembers = new ArrayList<>();
        Bundle b = this.getIntent().getExtras();
        company = b.getParcelable("Company");

        ImageView logoImageView = findViewById(R.id.logoImageView);
        ratingBar = findViewById(R.id.ratingBar);
        Button notesBtn = findViewById(R.id.notesBtn);
        Button websiteBtn = findViewById(R.id.websiteBtn);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ListView listView = findViewById(R.id.listView);
        companyMemberAdapter = new CompanyMemberAdapter(getApplicationContext(), R.layout.company_member_list_item, companyMembers);
        listView.setAdapter(companyMemberAdapter);

        setTitle(company.name);
        fetchRating();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (b == true){
                    SharedPreferences prefs = getSharedPreferences("org1hnvc.httpshbssup.hbsnewventurecompetition", Context.MODE_PRIVATE);
                    if (prefs.getBoolean("VotingEnabled", false) == false){
                        ratingBar.setRating(0);
                        Toast.makeText(getApplicationContext(), "Voting is not enabled on this device. To enable it, go to the the startups tab, click the Vote button in the upper right hand corner, and enter the event code.",
                                Toast.LENGTH_LONG).show();
                    }else{
                        FirebaseManager.manager.addVote(company.companyID, (double) v, new FirebaseManager.CompletionHandler() {
                            @Override
                            public void onSuccess() {}

                            @Override
                            public void onError(String error) {
                                Toast.makeText(getApplicationContext(), "An error occurred while saving your vote",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
        AsyncImageTask asyncImageTask = new AsyncImageTask(logoImageView, false);
        asyncImageTask.execute(company.logoImageURL);
        descriptionTV.setText(company.description);
        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(company.website));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            }
        });
        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyDetail.this, Notes.class);
                Bundle b = new Bundle();
                b.putString("CompanyID", company.companyID);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        fetchCompanyMembers();
    }

    private void fetchRating(){
        CollectionReference companies = FirebaseFirestore.getInstance().collection(NameFile.Firebase.CompanyDB.companies);
        String android_id = Settings.Secure.ANDROID_ID;
        companies.document(company.companyID).collection(NameFile.Firebase.CompanyDB.votes).document(android_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Double stars = document.getDouble(NameFile.Firebase.CompanyDB.stars);
                        ratingBar.setRating(stars.floatValue());
                    } else {
                        ratingBar.setRating(0);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "An error occurred while fetching your vote",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void fetchCompanyMembers(){
        FirebaseManager.manager.fetchCompanyMembers(company.companyID, new FirebaseManager.CompanyMemberCallback() {
            @Override
            public void onSuccess(CompanyMember[] companyMembers) {
                loadCompanyMembers(companyMembers);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "An error occurred while fetching the company members",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCompanyMembers(CompanyMember[] companyMembers){
        this.companyMembers.clear();
        for(CompanyMember companyMember: companyMembers){
            this.companyMembers.add(companyMember);
            Collections.sort(this.companyMembers);
            companyMemberAdapter.notifyDataSetChanged();
        }
    }

}
