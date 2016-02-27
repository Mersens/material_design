package com.example.material_design_test;

import java.util.ArrayList;
import java.util.List;

import com.example.material_design_test.NavigationItem.Style;

import android.content.Context;

public class DatasUtil {
	private static DatasUtil mDatas;
	private List<NavigationItem> mList;
	
	private DatasUtil(Context context){
		mList=new ArrayList<NavigationItem>();
		mList.add(new NavigationItem("首页", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("发现", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("关注", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("收藏", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("圆桌", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("私信", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.HASLINE));
		mList.add(new NavigationItem("切换主题", null,Style.NO_ICON));
		mList.add(new NavigationItem("设置", null,Style.NO_ICON));
	}
	
	public static DatasUtil getInstance(Context context){
		if(mDatas==null){
			synchronized (DatasUtil.class) {
				if(mDatas==null){
					mDatas=new DatasUtil(context);
				}
			}
		}
		return mDatas;
		
	}
	
	
	public  List<NavigationItem> getMenu(){
		 return new ArrayList<NavigationItem>(mList);
	}

}
