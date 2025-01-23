package exception;

import graphicalcontrollers.popup.PopupFactory;

public class GenericException extends Exception{

        public GenericException(String msg){
            super(msg);
        }

        public GenericException(String msg, Throwable cause){
            super(msg);
        }

        public void showException(){
            PopupFactory.getPopup().show(this.getMessage());
        }

        public void showException(Throwable cause){
            PopupFactory.getPopup().show(this.getMessage() + cause.getMessage());
        }

}
