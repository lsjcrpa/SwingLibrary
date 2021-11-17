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

package org.robotframework.swing.arguments;

/**
 * 负责awt和swing的参数转换
 * @param <T>
 */
public class ArgumentParser<T> {
    private ArgumentHandler<T> argumentHandler;

    public ArgumentParser() { }
    public ArgumentParser(ArgumentHandler<T> argumentHandler) {
        this.argumentHandler = argumentHandler;
    }

    public void setArgumentHandler(ArgumentHandler<T> argumentHandler) {
        this.argumentHandler = argumentHandler;
    }

    public T parseArgument(String argument) {
        if (argument.startsWith("awt="))
            return parseAWTArgument(argument);
        else
            return parseSwingArgument(argument);
    }

    private T parseAWTArgument(String argument) {
        argument = argument.substring(4);
        try {
            return argumentHandler.indexAWTArgument(Integer.parseInt(argument));
        } catch(NumberFormatException e) {
            return argumentHandler.nameAWTArgument(argument.toString());
        }
    }

    private T parseSwingArgument(String argument) {
        try {
            return argumentHandler.indexArgument(Integer.parseInt(argument));
        } catch(NumberFormatException e) {
            return argumentHandler.nameArgument(argument.toString());
        }
    }
}
