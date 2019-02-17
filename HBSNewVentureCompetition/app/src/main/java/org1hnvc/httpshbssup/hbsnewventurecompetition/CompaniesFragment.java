package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), CompanyDetail.class);
                Bundle b = new Bundle();
                b.putParcelable("Company", companies.get(i));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        fetchCompanies();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.company_action_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.company_action_bar_number:
                displayNumberList();
                return true;
            case R.id.company_action_bar_vote:
                displayVoting();
                return true;
        }
        return false;
    }

    private void displayNumberList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Select the company's designated number");
        String[] numbers = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++){
            numbers[i] = Integer.toString(i+1);
        }
        builder.setItems(numbers, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), CompanyDetail.class);
                Bundle b = new Bundle();
                b.putParcelable("Company", companies.get(which));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayVoting(){
        final SharedPreferences prefs = this.getActivity().getSharedPreferences("org1hnvc.httpshbssup.hbsnewventurecompetition", Context.MODE_PRIVATE);
        if (prefs.getBoolean("VotingEnabled", false) == true){
            Toast.makeText(getActivity(), "Voting has already been enabled on this device",
                    Toast.LENGTH_LONG).show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            final EditText edittext = new EditText(this.getContext());
            builder.setMessage("Enter the code for this event to enable voting capabilities");
            builder.setView(edittext);
            builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    final String code = edittext.getText().toString();
                    FirebaseManager.manager.fetchEventCode(new FirebaseManager.EventCodeCallback() {
                        @Override
                        public void onSuccess(String eventCode) {
                            if (!code.equals(eventCode)){
                                Toast.makeText(getActivity(), "Wrong event code",
                                        Toast.LENGTH_LONG).show();
                            }else{
                                prefs.edit().putBoolean("VotingEnabled", true).apply();
                                Toast.makeText(getActivity(), "Voting enabled",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(getActivity(), "An error occurred while fetching the event code",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
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
            Collections.sort(this.companies);
            companyAdapter.notifyDataSetChanged();
        }
    }

}
