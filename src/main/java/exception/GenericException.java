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
            try {
                PopupFactory.getPopup().show(this.getMessage());
            } catch (SystemErrorException e) {
                System.out.println("system error");
            }
        }

        public void showException(Throwable cause){
            try {
                PopupFactory.getPopup().show(this.getMessage() + cause.getMessage());
            } catch (SystemErrorException e) {
                System.out.println("system error");
            }
        }

}
