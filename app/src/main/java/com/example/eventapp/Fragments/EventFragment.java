package com.example.eventapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventapp.Interfaces.DatePickerInterface;
import com.example.eventapp.R;
import com.example.eventapp.Interfaces.TimePickerInterface;
import com.example.eventapp.Utils.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EventFragment extends Fragment implements View.OnClickListener, TimePickerInterface, DatePickerInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView startTimeTv, startDateTv, endTimeTv, endDateTv;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public EventFragment() {
        // Required empty public constructor
    }


    DateUtils dateUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        initUi();
        registerListeners();

        dateUtils = new DateUtils();

        String dateInMilli = getArguments().getString("dateinmilli");
        String date = getArguments().getString("date");
        Toast.makeText(getActivity(), "Date: " + dateInMilli, Toast.LENGTH_SHORT).show();

        String fullDate = dateUtils.milliToDate(Long.parseLong(dateInMilli));

        Toast.makeText(getActivity(), "" + fullDate, Toast.LENGTH_SHORT).show();


        startDateTv.setText(fullDate);
        endDateTv.setText(fullDate);
        startTimeTv.setText("8 : 00 AM");
        endTimeTv.setText("9 : 00 AM");
    }

    private void registerListeners() {

        startTimeTv.setOnClickListener(this);
        startDateTv.setOnClickListener(this);

        endTimeTv.setOnClickListener(this);
        endDateTv.setOnClickListener(this);
    }

    void initUi() {
        startTimeTv = getActivity().findViewById(R.id.event_time_tv);
        startDateTv = getActivity().findViewById(R.id.event_date_tv);
        endTimeTv = getActivity().findViewById(R.id.event_end_time_tv);
        endDateTv = getActivity().findViewById(R.id.event_end_date_tv);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.event_time_tv)
        {
            TimePickerFragment newFragment = new TimePickerFragment();
            newFragment.time(this, startTimeTv);
            newFragment.show(getActivity().getFragmentManager(), "TimePicker");
        }

        else if(v.getId() == R.id.event_date_tv)
        {
            DatePickerFragment dateFragment = new DatePickerFragment();
            dateFragment.date(this, startDateTv);
            dateFragment.show(getActivity().getFragmentManager(), "DatePicker");
        }
        else if(v.getId() == R.id.event_end_time_tv)
        {
            TimePickerFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.time(this, endTimeTv);
            timePickerFragment.show(getActivity().getFragmentManager(), "DatePicker");
        }
        else if(v.getId() == R.id.event_end_date_tv)
        {
            DatePickerFragment dateFragment = new DatePickerFragment();
            dateFragment.date(this, endDateTv);
            dateFragment.show(getActivity().getFragmentManager(), "DatePicker");
        }
    }

    @Override
    public void onSetTimeBtnClick(String time, TextView textView) {
//        TextView tv = (TextView) getActivity().findViewById(R.id.event_time_tv);

        textView.setText(time);
    }

    @Override
    public void onSetDate(String date, TextView textview) {
        textview.setText(date);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
