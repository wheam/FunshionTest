package com.example.wandoujiafunshiontest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayFunshionTestActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_play_funshion_test);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.play_funshion_test, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    private static final String FUNSHION_URI = "funshionvideo://";
    private static final String FUNSHION_MID = "mid=";
    private static final String FUNSHION_SID = "&sid=";
    private static final String FUNSHION_PACKAGENAME = "com.funshion.video.mobile";
    private static final String FUNSHION_COMPONENTNAME
        = "com.funshion.video.mobile.thirdpartyactive.WandoujiaStartPlayerActivity";
    private Button playButton;
    private EditText midEdit;
    private EditText sidEdit;

    public PlaceholderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_play_funshion_test, container, false);
      playButton = (Button) rootView.findViewById(R.id.play_button);
      midEdit = (EditText) rootView.findViewById(R.id.mid_edit);
      sidEdit = (EditText) rootView.findViewById(R.id.sid_edit);
      playButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          String midString = midEdit.getText().toString();
          String sidString = sidEdit.getText().toString();
          if (midString != null && !midString.isEmpty()
              && sidString != null && !sidString.isEmpty()) {
            String funshionUri = FUNSHION_URI + FUNSHION_MID + midString + FUNSHION_SID + sidString;
            try {
              Intent intent = new Intent();
              intent.setClassName(FUNSHION_PACKAGENAME, FUNSHION_COMPONENTNAME);
              intent.setData(Uri.parse(funshionUri));
              getActivity().startActivity(intent);
            } catch (ActivityNotFoundException e) {
              Toast.makeText(getActivity(), getString(R.string.play_fail) ,
                  Toast.LENGTH_SHORT).show();
            }
          }
        }
      });
      return rootView;
    }
  }

}
