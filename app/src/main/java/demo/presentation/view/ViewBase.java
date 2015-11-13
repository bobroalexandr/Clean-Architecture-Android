package demo.presentation.view;

import demo.presentation.presenter.PresenterBase;

/**
 * Created on 08.11.2015.
 *
 * @param <PlatformView> represents object that contains components that can be displayed on screen.
 */
public interface ViewBase<P extends PresenterBase, PlatformView> {

	void initializeViewComponents(PlatformView platformView);

	void releaseViewComponents();

	void setPresenter(P presenter);

}
