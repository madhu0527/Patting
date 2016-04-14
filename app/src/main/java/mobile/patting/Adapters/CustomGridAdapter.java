package mobile.patting.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.mobile.patting.R;

import mobile.patting.Utils.Constants;

/**
 * Created by goodworklabs on 22/03/2016.
 */
public  class CustomGridAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] imageId;
    LayoutInflater inflater = null;
    Constants utils=new Constants();
    public CustomGridAdapter(Activity mainActivity, String[] prgmNameList,
                         int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.home_grid_row, null);
        holder.tv = (TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
        holder.rowLay = (LinearLayout) rowView.findViewById(R.id.rowLay);
        holder.tv.setTypeface(utils.setfontRegular(context));
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        if (position == 0) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid1));
        } else if (position == 1) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid2));
        } else if (position == 2) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid3));
        } else if (position == 3) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid4));
        } else if (position == 4) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid5));
        } else if (position == 5) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid6));
        } else if (position == 6) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid7));
        } /*else if (position == 7) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid8));
        } else if (position == 8) {
            holder.rowLay.setBackgroundDrawable(context.getResources().getDrawable(R.color.home_grid9));
        }*/

        return rowView;
    }

    public class Holder {
        TextView tv;
        ImageView img;
        LinearLayout rowLay;
    }
}
