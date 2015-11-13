package demo.presentation.presenter;

import demo.presentation.DefaultSubscriber;
import demo.presentation.view.DataView;
import demo.usecase.UseCase;

/**
 * Created on 08.11.2015.
 */
public abstract class DataPresenterBase<D, VM, P, V extends DataView<VM, ?, ?>> implements DataPresenter<D, V> {

	private V             view;
	private D             data;
	private UseCase<D, P> dataUseCase;

	public DataPresenterBase(UseCase<D, P> dataUseCase) {
		this.dataUseCase = dataUseCase;
	}

	@Override
	public void attachToView(V view) {
		this.view = view;
		updateViewState();
	}

	@Override
	public void detachFromView() {
		this.view = null;
		dataUseCase.unsubscribe();
	}

	@Override
	public void loadData() {
		updateViewState();
		view.showLoading();
		dataUseCase.execute(new DataSubscriber(), getParams());
	}

	@Override
	public void retryLoading() {
		view.hideError();
		loadData();
	}

	@Override
	public void onDataReceived(D data) {
		this.data = data;
		view.hideLoading();
		updateViewState();
	}

	@Override
	public void onErrorOccurredDuringLoading(Throwable exception) {
		view.showError(exception, true);
		view.hideLoading();
		updateViewState();
	}

	@Override
	public void onLoadingCompleted() {
		//implement if needed
	}

	protected void updateViewState() {
		view.displayData((VM)data);
	}

	protected V getView() {
		return view;
	}

	protected abstract P getParams();

	private final class DataSubscriber extends DefaultSubscriber<D> {

		@Override
		public void onCompleted() {
			onLoadingCompleted();
		}

		@Override
		public void onError(Throwable e) {
			onErrorOccurredDuringLoading(e);
		}

		@Override
		public void onNext(D d) {
			onDataReceived(d);
		}
	}
}
