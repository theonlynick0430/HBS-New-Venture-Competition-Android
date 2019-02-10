package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyMemberAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.CompanyMember;

public class CompanyDetail extends AppCompatActivity {

    private List<CompanyMember> companyMembers;
    private CompanyMemberAdapter companyMemberAdapter;
    private Company company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_detail);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        //Button notesBtn = findViewById(R.id.notesBtn);
        Button websiteBtn = findViewById(R.id.websiteBtn);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ListView listView = findViewById(R.id.listView);
        companyMemberAdapter = new CompanyMemberAdapter(getApplicationContext(), R.layout.company_member_list_item, companyMembers);
        listView.setAdapter(companyMemberAdapter);

//        Bundle b = this.getIntent().getExtras();
//        company = b.getParcelable("Company");
//
//        ratingBar.setRating(company.stars.floatValue());
//        AsyncImageTask asyncImageTask = new AsyncImageTask(logoImageView, false);
//        asyncImageTask.execute(company.logoImageURL);
//        descriptionTV.setText(company.description);
//        websiteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(company.website));
//                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(browserIntent);
//            }
//        });
//
//        fetchCompanyMembers();
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
            companyMemberAdapter.notifyDataSetChanged();
        }
    }

}
