package com.example.wilder.liftsimulator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private boolean isLiftMoving = false;
    private int currentFloor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gotofloorzero = findViewById(R.id.fl_zero);
        gotofloorzero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(0);
            }
        });

        Button gotofloorone = findViewById(R.id.fl_one);
        gotofloorone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(1);

            }
        });


        Button gotofloortwo = findViewById(R.id.fl_two);
        gotofloortwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(2);

            }
        });

        Button gotofloortree = findViewById(R.id.fl_tree);
        gotofloortree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(3);

            }
        });

        Button gotofloorfour = findViewById(R.id.fl_four);
        gotofloorfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(4);

            }
        });

        Button gotofloorfive = findViewById(R.id.fl_five);
        gotofloorfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(5);

            }
        });

        Button gotofloorsix = findViewById(R.id.fl_six);
        gotofloorsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(6);
            }
        });


        Button gotofloorseven = findViewById(R.id.fl_seven);
        gotofloorseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(7);

            }
        });

        Button gotoflooreight = findViewById(R.id.fl_eight);
        gotoflooreight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(8);

            }
        });

        Button gotofloornine = findViewById(R.id.fl_nine);
        gotofloornine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(9);
            }
        });


    }

    private void goToFloor(int floor) {
        if (!isLiftMoving && floor != currentFloor) {
            moveNextFloor(floor);
            isLiftMoving = false;
        }
    }

    private void moveNextFloor(int floor) {
        if (floor != currentFloor) {
            isLiftMoving = true;
            Movelift lift = new Movelift();
            lift.execute(floor);

        }
    }


    class Movelift extends AsyncTask<Integer, Integer, Integer> {

        private static final String Tag = "MainActivity";

        @Override
        protected void onPreExecute() {
            //Setup precondition to execute some task
            Log.d(Tag, "onPreExecute: before task");
        }

        @Override
        protected Integer doInBackground(Integer... params) {


            //AWS network call
            Log.d(Tag, "doInBackground: AWS call");

            int floor = params[0];

            if (floor != currentFloor) {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                currentFloor = (floor > currentFloor) ? currentFloor + 1 : currentFloor - 1;


                //Do some task
                publishProgress(currentFloor);

            }
            return floor;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //Update the progress of current task
            TextView floorCount = (TextView) findViewById(R.id.floor_count);
            floorCount.setText(String.valueOf(currentFloor));
        }

        @Override
        protected void onPostExecute(Integer result) {
            //Show the result obtained from doInBackground
            Log.d(Tag, "onProgressUpdate: after task");

            isLiftMoving = false;

            moveNextFloor(result);


        }
    }
}





