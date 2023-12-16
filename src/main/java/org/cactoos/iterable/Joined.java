/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.iterable;

import java.util.Iterator;

/**
 * A few Iterables joined together.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <T> Type of item
 * @since 0.1
 */
public final class Joined<T> extends IterableEnvelope<T> {

    /**
     * Ctor.
     * @param items Items to concatenate
     */
    @SafeVarargs
    public Joined(final Iterable<? extends T>... items) {
        this(new IterableOf<>(items));
    }

    /**
     * Ctor.
     * @param item First item
     * @param items Iterable
     * @since 0.32
     */
    @SuppressWarnings("unchecked")
    public Joined(final T item, final Iterable<? extends T> items) {
        this(new IterableOf<>(new IterableOf<>(item), items));
    }

    /**
     * Ctor.
     * @param items Items to concatenate
     */
    public Joined(final Iterable<? extends Iterable<? extends T>> items) {
        super(
            new IterableOf<>(
                () -> new org.cactoos.iterator.Joined<>(
                    new Mapped<Iterator<? extends T>>(Iterable::iterator, items)
                )
            )
        );
    }

    /**
     * Ctor.
     * @param item Item to be appended to end of Iterable
     * @param items Iterable
     * @since 0.32
     */
    @SuppressWarnings("unchecked")
    public Joined(final Iterable<? extends T> items, final T... item) {
        this(new IterableOf<>(items, new IterableOf<>(item)));
    }
}
