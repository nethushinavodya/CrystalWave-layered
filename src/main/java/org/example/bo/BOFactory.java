package org.example.bo;


import org.example.bo.custom.impl.LoginBOImpl;
import org.example.bo.custom.impl.RoomBOImpl;
import org.example.bo.custom.impl.RoomTypeBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        ROOMTYPE,LOGIN,ROOM
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case LOGIN:
                return new LoginBOImpl();
            case ROOMTYPE:
                return new RoomTypeBOImpl();
            case ROOM:
                return new RoomBOImpl();
            default:
                return null;
        }
    }
}
