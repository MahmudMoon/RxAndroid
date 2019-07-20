package com.example.moon.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    EditText et_1,et_2;
    private static final String TAG = "MainActivity";
    Button btn;
    static List<Task> allTasks;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_1 = findViewById(R.id.editText);
        et_2 = findViewById(R.id.editText2);
        btn = (Button)findViewById(R.id.button);
        compositeDisposable = new CompositeDisposable();
        allTasks = GetData.GetAllData();
        Observable<Task> observable = Observable.fromIterable(allTasks)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());

       Observer<Task> observer = new Observer<Task>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.i(TAG, "onSubscribed");
               compositeDisposable.add(d);
           }

           @Override
           public void onNext(Task task) {
               Log.i(TAG, "onNext: "+Thread.currentThread().getName());
               Log.i(TAG, "onNext: "+task.getTaskName());
           }

           @Override
           public void onError(Throwable e) {
               Log.i(TAG, "onError: "+e.toString());
           }

           @Override
           public void onComplete() {
               Log.i(TAG, "onCompleted");
           }
       };


       observable.subscribe(observer);

        et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.i(TAG, "beforeTextChanged: "+s +"Start " + start + "Count "+count + "After "+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.i(TAG, "onTextChanged: "+s +"Start " + start + "Count "+count + "before "+before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: "+s);

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTasks.add(new Task(1,"K","kkkk"));
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
