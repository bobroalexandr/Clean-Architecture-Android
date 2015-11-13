package demo.presentation.presenter;

import demo.presentation.view.ViewBase;

/**
 * Created on 08.11.2015.
 */
@SuppressWarnings("all")

public interface PresenterBase<V extends ViewBase> {

	void attachToView(V view);

	void detachFromView();

}
