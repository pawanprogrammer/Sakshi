package com.cetpainfotech.onlinelearning.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cetpainfotech.onlinelearning.Adapter.AndroidAdapter;
import com.cetpainfotech.onlinelearning.Model.ParseJSON;
import com.cetpainfotech.onlinelearning.R;


public class AndroidFragment extends Fragment {
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    String Url = "http://searchkero.com/onlinelearn/android_fetch.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_android, container, false);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);

        if (isConnection()) {
            load();
        } else {
            showConnectionErrorDialog();
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()

        {
            @Override
            public void onRefresh() {
                // Refresh items
                progressBar.setVisibility(View.GONE);
                load();
                swipeRefreshLayout.setRefreshing(false);
            }


        });
        return v;
    }

    private void load() {
        progressBar.setVisibility(View.VISIBLE);
        //pd.setMessage("Please Wait.." +
        //      "Your net is slow...");
        // pd.setCancelable(false);

        StringRequest request = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ParseJSON parse = new ParseJSON(response);
                parse.parseJSON();

                AndroidAdapter adapter = new AndroidAdapter(getActivity(), ParseJSON.ids, ParseJSON.names);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


    public void showConnectionErrorDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("No Internet !!");
        builder.setMessage("Seem like not connected to the network.. Please turn it on and try again later");
        builder.setCancelable(false);
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*dialog.dismiss();*/

                if (isConnection())
                {
                    load();
                }
                // progressBar.setVisibility(View.GONE);
                // getActivity().finish();
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected boolean isConnection() {
        ConnectivityManager manage = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manage.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}


