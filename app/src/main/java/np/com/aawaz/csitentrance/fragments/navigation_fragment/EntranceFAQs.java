package np.com.aawaz.csitentrance.fragments.navigation_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import np.com.aawaz.csitentrance.R;
import np.com.aawaz.csitentrance.adapters.FAQAdapter;
import np.com.aawaz.csitentrance.objects.FAQ;
import np.com.aawaz.csitentrance.objects.FeaturedCollege;

public class EntranceFAQs extends Fragment implements ValueEventListener {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout error;
    FAQAdapter adapter;

    public EntranceFAQs() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyFaq);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar_faq);
        error = (LinearLayout) view.findViewById(R.id.error_faq);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        readyAdapter();

        readyOneTimeListener();
    }

    private void readyOneTimeListener() {
        FirebaseDatabase.getInstance().getReference().child("faqs").addListenerForSingleValueEvent(this);
    }

    private void readyAdapter() {
        adapter=new FAQAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrance_faqs, container, false);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        for (DataSnapshot child : dataSnapshot.getChildren())
            adapter.add(child.getValue(FAQ.class));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        progressBar.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }
}
