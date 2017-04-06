package yiwen.com.myapplication.View;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import yiwen.com.myapplication.R;

/**
 * Created by y.yiwen on 1/13/2016.
 */
public class CalendarViewDialog extends Dialog {
    //判断是否显示
    private boolean flag = true;
    private Context context;
    //日历控件
    public CalendarView calendar;

    private ImageView calendarLeft;

    private TextView calendarCenter;

    private ImageView calendarRight;

    public SimpleDateFormat format;

    Dialog dialog;
    //回调接口
     private OnSureClickListener mListener;

    public CalendarViewDialog(Context context) {
        super(context, R.style.CalendarViewDialog);
        //加载布局文件
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_calendar, null);
        format = new SimpleDateFormat("yyyy-MM-dd");
        //获取日历控件对象
        calendar = (CalendarView)view.findViewById(R.id.custom_calendar);
        calendar.setSelectMore(false); //单选
        calendarLeft = (ImageView)view.findViewById(R.id.calendarLeft);
        calendarCenter = (TextView)view.findViewById(R.id.calendarCenter);
        calendarRight = (ImageView)view.findViewById(R.id.calendarRight);
        try {
            //设置日历日期
            Date date = format.parse("2015-01-01");
            calendar.setCalendarData(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
        String[] ya = calendar.getYearAndmonth().split("-");
        calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
        calendarLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击上一月 同样返回年月
                String leftYearAndmonth = calendar.clickLeftMonth();
                String[] ya = leftYearAndmonth.split("-");
                calendarCenter.setText(ya[0]+"年"+ya[1]+ "月");
            }
        });
        calendarRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击下一月
                String rightYearAndmonth = calendar.clickRightMonth();
                String[] ya = rightYearAndmonth.split("-");
                calendarCenter.setText(ya[0]+"年"+ya[1]+"月");
            }
        });
        //设置控件监听，可以监听到点击的每一天（大家也可以在控件中根据需求设定）
//        calendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.getText(calendarCenter.toString());
//                dismiss();
//            }
//        });
        calendar.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void OnItemClick(Date selectedStartDate,
                                    Date selectedEndDate, final Date downDate) {
                if(calendar.isSelectMore()){
                    Toast.makeText(getContext(),format.format(selectedStartDate) + "到" + format.format(selectedEndDate), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), format.format(downDate), Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }
        });
        setCanceledOnTouchOutside(true);
        //dialog添加视图
        setContentView(view);
    }

    public CalendarViewDialog(Context context,OnSureClickListener listener) {
        super(context);
        mListener=listener;
    }
    /**
     * 回调接口
     */
    public interface OnSureClickListener{
        void getText(String result);
    }
    /**
     * 显示
     */
    @Override
    public void show() {
        super.show();
    }
    /**
     * 消失
     */
    @Override
    public void dismiss() {
        super.dismiss();
        flag = false;
    }
//    //保存到本地
//    public static void saveFile(String str) {
//        String filePath = null;
//        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        if (hasSDCard) {
//            filePath = Environment.getExternalStorageDirectory().toString() + Datepath + "Date.txt";
//        } else
//            filePath = Environment.getDownloadCacheDirectory().toString() + Datepath + "Date.txt";
//
//        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                File dir = new File(file.getParent());
//                dir.mkdirs();
//                file.createNewFile();
//            }
//            FileOutputStream outStream = new FileOutputStream(file);
//            outStream.write(str.getBytes());
//            outStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
