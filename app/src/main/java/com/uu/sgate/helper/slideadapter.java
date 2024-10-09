package com.uu.sgate.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.uu.sgate.R;

public class slideadapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public slideadapter(Context context) {
        this.context = context;
    }

    int images[]={
            R.drawable.cusr,
            R.drawable.d_woman_sca,
    };
    int headings[]={
            R.string.say_to_reme,
            R.string.hassle_free,
    };
    int descriptions[]={
            R.string.complex_pas,
            R.string.scan_to_go_,

    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view=layoutInflater.inflate(R.layout.slides,container,false);

       //hooks
        ImageView imageView=view.findViewById(R.id.onboard2img);
        TextView heading=view.findViewById(R.id.hassle_free);
        TextView desc=view.findViewById(R.id.scan_to_go_);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);
        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((RelativeLayout)object);
    }
}
