/*
 * Copyright 2008-2011 Nokia Siemens Networks Oyj
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

package org.robotframework.swing.chooser;

import java.awt.Component;

import org.jretrofit.Retrofit;
import org.netbeans.jemmy.ComponentChooser;
import org.robotframework.swing.util.ObjectUtils;

public class ByTextComponentChooser implements ComponentChooser {
    private final String expectedText;

    public ByTextComponentChooser(String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    public boolean checkComponent(Component component) {
        WithText withText = Retrofit.partial(component,
                WithText.class);
        return ObjectUtils.nullSafeEquals(expectedText, withText.getText());
    }

    @Override
    public String getDescription() {
        return expectedText;
    }
}
