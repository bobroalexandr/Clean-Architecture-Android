package demo.presentation.presenter;

import demo.presentation.view.DataView;

/**
 * Created on 08.11.2015.
 */
public interface DataPresenter<D, V extends DataView> extends PresenterBase<V> {

	void loadData();

	void retryLoading();

	void onDataReceived(D data);

	void onErrorOccurredDuringLoading(Throwable error);

	void onLoadingCompleted();
}
