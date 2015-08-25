package org.zalando.problem;

/*
 * ⁣​
 * Jackson-datatype-Problem
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

import com.google.common.collect.ImmutableMap;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.annotation.remark.OhNoYouDidnt;
import org.junit.Test;

import java.net.URI;

import static java.util.Optional.empty;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Hack
@OhNoYouDidnt
public final class EnforceCoverageTest {

    @Test(expected = DefaultProblem.class)
    public void foo() {
        new DefaultProblemMixIn(URI.create("http://httpstatus.es/400"), "Bad Request", BAD_REQUEST, empty(), empty()) {

            @Override
            void set(final String key, final Object value) {

            }

            @Override
            public ImmutableMap<String, Object> getParameters() {
                return null;
            }
        };
    }

}