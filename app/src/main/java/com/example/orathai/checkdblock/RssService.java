package com.example.orathai.checkdblock;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.SyncStateContract;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class RssService extends IntentService{

    private static final String RSS_LINK = "http://blog.checkd.no/?feed=rss2";
    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";

    private static final String TAG = "CHECKD-Fragments";

    public RssService() {
        super("RssService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(RssService.TAG, "Service started");
        List<RssItem> rssItems = null;
        try {
            CheckdRssParser parser = new CheckdRssParser();
            rssItems = parser.parse(getInputStream(RSS_LINK));
        } catch (XmlPullParserException | IOException e) {
            Log.w(e.getMessage(), e);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) rssItems);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        receiver.send(0, bundle);

    }

    public InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.w(RssService.TAG, "Exception while retrieving the input stream", e);
            return null;
        }
    }
}
