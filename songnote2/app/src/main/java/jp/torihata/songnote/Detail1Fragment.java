package jp.torihata.songnote;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class Detail1Fragment extends Fragment {

    public Detail1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        Integer position = i.getIntExtra("POSITION", 1);
        position++;

        final Cursor cursor = getActivity().getContentResolver().query(Uri.parse(String.valueOf(Lyrics.CONTENT_URI) + "/" + position), null, null, null, null);
        cursor.moveToFirst();

        // ActionBarのタイトルをセット
        int fieldIndex = cursor.getColumnIndex(Lyrics.COLUMN_NAME_TITLE);
        String title = cursor.getString(fieldIndex);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);

        // 本文のセット
        int fieldIndex2 = cursor.getColumnIndex(Lyrics.COLUMN_NAME_BODY);
        String body = cursor.getString(fieldIndex2);
        View v =inflater.inflate(R.layout.fragment_detail1, container, false);
        final TextView bodyView = (TextView)v.findViewById(R.id.body);
        bodyView.setText(body);

        //カーソルを閉じる
        cursor.close();

        return v;
    }
}
