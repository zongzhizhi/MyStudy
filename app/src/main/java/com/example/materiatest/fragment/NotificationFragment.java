package com.example.materiatest.fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.example.activity.OneLifeActivity;
import com.example.materiatest.R;
import com.example.materiatest.activity.MainActivity;
import com.example.materiatest.activity.NatificationActivity;
import com.example.materiatest.utils.LabelClickableSpan;

public class NotificationFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "NotificationFragment";
    private Button bt_createNotification;
    private TextView tvSpan;


    @Override
    protected void init(View view) {

        bt_createNotification = view.findViewById(R.id.bt_createNotification);
        bt_createNotification.setOnClickListener(this);
        tvSpan = view.findViewById(R.id.tv_Span);
        tvSpan.setOnClickListener(this);
        createStringSpan();
    }

    private void createStringSpan() {
        String[] strings = new String[]{"数据A;", "数据B;", "数据C;"};
        String[] emailAdd = new String[]{"111111", "22222", "33333"};
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        int start = 0;
        int end = 0;
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            LabelClickableSpan clickableSpan = new LabelClickableSpan(emailAdd[i]);
            end = stringBuilder.length();
            Log.i(TAG,
                    "createStringSpan: start-->" + start + "--end-->" + end + "-->strings-->" + strings[i]);
            stringBuilder.setSpan(clickableSpan, start, end,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end;
        }

        tvSpan.setText(stringBuilder);
        tvSpan.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_notification;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_createNotification:
                createNotificationChannel();
                createNotification();
                startActivity(new Intent(getActivity(),OneLifeActivity.class));
                break;
        }
    }

    private void createNotification() {
        NotificationManager manager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(getActivity(), NatificationActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getContext(), "1")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.timg))
                .setContentTitle("标题")
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentText("文本内容表较多，看我能不能全部显示出来呀！文本内容表较多，看我能不能全部显示出来呀！文本内容表较多，看我能不能全部显示出来呀！")
                .build();

        manager.notify(1, notification);
    }

    private void createNotificationChannel() {
        // 创建NotificationChannel，但仅在API 26+上创建，因为
        // NotificationChannel类是新的，并且不在支持库中
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "渠道名字111";
            String description = "渠道说明222";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =
                    new NotificationChannel("1", name,
                            importance);
            channel.setDescription(description);
            //向系统注册频道；在此之后，您将无法更改重要性
            // 或其他通知行为
            NotificationManager notificationManager =
                    getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
