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
package org.cactoos.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.cactoos.iterable.IterableOf;

/**
 * Implementation of {@link List}.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <T> List type
 * @since 0.1
 */
public final class ListOf<T> extends ListEnvelope<T> {
    /**
     * Ctor.
     *
     * @param array An array of some elements
     */
    @SafeVarargs
    public ListOf(final T... array) {
        this(new IterableOf<>(array));
    }

    /**
     * Ctor.
     * @param src An {@link Iterator}
     * @since 0.21
     */
    public ListOf(final Iterator<? extends T> src) {
        this(new IterableOf<T>(src));
    }

    /**
     * Ctor.
     * @param src An {@link Iterable}
     */
    public ListOf(final Iterable<? extends T> src) {
        super(new LinkedList<>());
        src.forEach(super::add);
    }
}
