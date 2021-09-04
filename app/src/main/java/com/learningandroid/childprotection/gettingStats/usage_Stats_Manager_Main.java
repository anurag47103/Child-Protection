package com.learningandroid.childprotection.gettingStats;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.learningandroid.childprotection.PermissionHandler;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.model.recyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class usage_Stats_Manager_Main extends Object{

    public List<recyclerViewItem> getuserlist(){
        //todo
        //the backend code to get the realtime data will be written here
        // temprory list to check if the app is running
        //this is the list which is shown in the recycle view



        List<recyclerViewItem> userList = new ArrayList<>();
        for(int i=0;i<3;i++) {
            userList.add(new recyclerViewItem(R.drawable.ap, "Amazon Prime", "04:23"));
            userList.add(new recyclerViewItem(R.drawable.fb, "Facebook", "05:03"));
            userList.add(new recyclerViewItem(R.drawable.fm, "Messenger", "06:20"));
            userList.add(new recyclerViewItem(R.drawable.hs, "HotStar", "01:12"));
            userList.add(new recyclerViewItem(R.drawable.ig, "Instagram", "01:56"));
            userList.add(new recyclerViewItem(R.drawable.nf, "Netflix", "00:54"));
            userList.add(new recyclerViewItem(R.drawable.pubg, "PUBG mobile", "05:21"));
            userList.add(new recyclerViewItem(R.drawable.sc, "Snapchat", "03:00"));
            userList.add(new recyclerViewItem(R.drawable.tg, "Telegram", "00:55"));
            userList.add(new recyclerViewItem(R.drawable.wa, "Whatsapp", "06:32"));
            userList.add(new recyclerViewItem(R.drawable.yt, "Youtube", "09:23"));
        }
        return userList;
    }

}
