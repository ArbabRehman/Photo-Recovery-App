package recycler.ArbabPhoto.photorecoverynew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import recycler.ArbabPhoto.photorecoverynew.ads.AdmobAds_Model;
import recycler.ArbabPhoto.photorecoverynew.model.AudioModel;
import recycler.ArbabPhoto.photorecoverynew.R;
import recycler.ArbabPhoto.photorecoverynew.adapter.AudioAdapter;
import recycler.ArbabPhoto.photorecoverynew.task.RecoverAudiosAsyncTask;

import static recycler.ArbabPhoto.photorecoverynew.utills.Utils.mAlbumAudios;

public class Audio_Activityy extends AppCompatActivity {

    int int_position;
    RecyclerView recyclerView;
    TextView btnRestore, btnUnchecked;
    ArrayList<AudioModel> mList = new ArrayList<AudioModel>();
    Activity aaa;
    String status;

    AudioAdapter adapter;
    RecoverAudiosAsyncTask mRecoverPhotosAsyncTask;

    MaterialToolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio2);

        new AdmobAds_Model(this).bannerAds(this, findViewById(R.id.adsView));
        intView();
        intData();

    }

    public void intView() {

        btnRestore = (TextView) findViewById(R.id.btnRestore);
        btnUnchecked = (TextView) findViewById(R.id.btnUnchecked);
        toolBar = findViewById(R.id.toolBar);
        recyclerView = (RecyclerView) findViewById(R.id.gv_folder);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        toolBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    public void intData() {
        int_position = getIntent().getIntExtra("value", 0);
        if (mAlbumAudios != null && (mAlbumAudios.size() > int_position)) {
            mList.addAll((ArrayList<AudioModel>) mAlbumAudios.get(int_position).getListAudio().clone());
//            toolBar.setTitle(""+mAlbumAudios.get(int_position).getStrAudioFolder());
        }
        adapter = new AudioAdapter(this, mList, aaa);
        recyclerView.setAdapter(adapter);
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                status = "restore";
                gotonext();
            }
        });

        btnUnchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.setAllImagesUnseleted();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {
        status = "back";
        gotonext();
    }

    private void gotonext() {

        if (status.equals("restore")) {
            final ArrayList<AudioModel> tempList = adapter.getSelectedItem();


            if (tempList.size() == 0) {
                Toast.makeText(Audio_Activityy.this, "Cannot restore, all items are unchecked!", Toast.LENGTH_LONG).show();
            } else {

                mRecoverPhotosAsyncTask = new RecoverAudiosAsyncTask(Audio_Activityy.this, adapter.getSelectedItem(), new RecoverAudiosAsyncTask.OnRestoreListener() {
                    @Override
                    public void onComplete() {
                        Intent intent = new Intent(getApplicationContext(), RestoreResultActivity.class);
                        intent.putExtra("value", tempList.size());
                        startActivity(intent);
                        finish();
                        adapter.setAllImagesUnseleted();
                        adapter.notifyDataSetChanged();
                    }
                });
                mRecoverPhotosAsyncTask.execute();
            }
        } else if (status.equals("back")) {
            finish();
        }
    }
}