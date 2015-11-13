package demo.usecase;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case.
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job on a specified subscribeOnScheduler and will post the result to the observeOnScheduler.
 */
public abstract class UseCase<T,P> {

	private final Scheduler subscribeOnScheduler;
	private final Scheduler observeOnScheduler;

	private Subscription subscription = Subscriptions.empty();

	public UseCase() {
		this.subscribeOnScheduler = Schedulers.io();
		this.observeOnScheduler = AndroidSchedulers.mainThread();
	}

	public UseCase(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
		this.subscribeOnScheduler = subscribeOnScheduler;
		this.observeOnScheduler = observeOnScheduler;
	}

	/**
	 * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
	 */
	protected abstract Observable<T> provideUseCaseObservable(P params);

	/**
	 * Executes the current use case.
	 *
	 * @param useCaseSubscriber The object that will be listen to the observable build with {@link #provideUseCaseObservable(P params)}.
	 */
	public void execute(Subscriber<T> useCaseSubscriber, P params) {
		this.subscription = this.provideUseCaseObservable(params)
			.subscribeOn(subscribeOnScheduler)
			.observeOn(observeOnScheduler)
			.subscribe(useCaseSubscriber);
	}

	public void unsubscribe() {
		if (!subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}
}