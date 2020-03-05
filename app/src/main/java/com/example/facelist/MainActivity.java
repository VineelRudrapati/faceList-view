package com.example.facelist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Face;
import com.microsoft.projectoxford.face.rest.ClientException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    Button gallery;
    private  final int IMAGE=1;
    private FaceServiceClient faceServiceClient =new FaceServiceRestClient("https://centralus.api.cognitive.microsoft.com/face/v1.0/","eed8954eee514c35b238b55a45077282");
    private ProgressDialog detectionProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group);
        imageView = (ImageView) findViewById(R.id.img);
        imageView.setImageBitmap(bitmap);
        Button button = (Button) findViewById(R.id.button);
        gallery=(Button)findViewById(R.id.button2);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectAndFrame(bitmap);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select the picture"),IMAGE);
            }
        });
        detectionProgressDialog=new ProgressDialog(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri=data.getData();
            try
            {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
                detectAndFrame(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void detectAndFrame(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        AsyncTask<InputStream, String, Face[]> detecttask = new AsyncTask<InputStream, String, Face[]>() {
            @Override
            protected Face[] doInBackground(InputStream... params) {
                FaceServiceClient.FaceAttributeType[] faceattr = new FaceServiceClient.FaceAttributeType[]
                        {
                                FaceServiceClient.FaceAttributeType.HeadPose,
                                FaceServiceClient.FaceAttributeType.Age,
                                FaceServiceClient.FaceAttributeType.Gender,
                                FaceServiceClient.FaceAttributeType.Smile,
                                FaceServiceClient.FaceAttributeType.FacialHair,
                        };
                    try {
                       Face[] result = faceServiceClient.detect(
                                params[0],
                                true,
                                true,
                                faceattr);
                        if (result == null) {
                            publishProgress("Detection failed. Nothing is detected");
                            return null;
                        }
                        publishProgress(String.format("Detection Finished. %d face(s) Detected", result.length));
                        return result;

                    } catch (Exception e) {
                        publishProgress("Detection failed");
                        return null;
                    }
                }

            @Override
            protected void onPreExecute() {
                detectionProgressDialog.show();
            }
            @Override
            protected void onProgressUpdate(String... values) {

                detectionProgressDialog.setMessage(values[0]);
            }

            @Override
            protected void onPostExecute(Face[] faces) {
                detectionProgressDialog.dismiss();
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                Gson gson=new Gson();
                String data=gson.toJson(faces);
                intent.putExtra("list_faces",data);
                startActivity(intent);
            }
        };
        detecttask.execute(inputStream);
    }
}

