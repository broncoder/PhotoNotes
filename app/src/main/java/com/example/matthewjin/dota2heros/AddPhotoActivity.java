package com.example.matthewjin.dota2heros;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class AddPhotoActivity extends ActionBarActivity {
    private static final int REQUEST_CODE = 0;
	EditText editText;
	ImageView imageView;
	Button photo;
	Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activeity_addphoto);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.caption);
        photo = (Button) findViewById(R.id.photobutton);
        save = (Button) findViewById(R.id.savebutton);
        save.setVisibility(Button.INVISIBLE);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	            finish();
			}
		});
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_CODE);
    }
    // camera related
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            save.setVisibility(Button.VISIBLE);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), photo);
            // CALL THIS METHOD TO GET THE ACTUAL PATH
            Hero hero = new Hero(editText.getText().toString(), getRealPathFromURI(tempUri));
            // insert this hero to sqlite, shall we!!
            insert(hero);
        }  
    }
    // camera related
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(CompressFormat.JPEG, 100, bytes);
        String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null); 
        cursor.moveToFirst(); 
        int idx = cursor.getColumnIndex(Images.ImageColumns.DATA);
        return cursor.getString(idx); 
    }    
    
    // Sqlite related

    private void insert(Hero hero) {
        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(DBHelper.NAME_COLUMN, hero.getName());
        newValues.put(DBHelper.FILE_PATH_COLUMN, hero.getFileName());
        db.insert(DBHelper.DATABASE_TABLE, null, newValues);
    }    
}
