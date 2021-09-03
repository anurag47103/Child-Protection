package com.learningandroid.childprotection.gettingStats;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.model.recycler_View_item_Class;

import java.util.ArrayList;
import java.util.List;

public class usage_Stats_Manager_Main extends Object{

    public List<recycler_View_item_Class> getuserlist(){
        //todo
        //the backend code to get the realtime data will be written here

        // temprory list to check if the app is running
        //this is the list which is shown in the recycle view
        List<recycler_View_item_Class> userList = new ArrayList<>();
        for(int i=0;i<3;i++) {
            userList.add(new recycler_View_item_Class(R.drawable.ap, "Amazon Prime", "04:23"));
            userList.add(new recycler_View_item_Class(R.drawable.fb, "Facebook", "05:03"));
            userList.add(new recycler_View_item_Class(R.drawable.fm, "Messenger", "06:20"));
            userList.add(new recycler_View_item_Class(R.drawable.hs, "HotStar", "01:12"));
            userList.add(new recycler_View_item_Class(R.drawable.ig, "Instagram", "01:56"));
            userList.add(new recycler_View_item_Class(R.drawable.nf, "Netflix", "00:54"));
            userList.add(new recycler_View_item_Class(R.drawable.pubg, "PUBG mobile", "05:21"));
            userList.add(new recycler_View_item_Class(R.drawable.sc, "Snapchat", "03:00"));
            userList.add(new recycler_View_item_Class(R.drawable.tg, "Telegram", "00:55"));
            userList.add(new recycler_View_item_Class(R.drawable.wa, "Whatsapp", "06:32"));
            userList.add(new recycler_View_item_Class(R.drawable.yt, "Youtube", "09:23"));
        }
        return userList;
    }

}
