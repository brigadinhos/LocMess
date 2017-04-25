package pt.ulisboa.tecnico.cmu.tg14.locmessclient;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import net.danlew.android.joda.JodaTimeAndroid;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ulisboa.tecnico.cmu.tg14.locmessclient.Listeners.DateTimeListener;

public class AddMessage extends AppCompatActivity {

    private final String TAG = "AddMessage";
    private EditText mMessageContent;
    private EditText mStartTime;
    private EditText mEndTime;
    private Calendar mCalendar;
    private Button mNext;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setTitle(R.string.title_activity_add_message);
        activity = this;

        mCalendar = Calendar.getInstance();

        mMessageContent = (EditText) findViewById(R.id.add_message_content);
        mStartTime = (EditText) findViewById(R.id.add_message_start_time);
        mEndTime = (EditText) findViewById(R.id.add_message_end_time);
        mNext = (Button) findViewById(R.id.button_next);

        mStartTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasChange) {
                timeListenerAux(mStartTime);
            }
        });

        mStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeListenerAux(mStartTime);
            }
        });

        mEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasChange) {
                timeListenerAux(mEndTime);
            }
        });

        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeListenerAux(mEndTime);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                hideKeyboard();

                if (!isValidInput()) {
                    return;
                }
                Intent i = new Intent(activity,MessageLocationActivity.class);
                i.putExtra("mMessageContent",mMessageContent.getText().toString());
                i.putExtra("mStartTime",mStartTime.getText().toString());
                i.putExtra("mEndTime",mEndTime.getText().toString());

                //TODO add message arguments to activity or save to disk
                startActivity(i);
                finish();
            }
        });
    }

    private boolean isValidInput() {
        if (mMessageContent.length() <= 0) {
            Toast.makeText(activity, "You need to write a message", Toast.LENGTH_LONG).show();
        } else if (mStartTime.length() <= 0) {
            Toast.makeText(activity, "You need to set the Start Time", Toast.LENGTH_LONG).show();
        }

        /*else if (getCalendar(mEndTime.getText().toString()).before(getCalendar(mStartTime.getText().toString()))){
            //if endtime is before starttime
            Toast.makeText(activity, "You need to set the End Time after Start Time", Toast.LENGTH_LONG).show();
        }*/

        return mMessageContent.length() > 0 && mStartTime.length() > 0;
    }

    private Calendar getCalendar(String s){
        String pattern = "(\\d{4})-(\\d{2}|\\d{1})-(\\d{2}|\\d{1}) (\\d{2}|\\d{1}):(\\d{2}|\\d{1})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);

        Calendar c = new GregorianCalendar(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));

        return c;
    }

    private void hideKeyboard() {
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    private void timeListenerAux(EditText time) {
        DateTimeListener dateTimeListener = new DateTimeListener(getFragmentManager(), time);
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(dateTimeListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show(getFragmentManager(),"DateTimePickerDialog");
    }
}
