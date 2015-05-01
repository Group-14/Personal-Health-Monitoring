package com.trainer.g14.g_trainer;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

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
    private DatabaseHelper2 db; //db obj
    private List<History> hlist; //history list

    private OnFragmentInteractionListener mListener;

    private static final String TAG = stats.class.getSimpleName();


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
        hlist = db.getAllHistory(); //get all the histories
        int sumcalo =0; //sum of calories out
        int sumcali=0; //sum calories in
        int steps=0; //total steps
        int days = 0; //days working out
        for(History q:hlist){
            sumcalo+=q.getcaloriesOut(); //add up calories out
            sumcali+=q.getCaloriesIn();  //add up calories in
            steps+=q.getSteps();  //add up steps
            if(!q.getRoutine().equals("")) days++; //add the number of histories that contain workouts
        }
        History recent;
        int i = hlist.size();
        if(i!=0) { //if history list is empty, dont do anything
            do {
                i--;
                recent = hlist.get(i);
            } while (recent.getRoutine().equals("") && i != 0); //get the most recent history with a routine
            String recentDate = recent.getDate(); //get its date and routine
            String recentName = recent.getRoutine();

            TextView rn = (TextView) view.findViewById(R.id.recentName);
            TextView rd = (TextView) view.findViewById(R.id.recentDate);
            TextView ci = (TextView) view.findViewById(R.id.calIn);
            TextView co = (TextView) view.findViewById(R.id.calOut);
            TextView dw = (TextView) view.findViewById(R.id.days);
            TextView st = (TextView) view.findViewById(R.id.steps);

            //display data
            rn.setText("You last worked out " + recentName);
            rd.setText("On " + recentDate);
            ci.setText("You have taken in " + sumcali + " Calories");
            co.setText("You have lost " + sumcalo + " Calories");
            dw.setText("You have worked out for " + days + " days");
            st.setText("Total Steps: " + steps);
        }
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
