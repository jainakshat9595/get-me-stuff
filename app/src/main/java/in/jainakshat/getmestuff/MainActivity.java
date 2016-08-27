package in.jainakshat.getmestuff;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.messaging.FirebaseMessaging;

import in.jainakshat.getmestuff.adapter.MainAdapter;
import in.jainakshat.getmestuff.database.FirebaseUtil;
import in.jainakshat.getmestuff.model.Item;

public class MainActivity extends AppCompatActivity {

    private boolean addFrameOpened = false;
    private FrameLayout mAddFrame;
    private FloatingActionButton mFab;

    private EditText mAddItemName;
    private EditText mAddItemQuant;
    private EditText mAddItemDesc;

    private RecyclerView mRVMain;
    private MainAdapter mRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseMessaging.getInstance().subscribeToTopic("notifs");

        mAddFrame = (FrameLayout) findViewById(R.id.add_frame);
        mAddFrame.setVisibility(View.GONE);

        mAddItemName = (EditText) findViewById(R.id.add_item_name);
        mAddItemQuant = (EditText) findViewById(R.id.add_item_quantity);
        mAddItemDesc = (EditText) findViewById(R.id.add_item_desc);

        mFab = (FloatingActionButton) findViewById(R.id.fab_add);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addFrameOpened) {
                    Item item = new Item();
                    item.setName(mAddItemName.getText().toString());
                    item.setQuant(mAddItemQuant.getText().toString());
                    item.setDesc(mAddItemDesc.getText().toString());
                    item.setTimestamp(System.currentTimeMillis());
                    FirebaseUtil.addItem(item);
                    closeAddFrame();
                    addFrameOpened = false;
                    clearAll();
                } else {
                    openAddFrame();
                    addFrameOpened = true;
                }
            }
        });

        mRVMain = (RecyclerView) findViewById(R.id.rv_main_list);
        mRVMain.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRVAdapter = new MainAdapter(getBaseContext());
        mRVMain.setAdapter(mRVAdapter);

        FirebaseUtil.getItemsref().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item item = Item.parse(dataSnapshot);
                mRVAdapter.addItem(item);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Item item = Item.parse(dataSnapshot);
                mRVAdapter.deleteItem(item);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    private void openAddFrame() {
        mAddFrame.setVisibility(View.VISIBLE);
        mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus_black_18dp));
    }

    private void closeAddFrame() {
        mAddFrame.setVisibility(View.GONE);
        mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus_white_18dp));
    }

    private void clearAll() {
        mAddItemName.setText("");
        mAddItemDesc.setText("");
        mAddItemQuant.setText("");
    }

    @Override
    public void onBackPressed() {
        if(addFrameOpened) {
            closeAddFrame();
            addFrameOpened = false;
        } else {
            super.onBackPressed();
        }
    }
}
