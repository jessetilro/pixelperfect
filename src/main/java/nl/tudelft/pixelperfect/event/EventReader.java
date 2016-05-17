package nl.tudelft.pixelperfect.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Reads the parameters of the different types of events from the file system or a given String.
 * Makes the data accessible to use as parameters to build Events with.
 * 
 * @author Jesse Tilro
 *
 */
public class EventReader {

  private static volatile EventReader instance;

  private JSONArray types;

  /**
   * Creates a new EventReader instance if it has not yet been instantiated.
   * 
   * @return The single EventReader instance.
   */
  public static EventReader getInstance() {
    if (instance == null) {
      synchronized (EventReader.class) {
        if (instance == null) {
          instance = new EventReader();
        }
      }
    }
    return instance;
  }

  private EventReader() {
  }

  /**
   * Read and parse the JSON from the file with the specified path to use as the Event type
   * register.
   * 
   * @param path
   *          The path to the file to read from.
   * @return Whether the process of reading and parsing succeeded.
   */
  public boolean readTypesFromFile(String path) {
    File file = new File(path);
    try {
      InputStream stream = new FileInputStream(file);
      JSONTokener tokener = new JSONTokener(stream);
      types = new JSONArray(tokener);
      return true;
    } catch (FileNotFoundException e) {
      return false;
    }
  }

  /**
   * Read and parse the JSON from given string to use as the Event type register.
   * 
   * @param json
   *          The JSON encoded string representing the array of Event types.
   */
  public void readTypesFromString(String json) {
    types = new JSONArray(json);
  }

  /**
   * Get a JSONObject representing the Event type with the specified id.
   * 
   * @param id
   *          The id of the Event type.
   * @return A JSONObject representing the Event type.
   */
  private JSONObject getTypeById(int id) {
    if (types != null) {
      for (int i = 0; i < types.length(); i++) {
        JSONObject obj = types.getJSONObject(i);
        int thisId = obj.getInt("id");
        if (thisId == id) {
          return obj;
        }
      }
    }
    return null;
  }

  /**
   * Get the specified String attribute from the type with the specified id.
   * 
   * @param id
   *          The type id.
   */
  private String getString(int id, String name) {
    JSONObject obj = getTypeById(id);
    if (obj != null) {
      return obj.getString(name);
    }
    return null;
  }

  /**
   * Get the description attribute of the Event type with the specified id.
   * 
   * @param id
   * @return
   */
  public String getDescription(int id) {
    return getString(id, "description");
  }

}
