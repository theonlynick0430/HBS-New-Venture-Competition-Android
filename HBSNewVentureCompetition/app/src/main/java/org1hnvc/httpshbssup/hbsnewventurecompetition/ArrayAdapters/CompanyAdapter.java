package org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.AsyncImageTask;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

public class CompanyAdapter extends ArrayAdapter<Company> {

    Context mCtx;
    int resource;
    List<Company> companyList;

    public CompanyAdapter(Context mCtx, int resource, List<Company> companyList){
        super(mCtx, resource, companyList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.companyList = companyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Company company = companyList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView descriptionTV = view.findViewById(R.id.descriptionTV);
        ImageView logoImageView = view.findViewById(R.id.logoImageView);

        nameTV.setText(company.name);
        descriptionTV.setText(company.description);
        AsyncImageTask asyncImageTask = new AsyncImageTask(logoImageView);
        asyncImageTask.execute(company.logoImageURL);

        return view;
    }

    @Override
    public int getCount() {
        return companyList.size();
    }

}
