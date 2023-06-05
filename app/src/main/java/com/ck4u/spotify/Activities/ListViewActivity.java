package com.ck4u.spotify.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ck4u.spotify.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;

    ArrayList<String> arrayListSongName = new ArrayList<>();
    ArrayList<String> arrayListUrl = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView =findViewById(R.id.recycleview_list);

        retrieveSongs();
    }

    private void retrieveSongs(){
        db.collection("songs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Song c = d.toObject(Song.class);
                        arrayListSongName.add(c.getSongName());
                        arrayListUrl.add(c.getSongUrl());
                    }

                    arrayAdapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,arrayListSongName){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position,convertView,parent);
                            TextView textView = (TextView)view.findViewById(android.R.id.text1);
                            textView.setSingleLine(true);
                            textView.setMaxLines(1);
                            return view;
                        }
                    };

                    listView.setAdapter(arrayAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(ListViewActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });



    }

}