package demo.presentation;

/**
 * Created on 08.11.2015.
 */
public interface Stateful<State> {

	void setState(State state);

	State getState();

}
