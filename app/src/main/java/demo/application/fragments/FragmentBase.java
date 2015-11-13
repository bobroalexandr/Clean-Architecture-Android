package demo.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.presentation.presenter.PresenterBase;
import demo.presentation.view.ViewBase;

/**
 * Created on 11.11.2015.
 */
public abstract class FragmentBase<V extends ViewBase<P,View>, P extends PresenterBase<V>> extends Fragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(getLayoutId(), container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		V viewBase = getViewBase();
		P presenterBase = getPresenterBase();
		initView(viewBase, getView(), savedInstanceState);
		initPresenter(presenterBase, savedInstanceState);
		viewBase.setPresenter(presenterBase);
		presenterBase.attachToView(viewBase);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getViewBase().releaseViewComponents();
		getPresenterBase().detachFromView();
	}

	protected void initView(V viewBase, View view, @Nullable Bundle savedInstanceState){
		viewBase.initializeViewComponents(view);
	}

	protected void initPresenter(P presenterBase, @Nullable Bundle savedInstanceState) {
		//implement if needed
	}

	protected abstract V getViewBase();

	protected abstract P getPresenterBase();

	protected abstract int getLayoutId();
}
