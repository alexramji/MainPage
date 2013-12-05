package com.example.Main;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView status;
    LinearLayout LL;
    InputMethodManager imm;
    RelativeLayout profiletab;
    EditText display;
    TextView username;
    Boolean open=null;
    private List<Sidemenu> icons = new ArrayList<Sidemenu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();

            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            LL = (LinearLayout)findViewById(R.id.nofocus);

            username = (TextView)findViewById(R.id.username);

            display = (EditText)findViewById(R.id.display);


            profiletab = (RelativeLayout)findViewById(R.id.profiletab);
            profiletab.setVisibility(View.INVISIBLE);

            status = (TextView)findViewById(R.id.text);
            status.setText(Html.fromHtml("<i>" + status.getText() + "</i>"));
            open = false;

            populateSidemenuList();
            populateListView();
            registerClickCallback();
    }
}


    private void populateSidemenuList() {
        icons.add(new Sidemenu(R.drawable.chat));
        icons.add(new Sidemenu(R.drawable.contacts));
        icons.add(new Sidemenu(R.drawable.profileicon));
        icons.add(new Sidemenu(R.drawable.settings));
    }

    private void populateListView() {
        ArrayAdapter<Sidemenu> adapter = new MyListAdapter();
        ListView list = (ListView)findViewById(R.id.side_menu);
        list.setAdapter(adapter);
    }
    private void registerClickCallback() {
    ListView list = (ListView)findViewById(R.id.side_menu);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                if (position == 0) {
                    moveTaskToBack(true);
                }
                if (position == 1) {
                    moveTaskToBack(true);
                }
                if (position == 2) {
                    moveTaskToBack(true);
                }
                if (position == 3) {
                    moveTaskToBack(true);
                }
            }
        });
        
    }

        private class MyListAdapter extends ArrayAdapter<Sidemenu>{
            public MyListAdapter()
        {
            super(MainActivity.this, R.layout.item_view, icons);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
        }
            Sidemenu currentSidemenu = icons.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentSidemenu.getIcon());

         return itemView;
        }
    }

    public void Update(View view)
    {
        status = (TextView)findViewById(R.id.text);
        status.setText(Html.fromHtml("<i>" + status.getText() + "</i>"));
        LL.requestFocus();
        imm.hideSoftInputFromWindow(status.getWindowToken(), 0);
    }

    public void Profile(View view)
    {
        if(open == true)
        {
            profiletab.setVisibility(View.INVISIBLE);
            open = false;
            status = (TextView)findViewById(R.id.text);
            status.setText(Html.fromHtml("<i>" + status.getText() + "</i>"));
            LL.requestFocus();
            imm.hideSoftInputFromWindow(status.getWindowToken(), 0);
            username.setText(display.getText());
        }
        else
        {
        profiletab.setVisibility(View.VISIBLE);
        display.setText(username.getText());
            open = true;
        }

    }

//    public void Cancel(View view)
//    {
//        profiletab.setVisibility(View.INVISIBLE);
//        open = false;
//        LL.requestFocus();
//        imm.hideSoftInputFromWindow(status.getWindowToken(), 0);
//    }
//
//    public void Save(View view)
//    {
//        profiletab.setVisibility(View.INVISIBLE);
//        open = false;
//        status = (TextView)findViewById(R.id.text);
//        status.setText(Html.fromHtml("<i>" + status.getText() + "</i>"));
//        LL.requestFocus();
//        imm.hideSoftInputFromWindow(status.getWindowToken(), 0);
//        username.setText(display.getText());
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
