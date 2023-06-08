package com.ck4u.spotify.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.ck4u.spotify.R;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchActivity extends AppCompatActivity {
    SearchView Search;
    ArrayList<Song> listSong;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    ArrayList<String> arrayListSongName = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Search =(SearchView) findViewById(R.id.edtsearch);
        listView = (ListView) findViewById(R.id.recycleview_search);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(SearchActivity.this, ListViewActivity.class);
                startActivity(intent);

            }
        });
        Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                arrayListSongName.add("Vu Tru Co Anh");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        ad();
    }

    private void searchDara(String s) {
        if(s.length() > 0)
            s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        listSong = new ArrayList<>();

        db.collection("songs").whereGreaterThanOrEqualTo("songName", s)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.err.println("Listen failed:" + e);
                            return;
                        }
                        listSong = new ArrayList<Song>();

                        for (DocumentSnapshot d : snapshots) {
                            Song c = d.toObject(Song.class);
                            Song s = new Song(c.getSongName(),c.getSongUrl(),c.getSubtitle());
                            listSong.add(s);
                            arrayListSongName.add(c.getSongName());
                            Log.d(TAG, "DocumentSnapshot data: " + d);
                        }
                    }
                });


    }

    private void ad()
    {
        arrayAdapter = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1,arrayListSongName){
            @NonNull
            @Override
            public View getView(int position, @androidx.annotation.Nullable View convertView, @NonNull ViewGroup parent) {
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