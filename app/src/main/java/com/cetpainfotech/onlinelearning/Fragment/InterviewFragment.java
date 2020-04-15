package com.cetpainfotech.onlinelearning.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cetpainfotech.onlinelearning.Adapter.InterviewAdapter;
import com.cetpainfotech.onlinelearning.Adapter.NotesAdapter;
import com.cetpainfotech.onlinelearning.Model.NotesJson;
import com.cetpainfotech.onlinelearning.R;


public class InterviewFragment extends Fragment {
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    String url="http://searchkero.com/sakshi/interview.json";
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_android, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        progressBar=view.findViewById(R.id.progressBar);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayout);

        if (isConnection()){
            noteload();
        }
        else{
            showConnectionErrorDialog();
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressBar.setVisibility(View.GONE);
                noteload();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void showConnectionErrorDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("No Internet !!");
        builder.setMessage("Seem like not connected to the network.. Please turn it on and try again later");
        builder.setCancelable(false);
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isConnection()) {
                    noteload();
                }
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void noteload() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                NotesJson notesJson=new NotesJson(response);
                notesJson.notejson();

                InterviewAdapter adapter=new InterviewAdapter(getActivity(),NotesJson.name,NotesJson.url);
                recyclerView.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private boolean isConnection() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info!=null && info.isConnectedOrConnecting()){
            return true;
        }
        else {
            return false;
        }
    }
}