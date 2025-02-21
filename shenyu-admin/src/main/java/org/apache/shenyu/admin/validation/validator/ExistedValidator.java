/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.validation.validator;

import org.apache.shenyu.admin.exception.ResourceNotFoundException;
import org.apache.shenyu.admin.spring.SpringBeanUtils;
import org.apache.shenyu.admin.validation.ExistProvider;
import org.apache.shenyu.admin.validation.annotation.Existed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ExistedValidator.
 */
public class ExistedValidator implements ConstraintValidator<Existed, Serializable> {
    
    /**
     * target annotation.
     */
    private Existed annotation;
    
    /**
     * provider cache.
     */
    private final Map<String, ExistProvider> providerCacheMap = new ConcurrentHashMap<>();
    
    @Override
    public void initialize(final Existed constraintAnnotation) {
        annotation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(final Serializable value, final ConstraintValidatorContext context) {
        if (Objects.isNull(annotation.provider())) {
            throw new ResourceNotFoundException("the validation ExistProvider is not found");
        }
        return Boolean.TRUE.equals(getExistProvider().existed(value));
    }
    
    private ExistProvider getExistProvider() {
        ExistProvider provider = providerCacheMap.get(annotation.provider().getName());
        if (!Objects.isNull(provider)) {
            return provider;
        }
        provider = SpringBeanUtils.getInstance().getBean(annotation.provider());
        providerCacheMap.put(annotation.provider().getName(), provider);
        return provider;
    }
}
