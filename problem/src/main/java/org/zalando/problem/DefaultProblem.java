package org.zalando.problem;

/*
 * ⁣​
 * Problem
 * ⁣⁣
 * Copyright (C) 2015 Zalando SE
 * ⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ​⁣
 */

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.ImmutableMap;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.annotation.remark.OhNoYouDidnt;

import javax.annotation.concurrent.Immutable;
import javax.ws.rs.core.Response.StatusType;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Immutable // TODO kind of a lie until we remove set(String, Object)
public final class DefaultProblem extends ThrowableProblem {

    private final URI type;
    private final String title;
    private final StatusType status;
    private final Optional<String> detail;
    private final Optional<URI> instance;
    private final Map<String, Object> parameters = new HashMap<>();

    DefaultProblem(final URI type,
            final String title,
            final StatusType status,
            final Optional<String> detail,
            final Optional<URI> instance) {
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.detail = Objects.requireNonNull(detail, "detail must not be null");
        this.instance = Objects.requireNonNull(instance, "instance must not be null");
    }

    @Override
    public URI getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public StatusType getStatus() {
        return status;
    }

    @Override
    public Optional<String> getDetail() {
        return detail;
    }

    @Override
    public Optional<URI> getInstance() {
        return instance;
    }

    public ImmutableMap<String, Object> getParameters() {
        return ImmutableMap.copyOf(parameters);
    }

    /**
     * This is required to workaround missing support for {@link com.fasterxml.jackson.annotation.JsonAnySetter} on
     * constructors annotated with {@link com.fasterxml.jackson.annotation.JsonCreator}.
     *
     * @param key   the custom key
     * @param value the custom value
     * @see <a href="https://github.com/FasterXML/jackson-databind/issues/562">Jackson Issue 562</a>
     */
    @Hack
    @OhNoYouDidnt
    void set(final String key, final Object value) {
        parameters.put(key, value);
    }

    /**
     * Specification by example:
     * <p>
     * <pre>{@code
     *   // Returns "http://httpstatus.es/404{}"
     *   Problem.create(NOT_FOUND).toString();
     * <p>
     *   // Returns "http://httpstatus.es/404{Order 123}"
     *   Problem.create(NOT_FOUND, "Order 123").toString();
     * <p>
     *   // Returns "http://httpstatus.es/404{Order 123, instance=https://example.org/"}
     *   Problem.create(NOT_FOUND, "Order 123", URI.create("https://example.org/").toString();
     * }</pre>
     *
     * @return a string representation of this problem
     */
    @Override
    public String toString() {
        final ToStringHelper helper = MoreObjects.toStringHelper(type.toString())
                .omitNullValues()
                .addValue(detail.orElse(null))
                .add("instance", instance.orElse(null));

        parameters.forEach(helper::add);

        return helper.toString();
    }

}
