package com.example.ns;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class Tab2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        Button button2 = (Button) rootView.findViewById(R.id.button2);
        Button button3 = (Button) rootView.findViewById(R.id.button3);
        Button button4 = (Button) rootView.findViewById(R.id.button4);
        Button button5 = (Button) rootView.findViewById(R.id.button5);
        Button button6 = (Button) rootView.findViewById(R.id.button6);
        Button button7 = (Button) rootView.findViewById(R.id.button7);
        ((Button) rootView.findViewById(R.id.button)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), TeluguActivity.class));
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), HindiActivity.class));
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), EnglishActivity.class));
            }
        });
        button4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), MathsActivity.class));
            }
        });
        button5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), PhysicsActivity.class));
            }
        });
        button6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), NsActivity.class));
            }
        });
        button7.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tab2.this.getActivity().startActivity(new Intent(Tab2.this.getActivity(), SocialActivity.class));
            }
        });
        return rootView;
    }

    private void setContentView(int activity_main) {
    }

    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
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
