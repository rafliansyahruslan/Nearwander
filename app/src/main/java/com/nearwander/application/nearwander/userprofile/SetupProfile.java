package com.nearwander.application.nearwander.userprofile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nearwander.application.nearwander.R;
import com.nearwander.application.nearwander.tabbed.HomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupProfile extends AppCompatActivity {

    private EditText input;

    private EditText input_name, input_id, input_email, input_phone, input_status, input_sex;
    private Button check;
    private ImageView mImageView;
    private CircleImageView circleImageView;
    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    private StorageReference mDatabaseUsers;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    /*private MenuView.ItemView itemViewCamera, itemViewGalery;
    private MenuItem menuItemCamera, menuItemGalery;*/

    private static final int GALLERY_INTENT = 2;
    private static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        Drawable drwA = getDrawable(R.drawable.ic_arrow_back_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) drwA).getBitmap();
        Drawable newDrw = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        toolbar.setNavigationIcon(newDrw);
        toolbar.setTitleTextColor(R.color.white);
        toolbar.setTitle("Edit Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){

            input_name = (EditText) findViewById(R.id.user_profile_name);
            input_id = (EditText) findViewById(R.id.your_id);
            input_email = (EditText) findViewById(R.id.email);
            input_phone = (EditText) findViewById(R.id.phone);
            input_status = (EditText) findViewById(R.id.status);
            input_sex = (EditText) findViewById(R.id.test);
            check = (Button) findViewById(R.id.button2);

            getmDatabaseReference = FirebaseDatabase.getInstance().getReference("tb_users");
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put( "email", input_email);
            childUpdates.put( "id_user", input_id);
            childUpdates.put( "name", input_name);
            childUpdates.put( "phone_number", input_phone);
            childUpdates.put( "sex", input_sex);
            childUpdates.put( "status", input_status);

            getmDatabaseReference.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {

                    }
                }
            });

        }*/


        mDatabaseReference = FirebaseDatabase.getInstance().getReference("tb_users");
        input_name = (EditText) findViewById(R.id.user_profile_name);
        input_id = (EditText) findViewById(R.id.your_id);
        input_email = (EditText) findViewById(R.id.email);
        input_phone = (EditText) findViewById(R.id.phone);
        input_status = (EditText) findViewById(R.id.status);
        input_sex = (EditText) findViewById(R.id.test);
        check = (Button) findViewById(R.id.button2);
        /*itemViewCamera = (MenuView.ItemView) findViewById(R.id.take_from_camera);
        itemViewGalery = (MenuView.ItemView) findViewById(R.id.choose_from_galery);

        menuItemCamera = (MenuItem) findViewById(R.id.take_from_camera);
        menuItemGalery = (MenuItem) findViewById(R.id.choose_from_galery);*/

        /*mFirebaseDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nearwander-ab9d5.firebaseio.com/tb_users");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                input_name.setText(value);
                input_id.setText(value);
                input_email.setText(value);
                input_phone.setText(value);
                input_status.setText(value);
                input_sex.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseStorage.getInstance().getReference().child("Users");
        mProgressDialog = new ProgressDialog(this);

        mImageView = (ImageView) findViewById(R.id.user_profile_photo);
        circleImageView = (CircleImageView) findViewById(R.id.profile_image);

        mStorage = FirebaseStorage.getInstance().getReference();

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(SetupProfile.this, mImageView);
                popupMenu.getMenuInflater().inflate(R.menu.popup_set_profile, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.choose_from_galery:
                                Intent i = new Intent(Intent.ACTION_PICK);
                                i.setType("image/*");

                                startActivityForResult(i, GALLERY_INTENT);
                                return true;

                            case R.id.take_from_camera:
                                Intent a = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(a, CAMERA_REQUEST_CODE);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startSetupAccount();

                String name = input_name.getText().toString();
                String id = mAuth.getCurrentUser().getUid();
                String email = mAuth.getCurrentUser().getEmail();
                String phone = input_phone.getText().toString();
                String status = input_status.getText().toString();
                String sex = input_sex.getText().toString();
                Users_model um = new Users_model(email, id, name, phone, sex, status);
                addUser(um);

//                if(input.getText().toString().equals("")){
//                if(addUser(um)){
//                    Toast.makeText(SetupProfile.this, "Please Insert", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(SetupProfile.this, HomePageActivity.class);
//                    startActivity(i);
//                }else {
//                    Toast.makeText(SetupProfile.this,"SAVED!!",Toast.LENGTH_SHORT).show();
//                }
            }

            private void startSetupAccount() {
                String name = input_name.getText().toString().trim();
                String user_id = mAuth.getCurrentUser().getUid();
                if (!TextUtils.isEmpty(name)) {
                    /*
                    mDatabaseUsers.child(user_id).child("Name").setValue(name);
                    mDatabaseUsers.child(user_id).child("Id").setValue();
                    */
                }
            }
        });

    }

    public void addUser(Users_model model) {
//        boolean is = false;
        mDatabaseReference.child(model.getId_user()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SetupProfile.this, "SAVED!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SetupProfile.this, HomePageActivity.class);
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SetupProfile.this,"Please Insert",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            mProgressDialog.setMessage("Uploading Image ...");
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(SetupProfile.this).load(downloadUri).fit().centerCrop().noFade().into(circleImageView);

                    mProgressDialog.dismiss();

                    Toast.makeText(SetupProfile.this, "Upload Done.", Toast.LENGTH_LONG).show();

                }
            });
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            mProgressDialog.setMessage("Uploading Image ...");
            mProgressDialog.show();

            Uri uriCam = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uriCam.getLastPathSegment());

            filepath.putFile(uriCam).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressDialog.dismiss();
                    Toast.makeText(SetupProfile.this, "Upload Done.", Toast.LENGTH_LONG).show();
                }
            });

        }

    }

}