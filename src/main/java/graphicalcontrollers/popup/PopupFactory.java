package graphicalcontrollers.popup;

public class PopupFactory {

    private static Popup me = null;

    protected PopupFactory() {
    }

    public static synchronized void setfactory(int scelta){
        if ( me == null ){

            switch (scelta) {
                case 1 :
                    me = new PopupGUI();
                    break;
                case 2 :
                    me = new PopupCLI();
                    break;

                default:
            }
        }
    }

    public static synchronized Popup getPopup(){
        return me;
    }


}
