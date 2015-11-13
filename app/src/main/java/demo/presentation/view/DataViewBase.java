package demo.presentation.view;

import demo.presentation.presenter.DataPresenter;

/**
 * Created on 08.11.2015.
 */
public abstract class DataViewBase<VM, P extends DataPresenter, PlatformView> implements DataView<VM, P, PlatformView> {

	private P dataPresenter;
	private PlatformView rootView;

	@Override
	public void initializeViewComponents(PlatformView view) {
		this.rootView = view;
	}

	@Override
	public void releaseViewComponents() {
 		this.rootView = null;
	}

	@Override
	public void setPresenter(P presenter) {
		this.dataPresenter = presenter;
	}

	protected P getDataPresenter() {
		return dataPresenter;
	}

	protected PlatformView getRootView() {
		return rootView;
	}

	@Override
	public void displayData(VM data) {
		//implement if needed
	}

	@Override
	public void showError(Throwable exception, boolean shouldRetry) {
		//implement if needed
	}

	@Override
	public void hideError() {
		//implement if needed
	}

	@Override
	public void showLoading() {
		//implement if needed
	}

	@Override
	public void hideLoading() {
		//implement if needed
	}
}
