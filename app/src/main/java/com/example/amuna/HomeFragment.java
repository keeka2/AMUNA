package com.example.amuna;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import static com.example.amuna.LoginSplash.userList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = (ListView)layout.findViewById(R.id.listview2);
        ListAdapter adapter = new ListAdapter(getActivity(),userList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return layout;
       // String Email = this.getArgument().getString("Email");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tv = (String)parent.getAdapter().getItem(position);
        

    }
}