package kejar.android.id.mylist2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText teams_edit_text;
    //Bundle bundle;
    Button random_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        random_button = (Button) findViewById(R.id.random_button);
        random_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bundle = new Bundle();
                teams_edit_text = (EditText)findViewById(R.id.teams_edit_text);
                final String[] teams =teams_edit_text.getText().toString().split("\n");
                //bundle.putStringArray("teams", teams);
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(promptView);

                final EditText jumlah_team = (EditText) promptView.findViewById(R.id.jumlah_team_edit_text);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("MainActivity", String.valueOf(teams.length));
                                //Toast.makeText(getBaseContext(),teams.length,Toast.LENGTH_SHORT).show();
                                //bundle = new Bundle();
                                //bundle.putInt("jumlah_team",Integer.valueOf(jumlah_team.getText().toString()));
                                Intent intent = new Intent(MainActivity.this, HasilAcakActivity.class);
                                intent.putExtra("jumlah_team",jumlah_team.getText().toString());
                                intent.putExtra("teams",teams);
                                //intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });


    }

}
