package recycler.ArbabPhoto.photorecoverynew.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import recycler.ArbabPhoto.photorecoverynew.R;
import recycler.ArbabPhoto.photorecoverynew.utills.Config;

public class SettingActivity extends AppCompatActivity {

    LinearLayout  ratting, share;

    MaterialToolbar toolBar;

    TextView storagePath;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ratting = findViewById(R.id.rate_Us);
        share = findViewById(R.id.share_Us);
        toolBar = findViewById(R.id.toolBar);
        storagePath = findViewById(R.id.storage_Path);

        storagePath.setText("" + Config.RECOVER_DIRECTORY);

        toolBar.setNavigationOnClickListener(v -> {
            finish();
        });



        ratting.setOnClickListener(v -> {

            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market : //details?id" + getPackageName())));
            } catch (ActivityNotFoundException e) {

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=recycler.ArbabPhoto.photorecoverynew&hl=en_US=" + getPackageName())));


            }

        });

        share.setOnClickListener(v -> {


                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");


                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Photo Recovery");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=recycler.ArbabPhoto.photorecoverynew&hl=en_US=" + getApplicationContext().getPackageName());

                startActivity(Intent.createChooser(shareIntent, "Share Using"));


        });

    }
}