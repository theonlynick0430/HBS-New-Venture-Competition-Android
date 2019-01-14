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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.JudgeAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Judge;

public class JudgesFragment extends Fragment {

    List<Judge> judges;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        judges = new ArrayList<>();
        //get judges from Firebase

        listView = getView().findViewById(R.id.listView);
        JudgeAdapter judgeAdapter = new JudgeAdapter(getActivity().getApplicationContext(), R.layout.judge_list_item, judges);
        listView.setAdapter(judgeAdapter);

        return inflater.inflate(R.layout.fragment_judges, null);

    }
}
