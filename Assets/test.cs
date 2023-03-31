
using UnityEngine;

public class test : MonoBehaviour
{
    private AndroidJavaObject ajo;
    
    // Start is called before the first frame update
    void Start()
    {
        ajo = new AndroidJavaObject("com.unity3d.player.testPlugin");
    }

    public void Call(string callMethod)
    {
        ajo.Call(callMethod);
    }
}
