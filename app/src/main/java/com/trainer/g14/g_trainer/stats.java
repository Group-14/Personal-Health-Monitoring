package com.trainer.g14.g_trainer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sqlite.helper.DatabaseHelper2;
import sqlite.model.History;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link stats.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link stats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stats extends Fragment {
    private DatabaseHelper2 db;
    private List<History> hlist;

    private static final String TAG = MainActivity.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static stats newInstance() {
        stats fragment = new stats();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public stats() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        hlist = db.getAllHistory();
        int sumcalo =0;
        int sumcali=0;
        int days = hlist.size();
        for(History q:hlist){
            sumcalo+=q.getcaloriesOut();
            sumcali+=q.getCaloriesIn();
        }
        History recent = hlist.get(hlist.size()-1);
        String recentDate = recent.getDate();
        String recentName = recent.getRoutine();

        TextView rn = (TextView) view.findViewById(R.id.recentName);
        TextView rd = (TextView) view.findViewById(R.id.recentDate);
        TextView ci = (TextView) view.findViewById(R.id.calIn);
        TextView co = (TextView) view.findViewById(R.id.calOut);
        TextView dw = (TextView) view.findViewById(R.id.days);

        rn.setText("Last Workout: "+recentName);
        rd.setText("On "+recentDate);
        ci.setText("Caloric Intake: " + sumcali);
        co.setText("Caloric Outake: " + sumcalo);
        dw.setText("Days Workedout: " + days);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        db=new DatabaseHelper2(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
