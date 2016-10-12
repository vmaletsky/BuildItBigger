package com.example.vmaletskiy.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        String joke = getIntent().getStringExtra(getString(R.string.joke_extra));
        TextView jokeView = (TextView) findViewById(R.id.joke_text);
        jokeView.setText(joke);
    }
}
