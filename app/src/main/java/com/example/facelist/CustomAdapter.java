package com.example.facelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.projectoxford.face.contract.Face;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends BaseAdapter {
    private  Face[] face;
    private Context context;
    private LayoutInflater inflater;
    private Bitmap originalBitmap;
    public CustomAdapter(Face[] face, Context context, Bitmap originalBitmap) {
        this.face = face;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.originalBitmap = originalBitmap;
    }

    @Override
    public int getCount() {
        return face.length;
    }
    @Override
    public Object getItem(int position) {
        return face[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        int faces_detected=face.length;
        List<Integer> Ages=new ArrayList<Integer>();
        List<String> Gender=new ArrayList<String>();
        double a;
        int sum=0;
        int facesd= face.length;
        int male=0,female=0;


        Log.d("these are age values",Ages.toString());
        //Log.d("male are :",String.valueOf(male));
        //Log.d("female are :",String.valueOf(female));
        Log.d("faces detected",String.valueOf(facesd));
        if(convertView==null)
            view=inflater.inflate(R.layout.facelistview,null);
        TextView textAge,textGen,textFacialHair,textHeadpose,textSmile;
        ImageView imageView;
        textAge=(TextView)view.findViewById(R.id.textAge);
        textGen=(TextView)view.findViewById(R.id.textGen);
        textFacialHair=(TextView)view.findViewById(R.id.textFacialHair);
        textHeadpose=(TextView)view.findViewById(R.id.textHeadpose);
        textSmile=(TextView)view.findViewById(R.id.textSmile);
        imageView=(ImageView)view.findViewById(R.id.img1);
        textSmile.setText("Smile :"+face[position].faceAttributes.smile);
        textAge.setText("Age :"+face[position].faceAttributes.age);
        textGen.setText("Gender :"+face[position].faceAttributes.gender);
        for(int j=0;j<faces_detected-1;j++) {
            Ages.add((int) face[position].faceAttributes.age);
        }
        for(int i:Ages)
        {
            sum=sum+i;
        }
        Log.d("sum of ages",String.valueOf(sum));
        textFacialHair.setText(String.format("Facial Hair : %f %f %f",face[position].faceAttributes.facialHair.moustache, face[position].faceAttributes.facialHair.sideburns,face[position].faceAttributes.facialHair.beard));
        textHeadpose.setText(String.format("HeadPose : %f %f %f",face[position].faceAttributes.headPose.pitch, face[position].faceAttributes.headPose.roll,face[position].faceAttributes.headPose.yaw));
        Bitmap bitmap=ImageHelper.generateThumbnail(originalBitmap,face[position].faceRectangle);
        imageView.setImageBitmap(bitmap);
        return view;
    }
}
