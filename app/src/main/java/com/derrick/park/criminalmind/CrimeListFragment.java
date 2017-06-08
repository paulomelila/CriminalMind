package com.derrick.park.criminalmind;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
/**
 * Created by park on 2017-06-01.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
//    public static final String ID = "ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolder(View v) {
            super(v);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        public void bind(final Crime crime) {
            mCrime = crime;

            mTitleTextView.setText(crime.getTitle());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DateFormat fmt = DateFormat.getDateInstance();
                mDateTextView.setText(fmt.format(crime.getDate()));
            } else {
                mDateTextView.setText(crime.getDate().toString());
            }

            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent crime_info = new Intent(getActivity(), CrimeActivity.class);
//                    crime_info.putExtra(ID, mCrime.getId());
//                    startActivity(crime_info);
                    Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
                    startActivity(intent);
                }
            }));
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public int getItemViewType (int index){
            if (mCrimes.get(index).requiresPolice()) {
                return 0;
            } else {
                return 1;
            }
        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == 0) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_crime, parent, false);
            } else if (viewType == 1) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_serious_crime, parent, false);
            }
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}