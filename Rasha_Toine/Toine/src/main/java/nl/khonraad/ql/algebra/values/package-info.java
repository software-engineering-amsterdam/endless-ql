package nl.khonraad.ql.algebra.values;

/**
 * <h1>Immutable values</h1>
 * <p>
 * Classes and interface implementing immutable valuations of
 * {@link nl.khonraad.ql.ast.data.Question} in <b>Question Language</b>.
 *
 * <p>
 * The {@link ImmutableValue} class uses layering and a "has-a"
 * relationship with the {@link MutableValue} class.
 * <p>
 * Creating a an {@link ImmutableValue} inherently creates a
 * {@link #MutableValue}
 * object. That object however, is inaccessible for users of the
 * {@link ImmutableValue} object.
 * 
 * In <b>Question</b>
 * The {@link MutableValue} id fully owned by a {@link ImmutableValue}.
 * Therefore this class has only package visability.
 * 
 * @author a.khonraad
 */