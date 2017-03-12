package com.nearwander.application.nearwander.tabbed;

/**
 * Created by Rafli on 3/12/17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nearwander.application.nearwander.R;

public class Tab1Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1home_page, container, false);
        return rootView;
    }
}
