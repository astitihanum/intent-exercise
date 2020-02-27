package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.polinema.intentexercise.model.User;

public class ProfileActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1 ;
    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutyouText;
    private User user;

    private ImageView avatarImage;
    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnameText = findViewById(R.id.text_fullname);
        emailText = findViewById(R.id.text_email);
        homepageText = findViewById(R.id.text_homepage);
        aboutyouText = findViewById(R.id.text_about);
        // TODO: bind here

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // TODO: display value here
            user = extras.getParcelable(RegisterActivity.USER_KEY);

            fullnameText.setText(user.getFullname());
            emailText.setText(user.getEmail());
            homepageText.setText(user.getHomepage());
            aboutyouText.setText(user.getAboutyou());
            avatarImage.setImageBitmap(user.getProfile());
            Bitmap bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("yourImage"), 0, getIntent().getByteArrayExtra("yourImage").length);
            avatarImage.setImageBitmap(bmp);
        }
    }

    public void handleHomepage(View view) {
        String homepage = homepageText.getText().toString();


        if (!homepage.startsWith("https://") && !homepage.startsWith("http://")){
            homepage = "http://" + homepage;
        }
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(homepage));
        if (openUrlIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openUrlIntent);
        }
    }
}
