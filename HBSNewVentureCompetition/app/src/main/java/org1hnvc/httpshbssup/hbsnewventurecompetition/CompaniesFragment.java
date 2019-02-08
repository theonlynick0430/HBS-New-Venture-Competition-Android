package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CompanyAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Company;

public class CompaniesFragment extends Fragment {

    private List<Company> companies;
    private ListView listView;
    private EditText searchView;
    private  CompanyAdapter companyAdapter;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null){
            return  view;
        }

        companies = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_companies, container, false);
        listView = rootView.findViewById(R.id.listView);
        searchView = rootView.findViewById(R.id.searchView);
        companyAdapter = new CompanyAdapter(getActivity().getApplicationContext(), R.layout.company_list_item, companies);
        listView.setAdapter(companyAdapter);
        view = rootView;

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                companyAdapter.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        searchView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                searchView.clearFocus();
                return true;
            }
        });

        fetchCompanies();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.company_action_bar, menu);
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
