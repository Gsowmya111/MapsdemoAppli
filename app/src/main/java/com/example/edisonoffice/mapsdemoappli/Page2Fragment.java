package com.example.edisonoffice.mapsdemoappli;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* Fragment used as page 2 */
public class Page2Fragment extends Fragment {
    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;
    private ArrayList<String> conTime;
    private ArrayList<String> conDate;
    private ArrayList<String> conType;
    TextView tv;
    ListView listview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page2, container, false);
       // tv= (TextView) rootView.findViewById(R.id.tvNameMain);
        listview= (ListView) rootView.findViewById(R.id.list);
        conNames = new ArrayList<String>();
        conNumbers = new ArrayList<String>();
        conTime = new ArrayList<String>();
        conDate = new ArrayList<String>();
        conType = new ArrayList<String>();

        Cursor curLog = CallLogHelper.getAllCallLogs(getActivity().getContentResolver());

        setCallLogs(curLog) ;
     Customadapter disadpt = new Customadapter(getActivity().getApplicationContext(), conNames,conNumbers,conTime,conDate,conType);
        listview.setAdapter(disadpt);

        return rootView;
    }


    private void setCallLogs(Cursor curLog) {
        while (curLog.moveToNext()) {
            String callNumber = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            conNumbers.add(callNumber);

            String callName = curLog
                    .getString(curLog
                            .getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));
            if (callName == null) {
                conNames.add("Unknown");
            } else
                conNames.add(callName);

            String callDate = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));
            conDate.add(dateString);

            String callType = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.TYPE));
            if (callType.equals("1")) {
                conType.add("Incoming");
            } else
                conType.add("Outgoing");

            String duration = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DURATION));
            conTime.add(duration);

        }
    }

    class Customadapter extends BaseAdapter {
        ArrayList result;
        Page1Fragment context;
        private ArrayList<String> name;
        private ArrayList<String> no;
        private ArrayList<String> timee;
        private ArrayList<String> datee;
        private ArrayList<String> type;
        private Context mContext;
        LayoutInflater layoutInflater;

        double latitute= 21.7679;
        double longitude= 78.8718;

        public Customadapter(Context c, ArrayList<String> name, ArrayList<String> no, ArrayList<String> timee , ArrayList<String> datee, ArrayList<String> type) {
            // TODO Auto-generated constructor stub
            this.mContext = c;
            this.name=name;
            this.no = no;
            this.timee = timee;
            this.datee = datee;
            this.type = type;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder {

            TextView name,number,time,datte,typee;
            Button btnaddr;
        }

        public View getView(int pos, View child, ViewGroup parent) {
            Holder mHolder;

            if (child == null) {
                layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                child = layoutInflater.inflate(R.layout.liststyle, null);
                mHolder = new Holder();

                //link to TextView
                mHolder.name = (TextView) child.findViewById(R.id.tvNameMain);
                mHolder.number = (TextView) child.findViewById(R.id.tvNumberMain);
                mHolder.time = (TextView) child.findViewById(R.id.tvTime);
                mHolder.datte = (TextView) child.findViewById(R.id.tvDate);
                mHolder.typee = (TextView) child.findViewById(R.id.tvType);
              //  mHolder.btnaddr= (Button) child.findViewById(R.id.button_Address);

                child.setTag(mHolder);
            } else {
                mHolder = (Holder) child.getTag();
            }


            //transfer to TextView in screen
            //  mHolder.tno.setText(anum.get(pos));
            mHolder.name.setText(name.get(pos));
            mHolder.number.setText(no.get(pos));
            mHolder.time.setText("( " + timee.get(pos) + "sec )");
            mHolder.datte.setText(datee.get(pos));
            mHolder.typee.setText("( " + type.get(pos) + " )");



            return child;
        }

    }







}
