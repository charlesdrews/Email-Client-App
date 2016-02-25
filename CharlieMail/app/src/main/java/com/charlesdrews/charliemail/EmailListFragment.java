package com.charlesdrews.charliemail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by charlie on 2/25/16.
 */
public class EmailListFragment extends Fragment {
    private ArrayList<Email> mEmails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO - figure out how savedInstanceState works for fragments - may be able to keep using same instance?
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ListsPagerAdapter.SELECTED_TAB_KEY)) {
            int selectedTab = getArguments().getInt(ListsPagerAdapter.SELECTED_TAB_KEY);
            getEmails(selectedTab);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_email_list, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new EmailListAdapter(mEmails));
        return rootView;
    }

    private void getEmails(int selectedTab) {
        //TODO - this is just for testing
        //TODO - this should be async - call gmail api here
        String list;
        switch (selectedTab) {
            case 0:
                list = "inbox ";
                break;
            case 1:
                list = "drafts ";
                break;
            case 2:
                list = "sent ";
                break;
            default:
                list = "";
        }

        if (mEmails == null) {
            mEmails = new ArrayList<>();
        } else {
            mEmails.clear();
        }

        for (int i = 0; i < 10; i++) {
            mEmails.add(new Email(list + "email #" + i));
        }
    }
}