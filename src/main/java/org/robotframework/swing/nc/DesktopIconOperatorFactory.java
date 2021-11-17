package org.robotframework.swing.nc;

import org.netbeans.jemmy.operators.ContainerOperator;
import org.robotframework.swing.chooser.ByNameOrTextComponentChooser;
import org.robotframework.swing.context.Context;
import org.robotframework.swing.factory.DefaultContextVerifyingOperatorFactory;

public class DesktopIconOperatorFactory extends DefaultContextVerifyingOperatorFactory<DesktopIconOperator>{
    @Override
    public DesktopIconOperator createOperatorByName(String name) {
        return new DesktopIconOperator((ContainerOperator) Context.getContext(), new ByNameOrTextComponentChooser(name));
    }

    @Override
    public DesktopIconOperator createOperatorByIndex(int index) {
        return new DesktopIconOperator((ContainerOperator) Context.getContext(), index);
    }
}
