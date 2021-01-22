package com.example.ns;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.squareup.picasso.Picasso;

public class EnglishActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private DatabaseReference mDatabaseRef;
    private EditText mEditTextFileName;
    private Uri mImageUri;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private StorageReference mStorageRef;
    private TextView mTextViewShowUploads;
    private StorageTask mUploadTask;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        this.mButtonChooseImage = (Button) findViewById(R.id.button_choose_image);
        this.mButtonUpload = (Button) findViewById(R.id.button_upload);
        this.mTextViewShowUploads = (TextView) findViewById(R.id.text_view_show_uploads);
        this.mEditTextFileName = (EditText) findViewById(R.id.edit_text_file_name);
        this.mImageView = (ImageView) findViewById(R.id.image_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        String str = "uploads";
        this.mStorageRef = FirebaseStorage.getInstance().getReference(str);
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference(str);
        this.mButtonChooseImage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EnglishActivity.this.openFileChooser();
            }
        });
        this.mButtonUpload.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (EnglishActivity.this.mUploadTask == null || !EnglishActivity.this.mUploadTask.isInProgress()) {
                    EnglishActivity.this.uploadFile();
                } else {
                    Toast.makeText(EnglishActivity.this, "Upload in progress", 0).show();
                }
            }
        });
        this.mTextViewShowUploads.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EnglishActivity.this.openImagesActivity2();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(intent, 1);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null && data.getData() != null) {
            this.mImageUri = data.getData();
            Picasso.with(this).load(this.mImageUri).into(this.mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }

    private void uploadFile() {
        if (this.mImageUri != null) {
            StorageReference fileReference = this.mStorageRef;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(System.currentTimeMillis());
            stringBuilder.append(".");
            stringBuilder.append(getFileExtension(this.mImageUri));
            this.mUploadTask = fileReference.child(stringBuilder.toString()).putFile(this.mImageUri).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                public void onSuccess(TaskSnapshot taskSnapshot) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            EnglishActivity.this.mProgressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(EnglishActivity.this, "Upload successful", 1).show();
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) {
                    }
                    EnglishActivity.this.mDatabaseRef.child(EnglishActivity.this.mDatabaseRef.push().getKey()).setValue(new Upload(EnglishActivity.this.mEditTextFileName.getText().toString().trim(), ((Uri) urlTask.getResult()).toString()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception e) {
                    Toast.makeText(EnglishActivity.this, e.getMessage(), 0).show();
                }
            }).addOnProgressListener(new OnProgressListener<TaskSnapshot>() {
                public void onProgress(TaskSnapshot taskSnapshot) {
                    double progress = (double) taskSnapshot.getBytesTransferred();
                    Double.isNaN(progress);
                    progress *= 100.0d;
                    double totalByteCount = (double) taskSnapshot.getTotalByteCount();
                    Double.isNaN(totalByteCount);
                    EnglishActivity.this.mProgressBar.setProgress((int) (progress / totalByteCount));
                }
            });
            return;
        }
        Toast.makeText(this, "No file selected", 0).show();
    }

    private void openImagesActivity2() {
        startActivity(new Intent(this, ImagesActivity2.class));
    }
}
