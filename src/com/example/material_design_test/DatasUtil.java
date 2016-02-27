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
		mList.add(new NavigationItem("��ҳ", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("����", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("��ע", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("�ղ�", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("Բ��", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.DEFAULT));
		mList.add(new NavigationItem("˽��", context.getResources().getDrawable(R.drawable.ic_toggle_star),Style.HASLINE));
		mList.add(new NavigationItem("�л�����", null,Style.NO_ICON));
		mList.add(new NavigationItem("����", null,Style.NO_ICON));
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
