package com.nearwander.application.nearwander.tabbed;

/**
 * Created by Rafli on 3/12/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
/*import com.nearwander.application.nearwander.tabbed.nearby.PlacesMain;
import com.nearwander.application.nearwander.tabbed.*;*/
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.nearwander.application.nearwander.R;

public class Tab1Home extends Fragment {

    private EditText mEditText;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private int REQUEST_CODE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1home_page, container, false);

        mEditText = (EditText) rootView.findViewById(R.id.search_homePage);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openApp(getActivity().getApplicationContext(), "com.infinity.dev.nearby");
                /*try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }*/
                /*Intent intent = new Intent(getActivity(), PlacesMain.class);
                getActivity().startActivity(intent);*/
            }
        });
        return rootView;
    }

    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            return false;
            //throw new PackageManager.NameNotFoundException();
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }
}
