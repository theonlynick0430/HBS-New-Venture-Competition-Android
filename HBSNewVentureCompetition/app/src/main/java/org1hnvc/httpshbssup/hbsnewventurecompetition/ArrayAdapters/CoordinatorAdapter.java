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
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Coordinator;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

public class CoordinatorAdapter extends ArrayAdapter<Coordinator> {

    Context mCtx;
    int resource;
    List<Coordinator> coordinatorList;

    public CoordinatorAdapter(Context mCtx, int resource, List<Coordinator> coordinatorList){
        super(mCtx, resource, coordinatorList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.coordinatorList = coordinatorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Coordinator coordinator = coordinatorList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView positionTV = view.findViewById(R.id.positionTV);
        TextView organizationTV = view.findViewById(R.id.organizationTV);
        Button linkedInBtn = view.findViewById(R.id.linkedInBtn);
        ImageView profileImageView = view.findViewById(R.id.profileImageView);

        nameTV.setText(coordinator.firstName + " " + coordinator.lastName);
        positionTV.setText(coordinator.position);
        organizationTV.setText(coordinator.organization);
        linkedInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinator.linkedInURL));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(browserIntent);
            }
        });
        AsyncImageTask asyncImageTask = new AsyncImageTask(profileImageView);
        asyncImageTask.execute(coordinator.profileImageURL);

        return view;
    }

    @Override
    public int getCount() {
        return coordinatorList.size();
    }

}
