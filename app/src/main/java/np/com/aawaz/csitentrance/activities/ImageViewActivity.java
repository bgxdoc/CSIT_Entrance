package np.com.aawaz.csitentrance.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import np.com.aawaz.csitentrance.R;
import np.com.aawaz.csitentrance.custom_views.TouchImageView;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchImageView imageView = (TouchImageView) findViewById(R.id.image_full_size);


        Picasso.with(this)
                .load(getIntent().getStringExtra("image_link"))
                .into(imageView);
    }
}
