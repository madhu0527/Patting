package mobile.patting.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.mobile.patting.R;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import mobile.patting.Design.CirclePageIndicator;

/**
 * Created by goodworklabs on 29/03/2016.
 */
public class TourActivity extends AppCompatActivity {
    TextView nextTxt,homeTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour);
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        TourAdapter adapter=new TourAdapter(this);
        mViewPager.setAdapter(adapter);
        final CirclePageIndicator mCircularIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        nextTxt=(TextView)findViewById(R.id.nextTxt);
        homeTxt=(TextView)findViewById(R.id.sign_up);
        mCircularIndicator.setFillColor(Color.parseColor("#70c1b3"));
        mCircularIndicator.setPageColor(Color.parseColor("#999999"));
        mCircularIndicator.setViewPager(mViewPager);
        nextTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1, true);
                nextTxt.setVisibility(View.GONE);
                homeTxt.setVisibility(View.VISIBLE);
            }
        });
        homeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(TourActivity.this,MainActivity.class);
                startActivity(home);
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
                if(position==0){
                    nextTxt.setVisibility(View.VISIBLE);
                    homeTxt.setVisibility(View.GONE);
                    mCircularIndicator.setFillColor(Color.parseColor("#70c1b3"));
                    mCircularIndicator.setPageColor(Color.parseColor("#999999"));
                }else{
                    nextTxt.setVisibility(View.GONE);
                    homeTxt.setVisibility(View.VISIBLE);
                    mCircularIndicator.setFillColor(Color.parseColor("#999999"));
                    mCircularIndicator.setPageColor(Color.parseColor("#70c1b3"));
                }
            }
        });
    }
    public class TourAdapter extends PagerAdapter {
        ViewGroup viewGroup;
        Typeface fontRegular;
        private Activity mActivity;

        public TourAdapter(Activity activity) {
            mActivity = activity;
            fontRegular = Typeface.createFromAsset(mActivity.getAssets(), "fonts/OpenSans-Regular.ttf");
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
                viewGroup = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.tour_one, null);
                TextView patingTxt = (TextView) viewGroup.findViewById(R.id.patingTxt);
                patingTxt.setTypeface(fontRegular);
                patingTxt.setText(Html.fromHtml("<font color=\"#47a842\">" + "Patting" + "</font>" + "<font color=\"#212121\">" + " is the solution" + "</font>"));
            } else {
                viewGroup = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.tour_two, null);
                TextView patingTxt = (TextView) viewGroup.findViewById(R.id.jhonTxt);
                TextView clickTxt = (TextView) viewGroup.findViewById(R.id.clickTxt);
                TextView sarahTxt = (TextView) viewGroup.findViewById(R.id.sarahTxt);
                TextView maxTxt = (TextView) viewGroup.findViewById(R.id.maxTxt);
                TextView stopWaggingTxt = (TextView) viewGroup.findViewById(R.id.stopWaggingTxt);
                patingTxt.setTypeface(fontRegular);
                clickTxt.setTypeface(fontRegular);
                sarahTxt.setTypeface(fontRegular);
                maxTxt.setTypeface(fontRegular);
                stopWaggingTxt.setTypeface(fontRegular);
            }

            container.addView(viewGroup);
            return viewGroup;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
