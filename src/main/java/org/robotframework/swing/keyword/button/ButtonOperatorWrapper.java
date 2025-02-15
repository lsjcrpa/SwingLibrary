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

package org.robotframework.swing.keyword.button;

import org.netbeans.jemmy.drivers.DriverManager;
import org.robotframework.swing.button.ButtonOperator;

public class ButtonOperatorWrapper {
    private final ButtonOperator buttonOperator;

    public ButtonOperatorWrapper(ButtonOperator buttonOperator) {
        this.buttonOperator = buttonOperator;
    }

    public void push() {
        waitButtonToBeEnabled();
        pushButton();
    }

    private void waitButtonToBeEnabled() {
        try {
            buttonOperator.waitComponentEnabled();
        } catch (InterruptedException e) {
            throw new RuntimeException("Wait for button interrupted", e);
        }
    }

    private void pushButton() {
        DriverManager.getButtonDriver(buttonOperator).push(buttonOperator);
    }
}
