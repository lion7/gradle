/*
 * Copyright 2014 the original author or authors.
 *
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
 */

package org.gradle.model.internal.manage.schema.extract;

import org.gradle.api.Nullable;
import org.gradle.model.internal.core.ModelType;

public class ModelSchemaExtractionContext<T> {

    private final ModelSchemaExtractionContext<?> parent;
    private final ModelType<T> type;
    private final String description;

    private ModelSchemaExtractionContext(ModelSchemaExtractionContext<?> parent, ModelType<T> type, String description) {
        this.parent = parent;
        this.type = type;
        this.description = description;
    }

    public static <T> ModelSchemaExtractionContext<T> root(ModelType<T> type) {
        return new ModelSchemaExtractionContext<T>(null, type, null);
    }

    /**
     * null if this is the root of the extraction
     */
    @Nullable
    public ModelSchemaExtractionContext<?> getParent() {
        return parent;
    }

    public ModelType<T> getType() {
        return type;
    }

    public String getDescription() {
        return description == null ? type.toString() : String.format("%s (%s)", description, type);
    }

    public <C> ModelSchemaExtractionContext<C> child(ModelType<C> type, String description) {
        return new ModelSchemaExtractionContext<C>(this, type, description);
    }
}
