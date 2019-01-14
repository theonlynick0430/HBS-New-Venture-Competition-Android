package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;

public class CompaniesFragment extends Fragment {

    List<Company> companies;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        companies = new ArrayList<>();
        //get companies from Firebase

        listView = getView().findViewById(R.id.listView);
        CompanyAdapter companyAdapter = new CompanyAdapter(getActivity().getApplicationContext(), R.layout.company_list_item, companies);
        listView.setAdapter(companyAdapter);

        return inflater.inflate(R.layout.fragment_companies, null);
    }
}
