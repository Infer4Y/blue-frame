package inferno.blue_frame.client.audio;

import inferno.blue_frame.client.assets.ResourceLocation;
import org.lwjgl.openal.*;

import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.ALC10.*;

public class AudioMaster {
    public static Sound HIT;
    public static Sound BLOCK_BREAK;
    public static Sound COIN;
    public static Sound TELEPORT_OUT;
    public static Sound TELEPORT_IN;




    static String defaultDeviceName;
    static long   device;

    static int[] attributes;
    static long  context;

    static ALCCapabilities alcCapabilities;
    static ALCapabilities  alCapabilities;


    public static void init() {
        defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        device            = alcOpenDevice(defaultDeviceName);
        attributes = new int[]{0};
        context    = alcCreateContext(device, attributes);

        alcMakeContextCurrent(context);

        alcCapabilities = ALC.createCapabilities(device);
        alCapabilities  = AL.createCapabilities(alcCapabilities);

        loadSounds();
    }

    public static void setListenerData(){
        AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
    }

    public static void loadSounds(){
        HIT = new Sound(new ResourceLocation("sounds/hit.wav"));
        BLOCK_BREAK = new Sound(new ResourceLocation("sounds/block_break.wav"));
        COIN = new Sound(new ResourceLocation("sounds/hit.wav"));
        TELEPORT_OUT = new Sound(new ResourceLocation("sounds/teleport.wav"));
        TELEPORT_IN = new Sound(new ResourceLocation("sounds/in_teleport.wav"));
    }

    public static void cleanUp(){
        HIT.dispose();
        BLOCK_BREAK.dispose();
        COIN.dispose();
        TELEPORT_IN.dispose();
        TELEPORT_OUT.dispose();

        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
