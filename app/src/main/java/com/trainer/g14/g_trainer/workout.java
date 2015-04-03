package com.trainer.g14.g_trainer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import sqlite.model.Routine;
import sqlite.model.Exercise;
import sqlite.model.RepSet;
import sqlite.helper.DatabaseHelper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link workout.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link workout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class workout extends Fragment implements View.OnClickListener{
    View view;
    ListView listView;
    View lastSelectedView=null;
    DatabaseHelper db;
    String routine=null;

    public static List<exercise> workout = new ArrayList<exercise>();

    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment workout.
     */
    // TODO: Rename and change types and number of parameters
    public static workout newInstance() {
        workout fragment = new workout();
        return fragment;
    }

    public workout() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        Button start = (Button) view.findViewById(R.id.start);
        start.setOnClickListener(this);
        // Inflate the layout for this fragment


        listView = (ListView) view.findViewById(R.id.routines);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        List<Routine> elist = db.getAllRoutines();
        List<String> values = new ArrayList<String>();
        for(Routine q:elist){
            values.add(q.getName());
        }

        // Defined Array values to show in ListView
        /*String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                listView.setItemChecked(position, true);
                clearSelection();
                lastSelectedView=view;
                view.setBackgroundColor(getResources().getColor(R.color.pressed_color));
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                routine    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Log.i(TAG, "Position: " +itemPosition+"  ListItem: " +routine);
                //Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                //        .show();

            }

        });
        return view;
    }

    public void clearSelection(){
        if(lastSelectedView != null) lastSelectedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        if(routine!=null) {
            List<Exercise> exercises = db.getAllExerciesByRoutine(routine);
            List<RepSet> repSets = db.getAllRepSetsByRoutine(routine);
            for(int q = 0; q<exercises.size(); q++){
                workout.add(new exercise(exercises.get(q).getName(), repSets.get(q).getSets(), repSets.get(q).getReps()));
            }

            Intent intent = new Intent(getActivity(), trainer.class);
            intent.putExtra("rt", routine);
            startActivity(intent);
        }
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
        db=new DatabaseHelper(getActivity());
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
