package demo.presentation.view;

import demo.presentation.presenter.DataPresenter;

/**
 * Created on 08.11.2015.
 */
public interface DataView<VM, P extends DataPresenter, PlatformView> extends ViewBase<P, PlatformView> {

	void displayData(VM data);

	void showError(Throwable exception, boolean shouldRetry);

	void hideError();

	void showLoading();

	void hideLoading();

}
