package inferno.blue_frame.client.audio;

import org.lwjgl.openal.AL10;

public class Source {

    private  int ID;

    public Source() {
        ID = AL10.alGenSources();
        AL10.alSourcef(ID, AL10.AL_GAIN, 1);
        AL10.alSourcef(ID, AL10.AL_PITCH, 1);
        AL10.alSource3f(ID, AL10.AL_POSITION, 0,0,0);
    }

    public void play(int buffer){
        AL10.alSourcei(ID, AL10.AL_BUFFER, buffer);
        AL10.alSourcePlay(ID);
    }

    public void delete(){
        AL10.alDeleteSources(ID);
    }
}
