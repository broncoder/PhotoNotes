package com.example.matthewjin.dota2heros;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activeity_detail);

        Hero hero = getIntent().getExtras().getParcelable("myHero");
        String name = hero.getName();
        String path = hero.getFileName();
        
        TextView textView = (TextView) findViewById(R.id.rowText);
        textView.setText(name);

        ImageView imageView = (ImageView) findViewById(R.id.rowImage);
        // ERROR is right here-->
        File imgFile = new File(path);
        if(imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
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
        if (id == R.id.uninstall) {
        	Uri packageURI = Uri.parse("package:com.example.dota2heros");
        	Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        	startActivity(uninstallIntent);
        }
        else if(id == R.id.addphoto){
        	final Intent intent = new Intent(DetailActivity.this, AddPhotoActivity.class);
        	startActivity(intent);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}