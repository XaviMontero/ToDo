package com.example.yeshasprabhakar.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeshasprabhakar.todo.R;

import com.example.yeshasprabhakar.todo.model.DatabaseHelper;
import com.example.yeshasprabhakar.todo.model.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Doctor> arrayList;

    public DoctorAdapter(Context context, ArrayList<Doctor> arrayList) {
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.row_item_doctor, null);
        TextView titleTextView = convertView.findViewById(R.id.doctorName);
        TextView dateTextView = convertView.findViewById(R.id.doctorEspe);
        TextView timeTextView = convertView.findViewById(R.id.doctorCedula);
        final ImageView delImageView = convertView.findViewById(R.id.deleteDoctor);
        delImageView.setTag(position);

        final View finalConvertView = convertView;
        //On delete icon click remove item from list and database
        delImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = (int) v.getTag();
                Animation animSlideRight = AnimationUtils.loadAnimation(context, R.anim.slide_out_right);
                animSlideRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Fires when animation starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // ...
                        deleteItem(pos);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // ...
                    }
                });
                finalConvertView.startAnimation(animSlideRight);
            }
        });
        Doctor dataModel = arrayList.get(position);
        titleTextView.setText(dataModel.nombre());
        dateTextView.setText(dataModel.especialidad());
        timeTextView.setText(dataModel.cedula());
        return convertView;
    }

    //Remove item from list and database
    public void deleteItem(int position) {
        deleteItemFromDb(arrayList.get(position).nombre(), arrayList.get(position).especialidad(), arrayList.get(position).cedula());
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    //Delete item from database
    public void deleteItemFromDb(String name, String date,String time) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.deleteData(name, date, time);
            toastMsg("Eliminado con exito ucacue!");
        } catch (Exception e) {
            e.printStackTrace();
            toastMsg("Trabajando juntos ucacue");
        }
    }

    //Create and call toast messages when necessary
    public void toastMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
