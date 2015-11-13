package demo.usecase;

import java.util.List;

import demo.datasource.Criteria;
import demo.datasource.criteria.UserIdCriteria;
import demo.datasource.repository.DataSourceException;
import demo.datasource.repository.RepositoryBase;
import demo.domain.Post;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import solid.stream.Stream;

/**
 * Created on 12.11.2015.
 */
public class GetPostsByUserIdUseCase extends UseCase<List<Post>, String> {

	private final RepositoryBase<Post> postRepository;

	public GetPostsByUserIdUseCase(RepositoryBase<Post> repositoryBase) {
		this.postRepository = repositoryBase;
	}

	public GetPostsByUserIdUseCase(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler, RepositoryBase<Post> repositoryBase) {
		super(subscribeOnScheduler, observeOnScheduler);
		this.postRepository = repositoryBase;
	}

	@Override
	protected Observable<List<Post>> provideUseCaseObservable(String userId) {
		final Criteria criteria = new UserIdCriteria(userId);
		return Observable.create(new Observable.OnSubscribe<List<Post>>() {
			@Override
			public void call(Subscriber<? super List<Post>> subscriber) {
				try {
					subscriber.onNext(Stream.stream(postRepository.read(criteria)).toList());
				} catch (DataSourceException e) {
					subscriber.onError(e);
				} finally {
					subscriber.onCompleted();
				}
			}
		});
	}
}
