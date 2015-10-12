package jp.torihata.songnote;

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

        // ActionBarのタイトルをセット
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);

        // 本文のセット
        View v =inflater.inflate(R.layout.fragment_detail1, container, false);
        final TextView body = (TextView)v.findViewById(R.id.body);
        body.setText(getString(R.string.lyrics_ontime));
        return v;
    }
}
