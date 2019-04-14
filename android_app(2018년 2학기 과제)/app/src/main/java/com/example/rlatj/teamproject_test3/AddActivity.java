package com.example.rlatj.teamproject_test3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {
    EditText mPlace,mPeople,mLatitude,mLongtitude;
    Button mBtnAdd, mList;
    ImageView mImageView;

    final int REQUEST_CODE_GALLERY =999;

    public  static SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mPlace=findViewById(R.id.edtPlace);
        mPeople=findViewById(R.id.edtPeople);
        mLatitude=findViewById(R.id.edtLa);
        mLongtitude=findViewById(R.id.edtLong);

        mImageView=findViewById(R.id.imageView);//이미지

        mBtnAdd=findViewById(R.id.btnadd);
        mList=findViewById(R.id.btnList);

        //Create database
        mSQLiteHelper =new SQLiteHelper(this,"RECORDSDB.sqlite",null,1);

        //create "Table" in db
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORDS(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "place VARCHAR,people VARCHAR,latitude DOUBLE, longtitude DOUBLE,image BLOB)");


       ; //이거는 멀티미디어 사진 업로드 활용해야할듯? 크롭 안깔림;;;
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //외부저장소에서 가져오기
                ActivityCompat.requestPermissions(
                        AddActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        //클릭하면 sqlite로 데이터 저장
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mSQLiteHelper.insertData(
                            mPlace.getText().toString(),
                            mPeople.getText().toString(),
                            Double.parseDouble(mLatitude.getText().toString()),
                            Double.parseDouble(mLongtitude.getText().toString()),
                            imageViewToByte(mImageView)
                    );
                    Toast.makeText(AddActivity.this,"추가했습니다!",Toast.LENGTH_SHORT).show();

                    mPlace.setText("");
                    mPeople.setText("");
                    mLatitude.setText("");
                    mLongtitude.setText("");
                    mImageView.setImageResource(R.drawable.addphoto);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        //리스트 화면 보여주기 여기서는 MainActivity로 이동
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,MainActivity.class));
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap= ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode== REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this,"접근 권한이 없습니다",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK){
            Uri imageUri=data.getData();
            //여기는 잠시 비워두자 이미지 업로드
            try{
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                mImageView.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
