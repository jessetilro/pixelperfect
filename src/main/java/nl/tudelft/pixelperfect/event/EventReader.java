package nl.tudelft.pixelperfect.event;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import nl.tudelft.pixelperfect.game.Constants;

/**
 * Reads the parameters of the different types of events from the file system or a given String.
 * Makes the data accessible to use as parameters to build Events with.
 * 
 * @author Jesse Tilro
 *
 */
public final class EventReader {

  private static volatile EventReader instance;
  private int id;
  private JSONArray data;

  /**
   * Creates a new EventReader instance if it has not yet been instantiated.
   * 
   * @return The single EventReader instance.
   */
  public static EventReader getInstance() {
    if (instance == null) {
      synchronized (EventReader.class) {
        instance = new EventReader();
      }
    }
    return instance;
  }

  private EventReader() {
    id = 1;
    readFromFile(Constants.EVENT_DATA_FILE);
  }

  /**
   * Clear the cache of data. Since this class is a Singleton, this method proves useful for example
   * when testing, where the object's state must not be maintained throughout the test cases.
   */
  public void clear() {
    data = null;
  }

  /**
   * Read and parse the JSON from the file with the specified path to use as the Event type
   * register.
   * 
   * @param path
   *          The path to the file to read from.
   * @return Whether the process of reading and parsing succeeded.
   */
  public boolean readFromFile(String path) {
    File file = new File(path);
    try {
      InputStream stream = new FileInputStream(file);
      JSONTokener tokener = new JSONTokener(stream);
      data = new JSONArray(tokener);
      return true;
    } catch (FileNotFoundException error) {
      return false;
    }
  }

  /**
   * Read and parse the JSON from given string to use as the Event type register.
   * 
   * @param json
   *          The JSON encoded string representing the array of Event types.
   */
  public void readFromString(String json) {
    data = new JSONArray(json);
  }

  /**
   * Get a JSONObject representing the Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier of the Event type.
   * @return A JSONObject representing the Event type.
   */
  private JSONObject getByType(int type) {
    if (data != null) {
      for (int i = 0; i < data.length(); i++) {
        JSONObject obj = data.getJSONObject(i);
        int id = obj.getInt("type");
        if (id == type) {
          return obj;
        }
      }
    }
    return null;
  }

  /**
   * Check whether the Reader has read about an Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier.
   * @return Whether an Event type with the specified identifier exists.
   */
  public boolean has(int type) {
    return (getByType(type) != null);
  }

  /**
   * Get the specified String attribute from the type with the specified type identifier.
   * 
   * @param type
   *          The type identifier.
   */
  private String getString(int type, String name) {
    JSONObject obj = getByType(type);
    if (obj != null) {
      return obj.getString(name);
    }
    return null;
  }

  /**
   * Get the summary attribute of the Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier
   * @return The Event type's summary.
   */
  public String getSummary(int type) {
    return getString(type, "summary");
  }

  /**
   * Get the description attribute of the Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier
   * @return The Event type's description.
   */
  public String getDescription(int type) {
    return getString(type, "description");
  }

  /**
   * Get the duration attribute of the Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier
   * @return The Event type's duration.
   */
  public long getDuration(int type) {
    JSONObject obj = getByType(type);
    if (obj != null) {
      return obj.getLong("duration");
    }
    return 0;
  }

  /**
   * Get the damage attribute of the Event type with the specified type identifier.
   * 
   * @param type
   *          The type identifier
   * @return The Event type's damage.
   */
  public double getDamage(int type) {
    JSONObject obj = getByType(type);
    if (obj != null) {
      return obj.getDouble("damage");
    }
    return 0;
  }

  /**
   * Get a map of associations between parameter keys and their summary for a specific type of
   * Event.
   * 
   * @param type
   *          The numeric Event Type identifier.
   * @return A Map of parameter key / summary associations.
   */
  public Map<String, String> getParameters(int type) {
    Map<String, String> map = new HashMap<String, String>();
    JSONObject obj = getByType(type);
    if (obj != null) {
      JSONArray parameters = obj.optJSONArray("parameters");
      if (parameters != null) {
        int index = 0;
        while (index >= 0) {
          JSONObject object = parameters.optJSONObject(index);
          if (object != null) {
            map.put(object.getString("key"), object.getString("summary"));
            ++index;
          } else {
            index = -1;
          }
        }
      }
    }
    return map;
  }

  /**
   * Retrieves the string description of the value of a certain parameter.
   * 
   * @param type
   *          , the type the parameter belongs to.
   * @param parameter
   *          , the parameter of which a value must be represented.
   * @param value
   *          , the value of the parameter. Can be different kind of values. I represented as
   *          integer and has a string description.
   * @return the String description of the value.
   */
  public String getParameterValue(int type, int parameter, int value) {
    JSONObject obj = getByType(type);
    if (obj != null) {
      JSONArray parameters = obj.optJSONArray("parameters");
      if (parameters != null) {
        int index = 0;
        while (index >= 0) {
          if (index == parameter) {
            JSONObject object = parameters.optJSONObject(index);
            if (object != null) {
              JSONArray values = object.optJSONArray("values");
              if (values != null) {
                if (values.optJSONObject(value) != null) {
                  return values.optJSONObject(value).getString("description");
                }
              }
            }
            return "";
          }
          index++;
        }
      }
    }
    return "";
  }

  /**
   * Since this is a Singleton, it is the perfect place to keep track of the unique numeric
   * identifier for Events.
   * 
   * @return The next Event id.
   */
  public int getNextId() {
    return id++;
  }

}
