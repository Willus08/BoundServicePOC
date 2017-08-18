package posidenpalace.com.boundservicepoc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }


    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public String RandomStringMaker()
    {
        Random generator= new Random();
        StringBuilder stringBuilder= new StringBuilder();
        int randomLength= generator.nextInt(15)+1;
        char tempchar;

        for (int i = 0; i <randomLength ; i++) {

            tempchar= (char)(generator.nextInt(96)+32);
            stringBuilder.append(tempchar);
        }
        return stringBuilder.toString();
    }

    public List<String> getStrings(int n){
        List<String> randomStrings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randomStrings.add(RandomStringMaker());
        }
        return randomStrings;
    }
    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }

}
