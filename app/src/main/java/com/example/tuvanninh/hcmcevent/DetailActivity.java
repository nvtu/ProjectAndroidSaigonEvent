package com.example.tuvanninh.hcmcevent;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class DetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    TextView descriptionView, nameView, phoneNoView, addressView, userName;
    ImageView imgView;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Info info;
    AccessToken accessToken;
    ProfilePictureView profilePictureView;
    Profile profile;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = LayoutInflater.from(this).inflate(R.layout.nav_header_detail, null);
        navigationView.addHeaderView(header);

        initComponent();
        getData();
        updateLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {


            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);
        }
    }

    private void updateLayout() {
        System.out.println(info.getPhoneNo());
        nameView.setText(info.getName());
        imgView.setImageResource(info.getBmpId());
        descriptionView.setText(info.getDescription());
        phoneNoView.setText(info.getPhoneNo());
        addressView.setText(info.getAddress());
    }

    private void getData() {
        Intent intent = getIntent();

        info = new Info(intent.getStringExtra("name"),
                intent.getStringExtra("description"),
                intent.getStringExtra("phoneNo"),
                intent.getStringExtra("address"),
                intent.getStringExtra("url"),
                intent.getIntExtra("imageId", 0)
        );

    }


    private void initComponent() {
        descriptionView = (TextView) findViewById(R.id.locationDescription);
        nameView = (TextView) findViewById(R.id.locationName);
        phoneNoView = (TextView) findViewById(R.id.locationPhoneNo);
        addressView = (TextView) findViewById(R.id.locationAddress);

        imgView = (ImageView) findViewById(R.id.locationImg);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        accessToken = AccessToken.getCurrentAccessToken();
        profile = Profile.getCurrentProfile();
        profilePictureView = (ProfilePictureView) header.findViewById(R.id.profile_image);
        userName = (TextView) header.findViewById(R.id.userName);

        if(profilePictureView != null) {
            profilePictureView.setProfileId(profile.getId());
        }
        if (userName != null){
            userName.setText(profile.getName());
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("Select profile Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /****
     * this method used for select image From Gallery
     *****/

    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap thumbnail;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        thumbnail = BitmapFactory.decodeFile(selectedImagePath, options);

        ShareDialog(thumbnail);
    }

    /***
     * this method used for take profile photo
     *******/

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        if (thumbnail != null) {
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        }

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {

            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        ShareDialog(thumbnail);

    }

    // This method is used to share Image on facebook timeline by SharePhotoContent model.
    public void ShareDialog(final Bitmap imagePath){

        //shareDialog = new ShareDialog(DetailActivity.this);

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imagePath)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(DetailActivity.this, content);
        //ShareApi.share(content, null);
        //setResult(Activity.RESULT_OK, getIntent());
        //finish();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.locationCall) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            if (info.getPhoneNo().isEmpty() == false) {
                intent.setData(Uri.parse("tel: " + info.getPhoneNo()));

                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return false;
                }
                startActivity(intent);
            }
        } else if (id == R.id.locationFindWeb) {
                if (info.getUrl().isEmpty() == true) {
                    Toast.makeText(DetailActivity.this, "No link to access.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.getUrl()));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(DetailActivity.this, "No application can handle this request. Please install a web browser", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
        } else if (id == R.id.locationFindMap) {
            GPSTracker gps = new GPSTracker(DetailActivity.this);
            String myLocation = gps.getLatitude() + "," + gps.getLongitude();
            Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
            intent.putExtra("originLocation", myLocation);
            intent.putExtra("destinationLocation", info.getAddress());
            startActivity(intent);
        } else if (id == R.id.share_btn) {
            selectImage();
        }
        else if (id == R.id.sendMessage){
            if (info.getPhoneNo().isEmpty() == false) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", info.getPhoneNo());
                startActivity(smsIntent);
            }
        }
        else if (id == R.id.logOutbtn){
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
