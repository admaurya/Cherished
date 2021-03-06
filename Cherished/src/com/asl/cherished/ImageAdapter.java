package com.asl.cherished;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	public int width, height;
	
	// Keep all Images in array
	public Integer[] mThumbIds = {
			R.drawable.p1, R.drawable.p2, R.drawable.p3,
			R.drawable.p4,R.drawable.p5, R.drawable.p6,
			R.drawable.p7, R.drawable.p8,
			R.drawable.p9, R.drawable.p10,
			R.drawable.p11, R.drawable.p12,
			R.drawable.p13, R.drawable.p14,
			R.drawable.p15,R.drawable.p16,
			R.drawable.p17,R.drawable.p18,
			R.drawable.p19,R.drawable.p20,
			
			R.drawable.l1,R.drawable.l2,
			R.drawable.l3,R.drawable.l4,
			R.drawable.l5,R.drawable.l6,
			R.drawable.l7,R.drawable.l8,
			R.drawable.l9,R.drawable.l10,
			
			R.drawable.l11,R.drawable.l12,
			R.drawable.l13,R.drawable.l14,
			R.drawable.l15,R.drawable.l16,
			R.drawable.l17,R.drawable.l18,
			R.drawable.l19,R.drawable.l20,
			
			R.drawable.l21,R.drawable.l22,
			R.drawable.l23,R.drawable.l24,
			R.drawable.l25,R.drawable.l26,
			R.drawable.l27,R.drawable.l28,
			R.drawable.l29,R.drawable.l30,
			
			R.drawable.l31,R.drawable.l32,
			R.drawable.l33,R.drawable.l34,
			R.drawable.l35,R.drawable.l36,
			R.drawable.l37,R.drawable.l38,
			R.drawable.l39,R.drawable.l40,
			
			R.drawable.l41,R.drawable.l42,
			R.drawable.l43,R.drawable.l44,
			R.drawable.l45,R.drawable.l46,
			R.drawable.l47,R.drawable.l48,
			R.drawable.l49,R.drawable.l50,
			
			R.drawable.l51,R.drawable.l52,
			R.drawable.l53,R.drawable.l54,
			R.drawable.l55,R.drawable.l56,
			R.drawable.l57,R.drawable.l58,
			R.drawable.l59,R.drawable.l60
			
	};
	
	
	public ImageAdapter(Context c){
		mContext = c;
	}
	// Constructor
	public ImageAdapter(Context c, int width, int height){
		mContext = c;
		this.width=width;
		this.height=height;
	}


	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {			
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new GridView.LayoutParams(this.width, this.height));
        return imageView;
	}

}
