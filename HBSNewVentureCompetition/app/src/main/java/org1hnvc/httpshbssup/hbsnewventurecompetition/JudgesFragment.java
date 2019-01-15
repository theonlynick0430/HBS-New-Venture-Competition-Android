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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.JudgeAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Judge;

public class JudgesFragment extends Fragment {

    private List<Judge> judges;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        judges = new ArrayList<>();

        listView = getView().findViewById(R.id.listView);
        JudgeAdapter judgeAdapter = new JudgeAdapter(getActivity().getApplicationContext(), R.layout.judge_list_item, judges);
        listView.setAdapter(judgeAdapter);

        fetchJudges();

        return inflater.inflate(R.layout.fragment_judges, null);
    }

    private void fetchJudges(){
        FirebaseManager.manager.fetchJudges(new FirebaseManager.JudgeCallback() {
            @Override
            public void onSuccess(Judge[] judges) {
                loadJudges(judges);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "An error occurred while fetching the judges",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadJudges(Judge[] judges){
        this.judges.clear();
        for(Judge judge: judges){
            this.judges.add(judge);
        }
    }

}
