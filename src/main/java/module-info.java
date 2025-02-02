module Crystalwave.layered {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jbcrypt;
    requires static lombok;
    requires net.sf.jasperreports.core;

    exports org.example;
    exports org.example.bo;
    exports org.example.bo.custom;
    exports org.example.controller.HomeControllers;
    opens org.example.controller.HomeControllers to javafx.fxml;
    exports org.example.controller.AdminControllers;
    opens org.example.controller.AdminControllers to javafx.fxml;
    exports org.example.controller.ReceptionistControllers;
    opens org.example.controller.ReceptionistControllers to javafx.fxml;
    exports org.example.dao;
    exports org.example.dao.custom;
    exports org.example.entity;
    exports org.example.dto;
    exports org.example.dto.tm;
}