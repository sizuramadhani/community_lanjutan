package com.udakita.komunitas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udakita.komunitas.R;
import com.udakita.komunitas.activity.DaftarProvinsiKomunitasActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KomunitasFragment extends Fragment {


    @BindView(R.id.btnkomunitas)
    Button btnkomunitas;
    Unbinder unbinder;

    public KomunitasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_komunitas, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnkomunitas)
    public void onViewClicked() {
        getActivity().startActivity(new Intent(getActivity(), DaftarProvinsiKomunitasActivity.class));
    }
}
