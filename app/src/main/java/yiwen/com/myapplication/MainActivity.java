package yiwen.com.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yiwen.com.myapplication.View.CalendarView;
import yiwen.com.myapplication.View.CalendarViewDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_date;
    TextView tv_date;
    //管理器
    WindowManager windowManager;
    //日历显示Dialog
    CalendarViewDialog dialog;
    WindowManager.LayoutParams lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        initView();
        //点击事件监听
        initListener();

    }


    /**
     * 初始化
     */
    private void initView() {

        btn_date= (Button) findViewById(R.id.btn_date);
        tv_date= (TextView) findViewById(R.id.tv_date);

    }

    /**
     * 点击事件
     */
    private void initListener() {
        btn_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                dialog=new CalendarViewDialog(MainActivity.this);
                dialog.show();
                dialog.calendar.setOnItemClickListener(new CalendarView.OnItemClickListener() {
                    @Override
                    public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate) {
                        if (!dialog.calendar.isSelectMore()) {
                            tv_date.setText(dialog.format.format(downDate));

                        }
                        dialog.dismiss();
                    }
                });
                windowManager = getWindowManager();
                lp = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
                lp.width = windowManager.getDefaultDisplay().getWidth();//为获取屏幕宽、高,宽度设置为全屏
                dialog.getWindow().setAttributes(lp);
                break;
        }
    }
}
