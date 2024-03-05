package recycler.ArbabPhoto.photorecoverynew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

import recycler.ArbabPhoto.photorecoverynew.R;
import recycler.ArbabPhoto.photorecoverynew.adapter.AlbumsAudioAdapter;
import recycler.ArbabPhoto.photorecoverynew.ads.AdmobAds_Model;

import static recycler.ArbabPhoto.photorecoverynew.utills.Utils.mAlbumAudios;

public class AudioAlbumActivity extends AppCompatActivity implements AlbumsAudioAdapter.OnClickItemListener {

    RecyclerView recyclerView;
    AlbumsAudioAdapter adapter;

    MaterialToolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        new AdmobAds_Model(this).bannerAds(this, findViewById(R.id.adsView));
        intView();
        intData();
    }

    public void intView() {

        recyclerView = (RecyclerView) findViewById(R.id.gv_folder);
        toolBar = findViewById(R.id.toolBar);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        toolBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    public void intData() {
        adapter = new AlbumsAudioAdapter(AudioAlbumActivity.this, mAlbumAudios, AudioAlbumActivity.this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getApplicationContext(), Audio_Activityy.class);
        intent.putExtra("value", position);
        startActivity(intent);
    }

}