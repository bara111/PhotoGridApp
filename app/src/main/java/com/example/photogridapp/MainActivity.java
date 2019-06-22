package com.example.photogridapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.photogridapp.CatApi.CatAPI;
import com.example.photogridapp.CatApi.CatResponse;

import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8, imageButton9, CatImageButton;

    Context context = this;
    private int id;
    private AlertDialog alertDialog;
    private static final int REQUEST_CODE = 1;
    private static final int TAKE_PHOTO = 101;
    private Bitmap bitmap;
    private Dialog dialog;
    private Intent intent;
    private Button button, camButton, GalleryButton;
    private Intent Intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();

        imageButton1 = findViewById(R.id.image1);
        imageButton2 = findViewById(R.id.image2);
        imageButton3 = findViewById(R.id.image3);
        imageButton4 = findViewById(R.id.image4);
        imageButton5 = findViewById(R.id.image5);
        imageButton6 = findViewById(R.id.image6);
        imageButton7 = findViewById(R.id.image7);
        imageButton8 = findViewById(R.id.image8);
        imageButton9 = findViewById(R.id.image9);
        CatImageButton = findViewById(R.id.imageCat);
        Intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.custom, null);
        camButton = view1.findViewById(R.id.Camera);
        GalleryButton = view1.findViewById(R.id.Gallery);
        builder.setView(view1);
        alertDialog = builder.create();
        button = findViewById(R.id.captureFront);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(Intent3, TAKE_PHOTO);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.show();
                //
//                intent.putExtra("imageView", R.id.image1);
                id = R.id.image1;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

                //                intent.putExtra("imageView", R.id.image2);
                id = R.id.image2;
//                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image3);
                id = R.id.image3;
//
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image4);
                id = R.id.image4;
//
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image5);
                id = R.id.image5;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image6);
                id = R.id.image6;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image7);
                id = R.id.image7;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

//                intent.putExtra("imageView", R.id.image8);
                id = R.id.image8;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();


                id = R.id.image9;
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        CatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GETIMAGEURL();
            }
        });
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(Intent3,TAKE_PHOTO);

            }
        });
        GalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


    }


    public void GETIMAGEURL() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CatAPI service = retrofit.create(CatAPI.class);
        service.get().enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                String URL = response.body().getUrl();
                Log.d("bara", URL);
                Glide.with(getApplicationContext()).load(URL).into(CatImageButton);

            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                Log.d("bara", "bara");

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }

                ImageButton imageButton = findViewById(id);
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                        bitmap, 250, 250, false);
                imageButton.setImageBitmap(resizedBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        else if (requestCode == TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            ImageButton imageButtonCAM = findViewById(id);
            Bundle bundle = data.getExtras();
            Bitmap bitmapCamera = (Bitmap) bundle.get("data");
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                    bitmapCamera, 250, 250, false);
            imageButtonCAM.setImageBitmap(resizedBitmap);

        }

    }


    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


    @Override
    public void applyTexts(String username, String password) {

    }
}
