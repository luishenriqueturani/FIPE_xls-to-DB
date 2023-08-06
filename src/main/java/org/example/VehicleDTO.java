package org.example;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class VehicleDTO {

  private Connection conn;

  public VehicleDTO(Connection conn) {
    this.conn = conn;
  }

  public boolean insert(Vehicle vehicle){
    if(vehicle == null) return false;

    if(vehicle.getCategory() == null) return false;

    if(vehicle.getBrand() == null) return false;

    if(vehicle.getModel() == null) return false;

    CRUD crud = new CRUD(this.conn);

    //busca a categoria
    List<Vehicle> categories = crud.getCategories(vehicle.getCategory());

    if(categories == null) return false;

    String idCategory;
    //se encontrar a categoria usa ela, senão insere uma nova
    if(categories.isEmpty()){
      UUID insertedCategory = crud.insert(
          "categories",
          new String[]{"label"},
          new String[]{vehicle.getCategory()}
      );

      if(insertedCategory == null) return false;

      idCategory = insertedCategory.toString();
    }else{
      idCategory = categories.get(0).getId();
    }
    categories = null;

    //busca vehicles por marca
    List<Vehicle> brands = crud.getBrands(vehicle.getBrand());

    if(brands == null) return false;

    String idBrand;
    //if retornar um resultado usa o resultado recebido, senão insere a marca

    if(brands.isEmpty()){
      UUID insertedBrand = crud.insert(
          "brands",
          new String[]{"label"},
          new String[]{vehicle.getBrand()}
      );

      if(insertedBrand == null) return false;

      idBrand = insertedBrand.toString();
    }else {
      idBrand = brands.get(0).getId();
    }
    brands = null;

    //busca o modelo
    List<Vehicle> models = crud.getModels(vehicle.getModel(), idBrand);

    if(models == null) return false;

    String idModel;
    //se retornar um resultado usa o resultado, senão insere um novo
    if(models.isEmpty()){
      UUID insertedModel = crud.insert(
          "models",
          new String[]{"label", "id_brand", "id_category"},
          new String[]{vehicle.getModel(), idBrand, idCategory}
      );

      if(insertedModel == null) return false;

      idModel = insertedModel.toString();
    }else {
      idModel = models.get(0).getId();
    }
    models = null;

    //insere a versão
    UUID insertedVersion = crud.insert(
        "versions",
        new String[]{"label", "id_model"},
        new String[]{vehicle.getVersion(), idModel}
    );

    if(insertedVersion == null) return false;

    String idVersion = insertedVersion.toString();

    //percorre os anos modelos e os salva
    for (YearPrice yp: vehicle.getYearPrices() ) {
      UUID insertedYearPrice = crud.insert(
          "year_versions_prices",
          new String[]{"id_version", "price", "year", "cod_fipe"},
          new String[]{idVersion, String.valueOf(yp.getPrice()), String.valueOf(yp.getYear()), vehicle.getCodFipe()}
      );
    }

    //retorna true ou false
    return true;
  }



}
