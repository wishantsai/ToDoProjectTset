package com.example.csie.todoproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ToDoActivity extends ActionBarActivity {
    private ListView lvItems;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        lvItems = (ListView)findViewById(R.id.lvItems);

        items = new ArrayList<String>();
        items.add("Item 1");
        items.add("Item 2");
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);
        lvItems.setOnItemLongClickListener(deleteItem);

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(addItem);

    }
    private AdapterView.OnItemLongClickListener deleteItem =
            new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder dialog = new
                    AlertDialog.Builder(ToDoActivity.this);
                    final int pos = position;
                    dialog.setCancelable(false);
                    dialog.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            items.remove(pos);
                            itemsAdapter.notifyDataSetChanged();
                        }
                    });
                    dialog.setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                        }
                    });

                    dialog.setTitle("Are you sure?");
                    dialog.setMessage("Are you sure you want to delete this event?");

                    dialog.show();


                    return true;
                }
            };
    private View.OnClickListener addItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
            itemsAdapter.add(etNewItem.getText().toString());
            etNewItem.setText("");
            etNewItem.setHint("Input a new event.");
        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
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
}
