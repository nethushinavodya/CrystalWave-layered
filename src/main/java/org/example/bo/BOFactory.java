package org.example.bo;


import org.example.bo.custom.impl.LoginBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        LOGIN
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }
}
