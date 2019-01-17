package org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.AsyncImageTask;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.CompanyMember;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

public class CompanyMemberAdapter extends ArrayAdapter<CompanyMember> {

    Context mCtx;
    int resource;
    List<CompanyMember> companyMemberList;

    public CompanyMemberAdapter(Context mCtx, int resource, List<CompanyMember> companyMemberList){
        super(mCtx, resource, companyMemberList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.companyMemberList = companyMemberList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CompanyMember companyMember = companyMemberList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView emailTV = view.findViewById(R.id.emailTV);
        TextView positionTV = view.findViewById(R.id.positionTV);
        TextView educationTV = view.findViewById(R.id.educationTV);
        Button phoneBtn = view.findViewById(R.id.phoneBtn);
        Button linkedInBtn = view.findViewById(R.id.linkedInBtn);
        ImageView profileImageView = view.findViewById(R.id.profileImageView);

        nameTV.setText(companyMember.firstName + " " + companyMember.lastName);
        emailTV.setText(companyMember.email);
        positionTV.setText(companyMember.position);
        educationTV.setText(companyMember.education);
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String androidPhoneNumber = "tel:" + companyMember.phoneNumber;
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse(androidPhoneNumber));
                view.getContext().startActivity(dialIntent);
            }
        });
        linkedInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyMember.linkedInURL));
                view.getContext().startActivity(browserIntent);
            }
        });
        AsyncImageTask asyncImageTask = new AsyncImageTask(profileImageView);
        asyncImageTask.execute(companyMember.profileImageURL);

        return view;
    }

    @Override
    public int getCount() {
        return companyMemberList.size();
    }

}
