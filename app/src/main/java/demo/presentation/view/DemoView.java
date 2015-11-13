package demo.presentation.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import demo.R;
import demo.presentation.presenter.DataPresenter;

/**
 * Created on 12.11.2015.
 */
public class DemoView<VM, P extends DataPresenter> extends DataViewBase<VM, P, View> {

	ProgressDialog progressDialog;
	Snackbar snackbar;

	@Override
	public void showError(Throwable exception, boolean shouldRetry) {
		super.showError(exception, shouldRetry);
		snackbar = Snackbar.make(getRootView(), getTextForError(exception), shouldRetry ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG);
		if (shouldRetry) {
			snackbar.setAction(R.string.action_retry, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getDataPresenter().retryLoading();
				}
			});
		}
		snackbar.show();
	}

	@Override
	public void hideError() {
		super.hideError();
		if(snackbar != null && snackbar.isShown()) {
			snackbar.dismiss();
		}
	}


	protected String getTextForError(Throwable exception) {
		return getContext().getString(R.string.message_unknown_error);
	}

	@Override
	public void showLoading() {
		super.showLoading();
		if(progressDialog == null) {
			progressDialog = new ProgressDialog(getContext());
			progressDialog.show();
		} else if(!progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	@Override
	public void hideLoading() {
		super.hideLoading();
		if(progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	protected Context getContext() {
		return getRootView().getContext();
	}
}
