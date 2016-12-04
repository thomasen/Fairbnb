package uk.harkerone.fairbnb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FairbnbActivity extends AppCompatActivity {

    private int days = 0;
    private ArrayList<Peep> peeps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //dummy data
        peeps = new ArrayList<>();
        addNewPeep("Mr E.");
        addNewPeep("Mr E.");
        addNewPeep("Bart");
        addNewPeep("Humphrey");
        addNewPeep("Rapunzel");


        redrawTable();
    }

    private void redrawTable() {
        TableLayout tl = (TableLayout) findViewById(R.id.tblMain);
        tl.removeAllViews();
        TableRow hr = new TableRow(this);
        TextView tv = new TextView(this);
        hr.addView(tv);
        for (int c = 0; c < peeps.size(); c++) {
            TextView nameLabel = new TextView(this);
            /* rotation does not work well with sizing
            nameLabel.setRotation(-90);
            nameLabel.setText(peeps.get(c));
             */
            nameLabel.setText("P"+(c+1));
            hr.addView(nameLabel);
        }
        Button butNewPeep = new Button(this);
        butNewPeep.setText("+");
        butNewPeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPeep("P" + peeps.size()+1);
            }
        });
        butNewPeep.setMaxWidth(40);
        hr.addView(butNewPeep);
        tl.addView(hr);
        for (int r = 1; r < days; r++) {
            TableRow tr = new TableRow(this);
            TextView dayLabel = new TextView(this);
            dayLabel.setText("day " + r);
            tr.addView(dayLabel);
            for (int c = 0; c < peeps.size(); c++) {
                CheckBox cb = new CheckBox(this);
                cb.setChecked(true);
                tr.addView(cb);
            }
            tl.addView(tr);
        }
        TableRow tr = new TableRow(this);
        Button butNewDay = new Button(this);
        butNewDay .setText("+");
        butNewDay .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDay();
            }
        });
        butNewDay.setMaxWidth(40);
        tr.addView(butNewDay);
        tl.addView(tr);

    }

    public void addNewPeep(String name) {
        peeps.add(new Peep(name));
        redrawTable();
    }

    public void addNewDay() {
        days++;
        redrawTable();
    }

    public void calculateResult() {
    }

    private class Peep {
        String name;
        ArrayList<Boolean> data;

        public Peep(String name) {
            this.name = name;
            this.data = new ArrayList<>();
        }
    }
}
