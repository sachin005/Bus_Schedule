package s.chavan.busschedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private HashMap<Integer, String[]> busMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("bus", "bus");

        ListView listView = (ListView) findViewById(R.id.buslist);
        final List<Bus> busList = createBusList();

        Integer[] numbers = new Integer[busList.size()];
        for(int i = 0; i < busList.size(); i++) {
            numbers[i] = busList.get(i).getNumber();
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "clicked on " + id + " " + position, Toast.LENGTH_SHORT).show();

//                Intent busIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://www.lbtransit.com/schedules/Default.aspx?routegrp="
//                                + busList.get(position).getRoutegrp() + "&direction=Outbound&day=today&mode=schedule"));
//                startActivity(busIntent);

                createDialog(view, busList.get(position));


            }
        });
    }

    private void createDialog(View view, Bus bus) {
        final Bus bus1 = bus;
        final CharSequence[] ends = {bus.getInbound(), bus.getOutbound()};
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder//.setTitle("Select Direction")
        .setSingleChoiceItems(ends, -1,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        callIntent(bus1.getRoutegrp(), bus1.getOutbound());
                        Log.d("which", which + "");
                        callIntent(bus1.getRoutegrp(), which == 0 ? "inbound" : "outbound");
                        dialog.dismiss();
                    }

                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void callIntent(int routeGroup, String direction) {
        Log.d("direction", direction);
        Intent busIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.lbtransit.com/schedules/Default.aspx?routegrp="
                    + routeGroup + "&direction=" + direction + "&day=today&mode=schedule"));
        startActivity(busIntent);
    }

//    public void createBusMap() {
//        busMap = new HashMap<Integer, String[]>();
//
//        String[] oneTwoOne = {"From PCH @ Ximeno/CSULB - Westbound", "From Catalina Landing - Eastbound"};
//        busMap.put(121, oneTwoOne);
//
//        String[] oneSevenOne = {"From Technology Place - Eastbound", "From Seal Beach - Westbound"};
//        busMap.put(171, oneSevenOne);
//    }

    public List<Bus> createBusList() {
        Bus one = new Bus(1, 1, "From CSUDH - Southbound", "From Downtown - Northbound");
        Bus twoOneXTwo = new Bus(21, 20, "From Cherry/ Downey - Southbound", "From Downtown - Northbound");
        Bus passport = new Bus(30, 30, "From Queen Mary - Northbound", "From 10th St - Southbound");
        Bus fortyFive = new Bus(45, 40, "From PCH - Westbound", "From Downtown/ Santa Fe - Eastbound");
        Bus oneTwoOne = new Bus(121, 120,"From PCH @ Ximeno/CSULB - Westbound", "From Catalina Landing - Eastbound");
        Bus oneSevenOne = new Bus(171, 171, "From Technology Place - Eastbound", "From Seal Beach - Westbound");
        Bus oneSevenTwo = new Bus(172, 170, "From Downtown - Northbound", "From Norwalk Station - Southbound");
        Bus oneSevenThree = new Bus(173, 170, "From Downtown - Northbound", "From Norwalk Station - Southbound");
        Bus oneSevenFour = new Bus(174, 170, "From Downtown - Northbound", "From Norwalk Station - Southbound");
        Bus oneOneOne = new Bus(111, 110, "From South St/Lakewood Mall - Southbound", "From Downtown - Northbound");
        Bus oneOneTwo = new Bus(112, 110, "From South St/Lakewood Mall - Southbound", "From Downtown - Northbound");
        List<Bus> busList = new ArrayList<>();
        busList.add(one);
        busList.add(twoOneXTwo);
        busList.add(passport);
        busList.add(fortyFive);
        busList.add(oneOneOne);
        busList.add(oneOneTwo);
        busList.add(oneTwoOne);
        busList.add(oneSevenOne);
        busList.add(oneSevenTwo);
        busList.add(oneSevenThree);
        busList.add(oneSevenFour);
        return busList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
