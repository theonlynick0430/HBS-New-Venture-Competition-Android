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
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Sponsor;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

public class SponsorAdapter extends ArrayAdapter<Sponsor> {

    Context mCtx;
    int resource;
    List<Sponsor> sponsorList;

    public SponsorAdapter(Context mCtx, int resource, List<Sponsor> sponsorList){
        super(mCtx, resource, sponsorList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.sponsorList = sponsorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Sponsor sponsor = sponsorList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView descriptionTV = view.findViewById(R.id.descriptionTV);
        Button websiteBtn = view.findViewById(R.id.websiteBtn);
        ImageView logoImageView = view.findViewById(R.id.logoImageView);

        nameTV.setText(sponsor.name);
        descriptionTV.setText(sponsor.description);
        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sponsor.website));
                view.getContext().startActivity(browserIntent);
            }
        });
        AsyncImageTask asyncImageTask = new AsyncImageTask(logoImageView);
        asyncImageTask.execute(sponsor.logoImageURL);

        return view;
    }

}
