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

import androidx.core.math.MathUtils;

import com.microsoft.projectoxford.face.contract.Face;

import java.util.ArrayList;
import java.util.List;

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
        double a;
        int sum=0;
        int faces_detected= face.length;
        int male=0,female=0;

        List<Integer> Ages=new ArrayList<Integer>();
        List<String> Gender = new ArrayList<String>();
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
        for(int j=0;j<faces_detected;j++) {
            Ages.add((int) face[position].faceAttributes.age);
            Gender.add(face[position].faceAttributes.gender);
        }
        for(int i:Ages)
        {
            sum=sum+i;
        }
        Log.d("this is sum of ages", String.valueOf(sum));
        textFacialHair.setText(String.format("Facial Hair : %f %f %f",face[position].faceAttributes.facialHair.moustache, face[position].faceAttributes.facialHair.sideburns,face[position].faceAttributes.facialHair.beard));
        textHeadpose.setText(String.format("HeadPose : %f %f %f",face[position].faceAttributes.headPose.pitch, face[position].faceAttributes.headPose.roll,face[position].faceAttributes.headPose.yaw));
    Bitmap bitmap=ImageHelper.generateThumbnail(originalBitmap,face[position].faceRectangle);
    imageView.setImageBitmap(bitmap);
    return view;

    }
}
