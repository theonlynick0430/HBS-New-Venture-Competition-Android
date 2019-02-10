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
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Judge;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

public class JudgeAdapter extends ArrayAdapter<Judge> {

    Context mCtx;
    int resource;
    List<Judge> judgeList;

    public JudgeAdapter(Context mCtx, int resource, List<Judge> judgeList){
        super(mCtx, resource, judgeList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.judgeList = judgeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Judge judge = judgeList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView descriptionTV = view.findViewById(R.id.descriptionTV);
        Button linkedInBtn = view.findViewById(R.id.linkedInBtn);
        ImageView profileImageView = view.findViewById(R.id.profileImageView);

        nameTV.setText(judge.firstName + " " + judge.lastName);
        descriptionTV.setText(judge.description);
        linkedInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(judge.linkedInURL));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(browserIntent);
            }
        });
        AsyncImageTask asyncImageTask = new AsyncImageTask(profileImageView, true);
        asyncImageTask.execute(judge.profileImageURL);

        return view;
    }

    @Override
    public int getCount() {
        return judgeList.size();
    }
}
