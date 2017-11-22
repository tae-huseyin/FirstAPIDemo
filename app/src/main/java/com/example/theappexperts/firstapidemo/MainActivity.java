package com.example.theappexperts.firstapidemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.theappexperts.firstapidemo.model.CakeModel;
import com.example.theappexperts.firstapidemo.services.RequestInterface;
import com.example.theappexperts.firstapidemo.services.ServerConnection;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.theappexperts.firstapidemo.services.ServerConnection.getServerConnection;

public class MainActivity extends AppCompatActivity {

    private RequestInterface requestInterface;
    private RecyclerView rvCakes;
    SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        rvCakes = (RecyclerView)findViewById(R.id.rvCakeLists);
        rvCakes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        loadData();

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                Log.i("CakeList", "WE DONE LOADING!!");
                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    void loadData()
    {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {
                        // do something with isConnectedToInternet value

                        if(isConnectedToInternet) {
                            Log.i("CakeList", "WE GOT INTERNET!!");
                            requestInterface = getServerConnection();
                            requestInterface.getCakesList()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(new Observer<List<CakeModel>>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(List<CakeModel> value) {
                                            for (CakeModel c : value) {
                                                Log.i("CakeList", c.getTitle());
                                            }
                                            rvCakes.setAdapter(new CakeAdapter(value, R.layout.cake_row, getApplicationContext()));
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }else {
                            Log.i("CakeList", "WE GOT NO INTERNET!!");
                        }
                    }
                });
    }

}
