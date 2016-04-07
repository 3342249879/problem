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

import com.google.common.annotations.VisibleForTesting;

import javax.ws.rs.core.Response.StatusType;
import java.net.URI;

final class GenericProblems {

    static final URI DEFAULT_TYPE = URI.create("about:blank");

    @VisibleForTesting
    GenericProblems() throws Exception {
        throw new IllegalAccessException();
    }

    static ProblemBuilder create(final StatusType status) {
        return Problem.builder()
                .withType(DEFAULT_TYPE)
                .withTitle(status.getReasonPhrase())
                .withStatus(status);
    }

}
