# ESP32 CSI Serial for Android


`File > New > Import Module > Select this directory`

In your project perform the following tasks:

## `build.gradle`

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" } // <- Add this line
    }
}
```

## `app/build.gradle`

```
android {
    ...
    compileOptions {
        encoding "UTF-8"
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation project(path: ':ESP32CSISerial')
}
```

## `AndroidManifest.xml`
```

<manifest>
<uses-permission android:name="android.permission.INTERNET"/>                       // <- Add this
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>           // <- Add this
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>         // <- Add this
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>          // <- Add this

. . .

<application>
. . .
<service android:name="com.stevenmhernandez.esp32csiserial.UsbService" />           // <- Add this
</application>
<manifest>
```

## `settings.gradle`

```
include ':app', ':ESP32CSISerial'
```

## Attach to an activity

In your MainActivity (or any activity you choose)

```
    private ESP32CSISerial csiSerial = new ESP32CSISerial();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        . . .
        csiSerial.setup(this, "your_project_name");
        csiSerial.onCreate(this);
    }

    @Override
    protected void onResume() {
        . . .
        csiSerial.onResume(this);
    }

    @Override
    protected void onPause() {
        . . .
        csiSerial.onPause(this);
    }
```


add the interface to your activity:

```

public class MainActivity extends AppCompatActivity implements CSIDataInterface {

    . . .

    int csiCounter = 0;
    @Override
    public void addCsi(String csi_string) {
        csiCounter++;
        homeFragment.homeViewModel.setText(String.valueOf(csiCounter));
    }
}
```
