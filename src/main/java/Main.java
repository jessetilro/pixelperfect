/*

VR Instancing Progress:
- igByGeom gets huge, and updateInstances still happens on it,
  even if the associated geometry is no longer in the scene
  - track which InstanceGeometry to add or remove inside the Geometry?
  - have list of instances to render maintained somewhere else?

*/

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import jmevr.app.VRApplication;
import jmevr.input.OpenVR;
import jmevr.input.VRBounds;
import jmevr.input.VRInput;
import jmevr.input.VRInput.VRINPUT_TYPE;
import jmevr.post.CartoonSSAO;
import jmevr.util.VRGuiManager;
import jmevr.util.VRGuiManager.POSITIONING_MODE;

/**
 *
 * @author reden
 */
public class Main extends VRApplication {

  // set some VR settings & start the app
  public static void main(String[] args){
    Main test = new Main();
    //test.preconfigureVRApp(PRECONFIG_PARAMETER.USE_STEAMVR_COMPOSITOR, false); // disable the SteamVR compositor (kinda needed at the moment)
    test.preconfigureVRApp(PRECONFIG_PARAMETER.USE_CUSTOM_DISTORTION, false); // use full screen distortion, maximum FOV, possibly quicker (not compatible with instancing)
    test.preconfigureVRApp(PRECONFIG_PARAMETER.ENABLE_MIRROR_WINDOW, false); // runs faster when set to false, but will allow mirroring
    test.preconfigureVRApp(PRECONFIG_PARAMETER.FORCE_VR_MODE, false); // render two eyes, regardless of SteamVR
    test.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_CURVED_SURFACE, true);
    test.preconfigureVRApp(PRECONFIG_PARAMETER.FLIP_EYES, false);
    test.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_OVERDRAW, true); // show gui even if it is behind things
    test.preconfigureVRApp(PRECONFIG_PARAMETER.INSTANCE_VR_RENDERING, false); // faster VR rendering, requires some vertex shader changes (see jmevr/shaders/Unshaded.j3md)
    test.preconfigureVRApp(PRECONFIG_PARAMETER.NO_GUI, false);
    test.preconfigureFrustrumNearFar(0.1f, 512f); // set frustum distances here before app starts
    //test.preconfigureResolutionMultiplier(0.666f); // you can downsample for performance reasons
    test.start();
  }

  // general objects for scene management
  Node boxes = new Node("boxes");
  Spatial observer;
  boolean moveForward, moveBackwards, rotateLeft, rotateRight;
  Material mat;
  Geometry leftHand, rightHand;

  @Override
  public void simpleInitApp() {
//        initTestScene();

    // print out what device we have
    if( VRApplication.getVRHardware() != null ) {
      System.out.println("Attached device: " + VRApplication.getVRHardware().getName());
    }
  }

}