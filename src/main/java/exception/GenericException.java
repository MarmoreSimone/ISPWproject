package exception;

import graphicalcontrollers.popup.PopupFactory;

public class GenericException extends Exception{

        public GenericException(String msg){
            super(msg);
        }

        public void showException(String msg){
            PopupFactory.getPopup().show(msg);
        }
}
