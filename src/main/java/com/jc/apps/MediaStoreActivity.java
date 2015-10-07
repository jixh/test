package com.jc.apps;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jc on 9/26/2015.
 */
public class MediaStoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView tv = new TextView(this);


        String requestedColumns[] = {MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DISPLAY_NAME};
        Cursor cursor = managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,requestedColumns,null,null,
                MediaStore.Audio.Media.DISPLAY_NAME+ " DESC limit 2"
        );
        StringBuilder str = new StringBuilder();
        while (cursor.moveToNext()){
            str.append("\n"+cursor.getString(cursor.getColumnIndex(requestedColumns[0])));
            str.append(","+cursor.getString(cursor.getColumnIndex(requestedColumns[1])));
        }
        cursor.close();

        String requestedColumns2[] = {CallLog.Calls.CACHED_NUMBER_LABEL,CallLog.Calls.DURATION};
        Cursor cursor2 = managedQuery(
            CallLog.Calls.CONTENT_URI, requestedColumns2,
                CallLog.Calls.CACHED_NUMBER_LABEL + "=?"
                , new String[]{"????"}, null
        );

        str.append("\n");
        while (cursor2.moveToNext()){
            str.append("\n"+cursor2.getString(cursor2.getColumnIndex(requestedColumns[0])));
            str.append(","+cursor2.getInt(cursor2.getColumnIndex(requestedColumns[1])));
        }
        cursor2.close();

        String requestedColumns3[] = {
                Browser.BookmarkColumns.TITLE,
                Browser.BookmarkColumns.VISITS,
                Browser.BookmarkColumns.BOOKMARK,
        };
        Cursor cursor3 = managedQuery(
            Browser.BOOKMARKS_URI,
                requestedColumns3,
                Browser.BookmarkColumns.BOOKMARK + "=1"
                ,null,
                Browser.BookmarkColumns.VISITS +" DESC limit 6"
        );

        str.append("\n");
        while (cursor3.moveToNext()){
            str.append("\n"+cursor3.getString(cursor3.getColumnIndex(requestedColumns3[0])));
            str.append(","+cursor3.getInt(cursor3.getColumnIndex(requestedColumns3[1])));
            str.append(","+cursor3.getInt(cursor3.getColumnIndex(requestedColumns3[2])));
        }
        cursor3.close();


/*
        UserDictionary.Words.addWord(this, "", 1, null, null);

        ContentValues cvs = new ContentValues();
        Uri.withAppendedPath()
*/

        tv.setText(str.toString());


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT) ;
        this.addContentView(tv,params);
    }
}
