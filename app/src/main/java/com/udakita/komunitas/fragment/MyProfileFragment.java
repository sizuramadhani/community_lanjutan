package com.udakita.komunitas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udakita.komunitas.R;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {


    @BindView(R.id.image_view_avatar)
    ImageView imageViewAvatar;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.text_view_description)
    TextView textViewDescription;
    Unbinder unbinder;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        Toast.makeText(getActivity(), "haiii", Toast.LENGTH_SHORT).show();
        EuclidActivity.mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Oh hi!", Toast.LENGTH_SHORT).show();
            }
        });
        getAdapter();

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public EuclidListAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.andi,
                R.drawable.mada,
        };
        int[] descripsi = {
                R.string.andi,
                R.string.mada,
        };
        String[] names = getResources().getStringArray(R.array.array_names);

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, descripsi[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(getActivity(), R.layout.list_item, profilesList);
    }
}
