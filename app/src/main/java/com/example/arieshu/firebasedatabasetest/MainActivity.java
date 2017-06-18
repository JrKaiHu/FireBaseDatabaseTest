package com.example.arieshu.firebasedatabasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private ArrayList<Profile> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        DatabaseReference reference_contacts = FirebaseDatabase.getInstance().getReference("contacts");
        reference_contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final long nChildCnt = dataSnapshot.getChildrenCount();
                adapter.clear();
                for ( DataSnapshot ds : dataSnapshot.getChildren() ) {

                    Profile profile = ds.getValue(Profile.class);
                    arrayList.add(ds.getValue(Profile.class));

                    adapter.add(profile.getName());
                    adapter.add(profile.getAddr());
                    adapter.add(profile.getPhone());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.child("1").child("addr").setValue("上海");

        String profileId = reference_contacts.push().getKey();

        // creating Profile object
        //Profile profile = new Profile("台中", "Ariel", "246801357");
        Profile profile = new Profile();
        profile.setAddr("台中");
        profile.setName("Phoebe");
        profile.setPhone("246801357");

        // pushing profile to 'contacts' node using the userId
        reference_contacts.child("3").setValue(profile);
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.list);
//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        listView.setAdapter(adapter);
    }
}
