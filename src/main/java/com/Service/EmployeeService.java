package com.Service;


import com.Repository.BiblioDAO;

public class EmployeeService {
    private BiblioDAO DB;

    public EmployeeService(BiblioDAO DBEmploye) {
        this.DB = DBEmploye;
    }

}
