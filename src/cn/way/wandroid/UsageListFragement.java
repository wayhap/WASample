package cn.way.wandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.way.wandroid.activities.tabhost.FragmentTabHostUsage;
import cn.way.wandroid.applation.PhotoPickerUsage;
import cn.way.wandroid.toast.Toaster;
import cn.way.wandroid.utils.WLog;
import cn.way.wandroid.views.ScratchViewFragment;
import cn.way.wandroid.views.layouts.ExpandableListViewUsage;
import cn.way.wandroid.views.wdialog.WDialogAndListViewUsage;

public class UsageListFragement extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.usage_list_piece, container, false);
        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                DummyContent.ITEMS));
        lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				DummyItem item = DummyContent.ITEMS.get(position);
					Bundle extras = new Bundle();
					extras.putString("TEST", "TESTVALUE");
					FragmentContentActivity.startWithFragment(getActivity(),extras,item.clazz,1001,false);
			}
		});
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String result = "request:"+requestCode+" resultCode:"+resultCode+" data:"+data;
		Toaster.instance(getActivity()).setup(result).show();
		WLog.d(result);
	}
	
	public static class DummyContent {
	    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	    static {
	    	addItem(new DummyItem(ScratchViewFragment.class));
	    	addItem(new DummyItem(ExpandableListViewUsage.class));
	    	addItem(new DummyItem(PhotoPickerUsage.class));
	    	addItem(new DummyItem(WDialogAndListViewUsage.class));
	    	addItem(new DummyItem(FragmentTabHostUsage.class));
	    }
	    static void addItem(DummyItem item) {
	        ITEMS.add(item);
	    }
	}
	static class DummyItem {
        public Class<? extends Fragment> clazz;
        private String title;
        public DummyItem(Class<? extends Fragment> clazz) {
            this.clazz = clazz;
            this.title = clazz.getSimpleName();
        }
        @Override
        public String toString() {
        	return (1+DummyContent.ITEMS.indexOf(this))+"."+this.title;
        }
    }
	
}
