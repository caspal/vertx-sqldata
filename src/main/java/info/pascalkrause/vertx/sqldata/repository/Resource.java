package info.pascalkrause.vertx.sqldata.repository;

import io.vertx.core.json.JsonObject;

public interface Resource {

    /**
     * @return the name of the table which represents this resource.
     */
    String getTableName();

    /**
     * The id of the resource. It is used to implement the Get, Update and Delete functionality of SQLRepository.
     * 
     * @return the id of the resource.
     */
    Object getId();

    /**
     * Converts the resource from its JSON representation to its Java object representation.
     * 
     * @param o A JSON representation of this object.
     * @return A resource as Java object representation.
     */
    Resource fromJson(JsonObject o);

    /**
     * Converts the resource from its object representation to its JSON representation.
     * 
     * @return This resource as JSON representation.
     */
    JsonObject toJson();
}
