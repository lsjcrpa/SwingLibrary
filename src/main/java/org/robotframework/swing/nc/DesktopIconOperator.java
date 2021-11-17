package org.robotframework.swing.nc;

import com.dl.desktop.DesktopIcon;
import org.jretrofit.Retrofit;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.robotframework.swing.chooser.WithDoubleClicker;

import java.awt.*;


public class DesktopIconOperator extends JComponentOperator {

    public DesktopIconOperator(DesktopIcon b) {
        super(b);
    }

    public DesktopIconOperator(ContainerOperator cont, ComponentChooser chooser, int index) {
        this((DesktopIcon) cont.waitSubComponent(new DesktopIconOperator.DesktopIconFinder(chooser), index));
        copyEnvironment(cont);
    }

    public DesktopIconOperator(ContainerOperator cont, ComponentChooser chooser) {
        this(cont, chooser, 0);
    }

    public DesktopIconOperator(ContainerOperator cont, int index) {
        this((DesktopIcon)
                waitComponent(cont,
                        new DesktopIconOperator.DesktopIconFinder(),
                        index));
        copyEnvironment(cont);
    }

    public static DesktopIcon findDesktopIcon(Container cont, ComponentChooser chooser, int index) {
        return ((DesktopIcon) findComponent(cont, new DesktopIconOperator.DesktopIconFinder(chooser), index));
    }

    public static DesktopIcon findDesktopIcon(Container cont, ComponentChooser chooser) {
        return (findDesktopIcon(cont, chooser, 0));
    }

    public static DesktopIcon waitDesktopIcon(final Container cont, final ComponentChooser chooser, final int index) {
        return ((DesktopIcon) waitComponent(cont, new DesktopIconOperator.DesktopIconFinder(chooser), index));
    }

    public static DesktopIcon waitDesktopIcon(Container cont, ComponentChooser chooser) {
        return (waitDesktopIcon(cont, chooser, 0));
    }

    ////////////////////////////////////////////////
    //Mapping                                     //

    public void doubleClick() {
        try {
            WithDoubleClicker clicker = Retrofit.partial(getSource(), WithDoubleClicker.class);
            clicker.doDoubleClicked((Container) getSource());
        }catch (Exception e){
            throw e;
        }
    }
    public String getText() {
        return((String)runMapping(new MapAction("getText") {
            public Object map() {
                return(((DesktopIcon)getSource()).getText());
            }}));}

    //End of mapping                                      //
    ////////////////////////////////////////////////////////



    public static class DesktopIconFinder extends Finder {

        public DesktopIconFinder(ComponentChooser sf) {
            super(DesktopIcon.class, sf);
        }

        public DesktopIconFinder() {
            super(DesktopIcon.class);
        }
    }


}
