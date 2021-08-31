package computer.brands.Apple;

import computer.brands.Models;

import java.util.ArrayList;

public class AppleModel extends Models {

    private int modelYear;
    private AppleModels modelType;
    private static ArrayList<AppleModel> models = new ArrayList<AppleModel>();

    public AppleModel(AppleModels model, int modelYear){
        try {
            setModelType(model);
            setModelYear(modelYear);
            addModel(this);
        } catch (Exception e){
            e.getMessage();
        }
    }

    private void setModelYear(int year) throws Exception {
        if(year > 2009 && year < 2020){
            this.modelYear = year;
        } else{
            throw new Exception("Invalid year.");
        }
    }

    private void setModelType(AppleModels model){
        this.modelType = model;
    }

    private void addModel(AppleModel model) throws Exception{
        if(models.contains(model)){
            throw new Exception("Model exits.");
        } else{
            models.add(model);
        }
    }

    public ArrayList<AppleModel> getModels(){
        return models;
    }
}
