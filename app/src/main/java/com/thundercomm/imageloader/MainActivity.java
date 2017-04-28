package com.thundercomm.imageloader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thundercomm.libimageloader.DoubleCache;
import com.thundercomm.libimageloader.ImageLoader;
import com.thundercomm.libimageloader.views.CircleImageView;


public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "Main";
    private Button execute;
    private Button cancel;
    private ProgressBar progressBar;
    private TextView textView;
    private CircleImageView imageView;
    private ImageView image;
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG,"onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    private MyTask mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.execute:
                mTask = new MyTask();
                mTask.execute("http://www.baidu.com");
                execute.setEnabled(false);
                cancel.setEnabled(true);
                break;
            case R.id.cancel:
                mTask.cancel(true);
                break;
        }
    }
    private void  initViews()
    {
        execute = (Button)findViewById(R.id.execute);
        cancel = (Button)findViewById(R.id.cancel);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        textView = (TextView)findViewById(R.id.text_view);
        imageView = (CircleImageView)findViewById(R.id.circle_img);
        image = (ImageView)findViewById(R.id.image);
        ImageLoader.GetInstance().setImageCache(new DoubleCache());
        ImageLoader.GetInstance().displayImage("http://i.guancha.cn/news/2017/04/04/20170404173137184.jpg",image);
        ImageLoader.GetInstance().displayImage("http://i.guancha.cn/news/2017/04/04/20170404173137184.jpg",imageView);

    }

    private class MyTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
