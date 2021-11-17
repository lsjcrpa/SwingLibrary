package org.robotframework.swing.keyword.desktopcall;

import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.swing.nc.DesktopIconOperator;
import org.robotframework.swing.nc.DesktopIconOperatorFactory;
import org.robotframework.swing.factory.IdentifierParsingOperatorFactory;

@RobotKeywords
public class DesktopIconKeywords {
    private IdentifierParsingOperatorFactory<DesktopIconOperator> operatorFactory=new DesktopIconOperatorFactory();


    @RobotKeyword("Uses current context to search for a DesktopCall and when found, double click it.\n\n"
            + "Example:\n"
            + "| `Push Desktop Icon Button` | OK |\n")
    @ArgumentNames({"identifier"})
    public void pushDesktopIconButton(String identifier) {
         createOperator(identifier).doubleClick();
    }
    private DesktopIconOperator createOperator(String identifier){
        return operatorFactory.createOperator(identifier);
    }



}
