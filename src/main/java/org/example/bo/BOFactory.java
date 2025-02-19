package org.example.bo;


import org.example.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        ROOMTYPE,LOGIN, INVENTORY, EMPLOYEE, DISCOUNT, CHECKINDETAIL, GUEST, ROOM,BILL, RESERVATION, SERVICE
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case LOGIN:
                return new LoginBOImpl();
            case ROOMTYPE:
                return new RoomTypeBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case DISCOUNT:
                return new DiscountBOImpl();
            case CHECKINDETAIL:
                return new CheckInDetailBOImpl();
            case GUEST:
                return new GuestBOImpl();
            case BILL:
                return new BillBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
