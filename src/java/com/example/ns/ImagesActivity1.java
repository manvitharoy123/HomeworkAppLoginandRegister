package com.example.ns;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ImagesActivity1 extends AppCompatActivity {
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private ProgressBar mProgressCircle;
    private RecyclerView mRecyclerView;
    private List<Upload> mUploads;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images1);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle);
        this.mUploads = new ArrayList();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("uploads");
        this.mDatabaseRef = reference;
        reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ImagesActivity1.this.mUploads.add((Upload) postSnapshot.getValue(Upload.class));
                }
                ImagesActivity1 imagesActivity1 = ImagesActivity1.this;
                ImagesActivity1 imagesActivity12 = ImagesActivity1.this;
                imagesActivity1.mAdapter = new ImageAdapter(imagesActivity12, imagesActivity12.mUploads);
                ImagesActivity1.this.mRecyclerView.setAdapter(ImagesActivity1.this.mAdapter);
                ImagesActivity1.this.mProgressCircle.setVisibility(4);
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity1.this, databaseError.getMessage(), 0).show();
                ImagesActivity1.this.mProgressCircle.setVisibility(4);
            }
        });
    }
}
