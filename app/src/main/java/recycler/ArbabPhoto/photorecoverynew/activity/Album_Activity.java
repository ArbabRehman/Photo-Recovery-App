package recycler.ArbabPhoto.photorecoverynew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import recycler.ArbabPhoto.photorecoverynew.ads.AdmobAds_Model;
import recycler.ArbabPhoto.photorecoverynew.model.AlbumPhoto;
import recycler.ArbabPhoto.photorecoverynew.model.PhotoModel;
import recycler.ArbabPhoto.photorecoverynew.R;
import recycler.ArbabPhoto.photorecoverynew.adapter.AlbumsImageAdapter;

import static recycler.ArbabPhoto.photorecoverynew.utills.Utils.mAlbumPhotos;
import static recycler.ArbabPhoto.photorecoverynew.utills.Utils.mHiddenFiles;

public class Album_Activity extends AppCompatActivity implements AlbumsImageAdapter.OnClickItemListener {

    RecyclerView recyclerView;
    AlbumsImageAdapter adapter;

    MaterialToolbar toolBar;

    static boolean allFiles = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        intView();
        setSupportActionBar(toolBar);
        intData(mAlbumPhotos);
        new AdmobAds_Model(this).bannerAds(this, findViewById(R.id.adsView));
    }

    public void intView() {

        recyclerView = (RecyclerView) findViewById(R.id.gv_folder);
        toolBar = findViewById(R.id.toolBar);
        toolBar.inflateMenu(R.menu.album_menu);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public void intData(ArrayList<AlbumPhoto> albumPhotos) {

        adapter = new AlbumsImageAdapter(Album_Activity.this, albumPhotos, Album_Activity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getApplicationContext(), PhotosActivity.class);
        intent.putExtra("value", position);
        intent.putExtra("all_files", allFiles);
        startActivity(intent);
    }

    private ArrayList<AlbumPhoto> getFilter(ArrayList<AlbumPhoto> albumPhotos) {

        mHiddenFiles = new ArrayList<>();
        if (albumPhotos.size() != 0) {
            for (int i = 0; i < albumPhotos.size(); i++) {

                String temp = albumPhotos.get(i).getStrFolder();

                if (new File(temp).isHidden()) {
                    AlbumPhoto albumPhoto = new AlbumPhoto();
                    albumPhoto.setStrFolder(temp);
                    albumPhoto.setLastModified(new File(temp).lastModified());
                    Collections.sort(albumPhotos.get(i).getListPhoto(), (lhs, rhs) -> Long.valueOf(rhs.getLastModified()).compareTo(lhs.getLastModified()));
                    albumPhoto.setListPhoto((ArrayList<PhotoModel>) albumPhotos.get(i).getListPhoto().clone());

                    mHiddenFiles.add(albumPhoto);
                }
            }
            return mHiddenFiles;
        } else {
            return mAlbumPhotos;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.album_menu, menu);
        Log.d("debug", "activity : onCreateOptionsMenu");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allFiles:
                allFiles = true;
                intData(mAlbumPhotos);
                break;
            case R.id.hiddenFiles:
                allFiles = false;
                Log.e("tag", "all_files-->4: " + allFiles);
                intData(getFilter(mAlbumPhotos));
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                //Do Something.
                break;
        }
        return true;
    }


}