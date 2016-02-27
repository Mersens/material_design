package com.example.material_design_test;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity implements
		NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private PullToLoadView mPullToLoadView;
	private SimpleAdapter mAdapter;
	private boolean isLoading = false;
	private boolean isHasLoadedAll = false;
	private int nextPage;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_topdrawer);
		initViews();
		initEvent();
	}

	private void initEvent() {
		RecyclerView mRecyclerView = mPullToLoadView.getRecyclerView();
		LinearLayoutManager manager = new LinearLayoutManager(this,
				LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(manager);
		mAdapter = new SimpleAdapter();
		mRecyclerView.setAdapter(mAdapter);
		mPullToLoadView.isLoadMoreEnabled(true);
		mPullToLoadView.setPullCallback(new PullCallback() {
			@Override
			public void onLoadMore() {
				loadData(nextPage);
			}

			@Override
			public void onRefresh() {
				mAdapter.clear();
				isHasLoadedAll = false;
				loadData(1);
			}

			@Override
			public boolean isLoading() {
				Log.e("main activity", "main isLoading:" + isLoading);
				return isLoading;
			}

			@Override
			public boolean hasLoadedAllItems() {
				return isHasLoadedAll;
			}
		});

		mPullToLoadView.initLoad();
	}

	private void initViews() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_drawer);
		mNavigationDrawerFragment.setup(R.id.fragment_drawer,
				(DrawerLayout) findViewById(R.id.drawer), mToolbar);
		mPullToLoadView = (PullToLoadView) findViewById(R.id.pullToLoadView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Toast.makeText(this, "Menu item selected -> " + position,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen())
			mNavigationDrawerFragment.closeDrawer();
		else
			super.onBackPressed();
	}

	private static class SimpleAdapter extends RecyclerView.Adapter<CellHolder> {
		private List<String> mList;

		public SimpleAdapter() {
			mList = new ArrayList<>();
		}

		@Override
		public CellHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			View view = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.item, viewGroup, false);
			return new CellHolder(view);
		}

		@Override
		public void onBindViewHolder(CellHolder holder, int i) {

		}

		public void add(String s) {
			mList.add(s);
			notifyDataSetChanged();
		}

		public void clear() {
			mList.clear();
			notifyDataSetChanged();
		}

		@Override
		public int getItemCount() {
			return mList.size();
		}
	}

	private static class CellHolder extends RecyclerView.ViewHolder {

		public CellHolder(View itemView) {
			super(itemView);
		}
	}

	private void loadData(final int page) {
		isLoading = true;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (isHasLoadedAll) {
					Toast.makeText(MainActivity.this, "没有更多数据了",
							Toast.LENGTH_SHORT).show();
				}
				if (page > 10) {
					isHasLoadedAll = true;
					return;
				}
				for (int i = 0; i <= 15; i++) {
					mAdapter.add(i + "");
				}
				mPullToLoadView.setComplete();
				isLoading = false;
				nextPage = page + 1;
			}
		}, 3000);
	}
	
}
