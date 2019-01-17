package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;

public class CompaniesFragment extends Fragment {

    private List<Company> companies;
    private ListView listView;
    private  CompanyAdapter companyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        companies = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_companies, container, false);
        listView = rootView.findViewById(R.id.listView);
        companyAdapter = new CompanyAdapter(getActivity().getApplicationContext(), R.layout.company_list_item, companies);
        listView.setAdapter(companyAdapter);

        fetchCompanies();

        return rootView;
    }

    private void fetchCompanies(){
        FirebaseManager.manager.fetchCompanies(new FirebaseManager.CompanyCallback() {
            @Override
            public void onSuccess(Company[] companies) {
                loadCompanies(companies);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "An error occurred while fetching the companies",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCompanies(Company[] companies){
        this.companies.clear();
        for(Company company: companies){
            this.companies.add(company);
            companyAdapter.notifyDataSetChanged();
        }
    }

}
