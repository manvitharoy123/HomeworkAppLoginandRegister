package com.example.ns;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Tab3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    public void onButtonPressed(Uri uri) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.mListener;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.toString());
        stringBuilder.append(" must implement OnFragmentInteractionListener");
        throw new RuntimeException(stringBuilder.toString());
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
