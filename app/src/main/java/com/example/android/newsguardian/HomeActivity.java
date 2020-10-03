package com.example.android.newsguardian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private double position = 0;
	
	private ArrayList<HashMap<String, Object>> listHeadline = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear4;
	private Button btn_headline;
	private Button btn_sport;
	private Button btn_technology;
	private Button btn_health;
	private Button btn_debates;
	private Button btn_lifestyle;
	private LinearLayout linear_headline;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private ListView listview_headline;
	private ListView listview2;
	private ListView listview1;
	
	private RequestNetwork reqHeadline;
	private RequestNetwork.RequestListener _reqHeadline_request_listener;
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private ObjectAnimator oa_d = new ObjectAnimator();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		btn_headline = (Button) findViewById(R.id.btn_headline);
		btn_sport = (Button) findViewById(R.id.btn_sport);
		btn_technology = (Button) findViewById(R.id.btn_technology);
		btn_health = (Button) findViewById(R.id.btn_health);
		btn_debates = (Button) findViewById(R.id.btn_debates);
		btn_lifestyle = (Button) findViewById(R.id.btn_lifestyle);
		linear_headline = (LinearLayout) findViewById(R.id.linear_headline);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		listview_headline = (ListView) findViewById(R.id.listview_headline);
		listview2 = (ListView) findViewById(R.id.listview2);
		listview1 = (ListView) findViewById(R.id.listview1);
		reqHeadline = new RequestNetwork(this);
		d = new AlertDialog.Builder(this);
		
		btn_headline.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Headlines");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "http://content.guardianapis.com/tags?q=news&api-key=test", "", _reqHeadline_request_listener);
			}
		});
		
		btn_sport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Sport");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.guardianapis.com/sections?q=sport&api-key=test", "", _reqHeadline_request_listener);
			}
		});
		
		btn_technology.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Technology");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.guardianapis.com/sections?q=technology&api-key=test", "", _reqHeadline_request_listener);
			}
		});
		
		btn_health.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Health");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.guardianapis.com/sections?q=health&api-key=test", "", _reqHeadline_request_listener);
			}
		});
		
		btn_debates.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Debates");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.guardianapis.com/search?q=debates&api-key=test", "", _reqHeadline_request_listener);
			}
		});
		
		btn_lifestyle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setTitle("Lifestyle");
				reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.guardianapis.com/search?q=lifeandstyle", "", _reqHeadline_request_listener);
			}
		});
		
		listview_headline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setClass(getApplicationContext(), OpenNewsActivity.class);
				i.putExtra("url", listHeadline.get((int)_position).get("url").toString());
				startActivity(i);
			}
		});
		
		listview_headline.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				SketchwareUtil.showMessage(getApplicationContext(), "Created by ".concat("Roger Van Wyk"));
				return true;
			}
		});
		
		_reqHeadline_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				listHeadline = new Gson().fromJson(_response.substring((int)(_tag.length()), (int)(Double.parseDouble(_response) - 1)), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview_headline.setAdapter(new Listview_headlineAdapter(listHeadline));
				((BaseAdapter)listview_headline.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
				final View v2 = (View) getLayoutInflater().inflate(R.layout.error, null);
				
				ScrollView scrollV = new ScrollView(getApplicationContext()); scrollV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				
				scrollV.addView(v2);
				
				d.setView(scrollV);
				d.setCancelable(true);
				
				d.create().show();
			}
		};
	}
	private void initializeLogic() {
		setTitle("ABND News");
		//_toolbar.setTitleMargin(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
		        
		androidx.appcompat.app.ActionBar actionbar=getSupportActionBar();
		        
		actionbar.setHomeButtonEnabled(false);
		        
		actionbar.setDisplayHomeAsUpEnabled(false);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_headline);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_sport);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_technology);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_health);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_debates);
		_view(100, 100, 100, 100, "#fff44336", "#00000000", 0, btn_lifestyle);
		reqHeadline.startRequestNetwork(RequestNetworkController.GET, "https://content.gaurdianapis.com/search", "", _reqHeadline_request_listener);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		_exit_dialog();
	}
	private void _view (final double _kiriatas, final double _kananatas, final double _kiribawah, final double _kananbawah, final String _WarnaBackground, final String _WarnaGaris, final double _tebalgaris, final View _Widget) {
		Double kra = _kiriatas;
		Double krb = _kiribawah;
		Double kna = _kananatas;
		Double knb = _kananbawah;
		Double tg = _tebalgaris;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable ();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float []
		{kra.floatValue(),kra.floatValue(),
			kna.floatValue(),kna.floatValue(),
			krb.floatValue(),krb.floatValue(),
			knb.floatValue(),knb.floatValue()});
		s.setColor(Color.parseColor(_WarnaBackground));
		s.setStroke(tg.intValue(), Color.parseColor(_WarnaGaris));
		_Widget.setBackground(s);
	}
	
	
	private void _setBgCorners (final View _view, final double _radius, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
		gd.setColor(Color.parseColor("#" + _color.replace("#", ""))); /* color */
		gd.setCornerRadius((int)_radius); /* radius */
		gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
		_view.setBackground(gd);
	}
	
	
	private void _bounce (final View _view) {
		oa_d.setTarget(_view);
		oa_d.setPropertyName("rotation");
		oa_d.setFloatValues((float)(90), (float)(0));
		oa_d.setDuration((int)(1000));
		oa_d.setInterpolator(new BounceInterpolator());
		oa_d.start();
	}
	
	
	private void _exit_dialog () {
		final AlertDialog exit_dialog = new AlertDialog.Builder(HomeActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		
		View convertView = (View) inflater.inflate(R.layout.exit_dialog, null);
		exit_dialog.setView(convertView);
		
		exit_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  exit_dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		
		
		LinearLayout i_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg);
		
		LinearLayout i_div = (LinearLayout) convertView.findViewById(R.id.linear_div);
		
		Button i_ok = (Button) convertView.findViewById(R.id.exit_okay_button);
		
		Button i_cancel = (Button) convertView.findViewById(R.id.exit_cancel_button);
		
		ImageView i_header = (ImageView) convertView.findViewById(R.id.img_header);
		
		TextView i_title = (TextView) convertView.findViewById(R.id.txt_title);
		
		TextView i_msg = (TextView) convertView.findViewById(R.id.txt_msg);
		
		_setBgCorners(i_bg, 8, "#FFFFFF");
		_setBgCorners(i_ok, 8, "#03A9F4");
		_setBgCorners(i_cancel, 8, "#68CFFD");
		i_header.setElevation(5);
		i_ok.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				exit_dialog.dismiss();
				_closeApp();
			}});
		i_cancel.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				exit_dialog.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), "Cancelled!");
			}});
		exit_dialog.show();
		_bounce(i_header);
	}
	
	
	private void _closeApp () {
		finishAffinity();
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
	
	
	public class Listview_headlineAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview_headlineAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.model_news, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final LinearLayout linear4 = (LinearLayout) _v.findViewById(R.id.linear4);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
			final LinearLayout linear6 = (LinearLayout) _v.findViewById(R.id.linear6);
			final TextView txt_title = (TextView) _v.findViewById(R.id.txt_title);
			final LinearLayout linear7 = (LinearLayout) _v.findViewById(R.id.linear7);
			final LinearLayout linear8 = (LinearLayout) _v.findViewById(R.id.linear8);
			final TextView txt_publishedat = (TextView) _v.findViewById(R.id.txt_publishedat);
			
			final androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(HomeActivity.this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			int mgs = (int)getDip(5);
			
			lp.setMargins(mgs,mgs,mgs,mgs);
			cv.setLayoutParams(lp);
			
			cv.setCardBackgroundColor(Color.WHITE);
			
			cv.setRadius((int)getDip(7));
			
			cv.setCardElevation(3);
			
			cv.setMaxCardElevation(12);
			
			cv.setPreventCornerOverlap(true);
			((ViewGroup)linear2.getParent()).removeView(linear2);
			linear1.removeAllViews();
			linear1.addView(cv);
			
			cv.addView(linear2);
			Glide.with(getApplicationContext()).load(Uri.parse(listHeadline.get((int)_position).get("urlToImage").toString())).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).placeholder(R.drawable.ic_load).into(imageview1);
			txt_title.setText(listHeadline.get((int)_position).get("title").toString());
			txt_publishedat.setText(listHeadline.get((int)_position).get("publishedAt").toString());
			Glide.with(getApplicationContext()).load(Uri.parse("url")).into(imageview1);
			
			return _v;
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.bbb, null);
			}
			
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
			
			
			
			return _v;
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.bbb, null);
			}
			
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
			
			
			
			return _v;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
