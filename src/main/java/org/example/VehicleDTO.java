package org.example;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class VehicleDTO {

  private Connection conn;

  public VehicleDTO(Connection conn) {
    this.conn = conn;
  }

  public int insert(Vehicle vehicle){
    if(vehicle == null) return -1;

    if(vehicle.getCategory() == null) return -2;

    if(vehicle.getBrand() == null) return -3;

    if(vehicle.getModel() == null) return -4;

    CRUD crud = new CRUD(this.conn);

    //busca a categoria
    List<Vehicle> categories = crud.getCategories(vehicle.getCategory());

    String idCategory;
    //se encontrar a categoria usa ela, senão insere uma nova
    if(categories.isEmpty()){
      //idCategory = crud.insert(
      String insertedCategory = crud.insert(
          "categories",
          new String[]{"label"},
          new String[]{vehicle.getCategory()}
      );

      if(insertedCategory == null){
        insertedCategory = crud.getCategories( vehicle.getCategory() ).get(0).getId();

        if(insertedCategory == null) return -6;
      }

      idCategory = insertedCategory;
    }else{
      idCategory = categories.get(0).getId();
    }
    categories = null;

    //busca vehicles por marca
    List<Vehicle> brands = crud.getBrands(vehicle.getBrand());

    String idBrand;
    //if retornar um resultado usa o resultado recebido, senão insere a marca

    if(brands.isEmpty()){
      //idBrand = crud.insert(
      String insertedBrand = crud.insert(
          "brands",
          new String[]{"label"},
          new String[]{vehicle.getBrand()}
      );

      if(insertedBrand == null){
        insertedBrand = crud.getBrands( vehicle.getBrand() ).get(0).getId();

        if(insertedBrand == null) return -8;
      }

      idBrand = insertedBrand;
    }else {
      idBrand = brands.get(0).getId();
    }
    brands = null;

    //busca o modelo
    List<Vehicle> models = crud.getModels(vehicle.getModel(), idBrand);

    String idModel;
    //se retornar um resultado usa o resultado, senão insere um novo
    if(models.isEmpty()){
      //idModel = crud.insert(
      String insertedModel = crud.insert(
          "models",
          new String[]{"label", "id_brand", "id_category"},
          new String[]{vehicle.getModel(), idBrand, idCategory}
      );

      if(insertedModel == null){
        insertedModel = crud.getModels(vehicle.getModel(), idBrand).get(0).getId();

        if(insertedModel == null) return -10;
      }

      idModel = insertedModel;
    }else {
      idModel = models.get(0).getId();
    }
    models = null;

    //insere a versão
    String idVersion = crud.insert(
        "versions",
        new String[]{"label", "id_model"},
        new String[]{vehicle.getVersion(), idModel}
    );

    if(idVersion == null){
      idVersion = crud.getVersion(vehicle.getVersion(), idModel).get(0).getId();

      if(idVersion == null) return -11;
    }

    //percorre os anos modelos e os salva
    for (YearPrice yp: vehicle.getYearPrices() ) {
      String insertedYearPrice = crud.insert(
          "year_versions_prices",
          new String[]{"id_version", "price", "year", "cod_fipe"},
          new String[]{idVersion, String.valueOf(yp.getPrice()), String.valueOf(yp.getYear()), vehicle.getCodFipe()}
      );
    }

    //retorna true ou false
    return 0;
  }



}
