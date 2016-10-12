package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vmaletskiy.jokedisplay.JokeActivity;

public class MainActivity extends ActionBarActivity {
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        mProgressBar.setVisibility(View.VISIBLE);
        EndpointsAsyncTask task = new EndpointsAsyncTask(
                new OnAsyncTaskCompleted() {
                    @Override
                    public void onCompleted(String joke) {
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                        intent.putExtra(getString(R.string.joke_extra), joke);
                        mProgressBar.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }
        );
        task.execute();
    }

}
