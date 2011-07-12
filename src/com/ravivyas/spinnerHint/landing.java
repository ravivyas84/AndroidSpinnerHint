package com.ravivyas.spinnerHint;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class landing extends Activity {

	private String TAG = "SpinnerHint";

	private Spinner typeSpinner;

	private LayoutInflater mInflator;
	private boolean selected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initUI();
	}

	private void initUI() {
		selected = false;
		typeSpinner = (Spinner) findViewById(R.id.id_type_spinner);
		typeSpinner.setAdapter(typeSpinnerAdapter);
		typeSpinner.setOnItemSelectedListener(typeSelectedListener);
		typeSpinner.setOnTouchListener(typeSpinnerTouchListener);
		mInflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		Log.d(TAG, "UI Initialized");
	}

	private SpinnerAdapter typeSpinnerAdapter = new BaseAdapter() {

		private TextView text;
		private String[] data = { "Selection 1", "Selection 2", "Selection 3" };
		private int count = 3;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflator.inflate(R.layout.row_spinner, null);
			}
			text = (TextView) convertView.findViewById(R.id.spinnerTarget);
			if (!selected) {
				text.setText("Please select a value");
			} else {
				text.setText(data[position]);
			}
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return data[position];
		}

		@Override
		public int getCount() {
			return count;
		}

		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflator.inflate(
						android.R.layout.simple_spinner_dropdown_item, null);
			}
			text = (TextView) convertView.findViewById(android.R.id.text1);
			text.setText(data[position]);
			return convertView;
		};
	};

	private OnItemSelectedListener typeSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			Log.d(TAG, "user selected : "
					+ typeSpinner.getSelectedItem().toString());

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	};

	private OnTouchListener typeSpinnerTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			selected = true;
			((BaseAdapter) typeSpinnerAdapter).notifyDataSetChanged();
			return false;
		}
	};

}