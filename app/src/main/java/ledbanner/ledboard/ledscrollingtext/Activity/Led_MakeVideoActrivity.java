package ledbanner.ledboard.ledscrollingtext.Activity;

import static com.hbisoft.hbrecorder.Constants.MAX_FILE_SIZE_REACHED_ERROR;
import static com.hbisoft.hbrecorder.Constants.SETTINGS_ERROR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;

import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hbisoft.hbrecorder.HBRecorder;
import com.hbisoft.hbrecorder.HBRecorderListener;

import ledbanner.ledboard.ledscrollingtext.AppUtils.App;
import ledbanner.ledboard.ledscrollingtext.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Led_MakeVideoActrivity extends AppCompatActivity implements HBRecorderListener {
    public static final int SCREEN_RECORD_REQUEST_CODE = 777;
    static String filename, filename1;
    ValueAnimator animator;
    ContentValues contentValues;
    int counter = 0;
    String[] data = {"abeezee_italic.ttf", "aguafina_script.ttf", "alfa_slab_one.ttf", "alike_angular.ttf", "almendra_display.ttf", "andada.ttf", "annie_use_your_telescope.ttf", "bangers.ttf", "bungee_shade.ttf", "cinzel_decorative.ttf"};
    public boolean hasPermissions = false;

    public HBRecorder hbRecorder;
    boolean isAudioEnabled = false;
    Uri mUri;
    String path;
    String path1;
    ContentResolver resolver;
    ImageView save;
    RelativeLayout scrollView_showtext;
    TextView textView;
    long textspeed;
    boolean wasHDSelected = true;
    boolean isBlink;
    ImageView back;
    float mTextSize;
    private Animation anim;
    public static int arrayB;
    RelativeLayout relload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources();
        setContentView(R.layout.activity_led_make_video_actrivity);
        getWindow().setFlags(1024, 104);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        textView = findViewById(R.id.text_vedio);
        save = findViewById(R.id.save_vedio);
        relload = findViewById(R.id.relload);
        back = findViewById(R.id.back);
        scrollView_showtext = findViewById(R.id.scrollView_vedio);
        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("isBlink")) {
                blink();
            }
            mTextSize = savedInstanceState.getFloat("textsize");
        }
        arrayB = getIntent().getIntExtra("bg", 0);

        if (arrayB == 0) {
            scrollView_showtext.setBackgroundColor(getIntent().getIntExtra("textbackgroundcolor", 0));
        } else {
            scrollView_showtext.setBackgroundResource(arrayB);
        }
        textView.setTextSize(mTextSize);
        this.textspeed = getIntent().getLongExtra("duration", 0);
        int intExtra3 = getIntent().getIntExtra("textstyle", 0);
        AssetManager assets = getAssets();
        int intExtra4 = getIntent().getIntExtra("check", 0);
        textView.setText(getIntent().getStringExtra("marqueetext"));
        textView.setTextColor(getIntent().getIntExtra("textcolor", 0));
        textView.setTypeface(Typeface.createFromAsset(assets, "font/" + this.data[intExtra3]));

        if (intExtra4 == 0) {
            right_to_left();
        } else if (intExtra4 == 1) {
            left_to_right();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder = new HBRecorder(this, this);
        }
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FullScreencall();
                if (arrayB == 0) {
                    HistoyFolder();
                    back.setVisibility(View.GONE);
                    save.setVisibility(View.GONE);
                } else {
                    BannerFolder();
                    back.setVisibility(View.GONE);
                    save.setVisibility(View.GONE);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    startRecordingScreen();
                }
            }
        });
    }


    private void blink() {
        if (anim == null) {
            isBlink = true;
            anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            textView.startAnimation(anim);
        } else {
            isBlink = false;
            textView.clearAnimation();
            anim = null;
        }
    }

    public void HistoyFolder() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "/LED TEXT/");
        if (!file.exists() && file.mkdirs()) {
            Log.i("Folder ", "created");
        }
    }

    public void BannerFolder() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "/LED TEXT/Banner/");
        if (!file.exists() && file.mkdirs()) {
            Log.i("Banner ", "created");
        }
    }

    public void HBRecorderOnStart() {

    }

    public void HBRecorderOnComplete() {
        showLongToast("Saved Successfully");
        if (!this.hbRecorder.wasUriSet()) {
            refreshGalleryFile();
        } else if (Build.VERSION.SDK_INT >= 29) {
            updateGalleryUri();
        } else {
            refreshGalleryFile();
        }
    }

    public void refreshGalleryFile() {
        MediaScannerConnection.scanFile(this, new String[]{this.hbRecorder.getFilePath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                Log.i("ExternalStorage", "Scanned " + str + ":");
                StringBuilder sb = new StringBuilder();
                sb.append("-> uri=");
                sb.append(uri);
                Log.i("ExternalStorage", sb.toString());
            }
        });
    }

    public void updateGalleryUri() {
        this.contentValues.clear();
        this.contentValues.put("is_pending", 0);
        getContentResolver().update(this.mUri, this.contentValues, (String) null, (String[]) null);
    }

    public void HBRecorderOnError(int i, String str) {
        if (i == SETTINGS_ERROR) {
            showLongToast(getString(R.string.settings_not_supported_message));
        } else if (i == MAX_FILE_SIZE_REACHED_ERROR) {
            showLongToast(getString(R.string.max_file_size_reached_message));
        } else {
            showLongToast(getString(R.string.general_recording_error_message));

        }
    }

    public void startRecordingScreen() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT >= 28) {
            customSettings();
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            if (mediaProjectionManager != null) {
                intent = mediaProjectionManager.createScreenCaptureIntent();
            }
            startActivityForResult(intent, SCREEN_RECORD_REQUEST_CODE);
            return;
        }
        quickSettings();
        MediaProjectionManager mediaProjectionManager2 = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        if (mediaProjectionManager2 != null) {
            intent = mediaProjectionManager2.createScreenCaptureIntent();
        }
        startActivityForResult(intent, SCREEN_RECORD_REQUEST_CODE);
    }

    public void customSettings() {
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean z = defaultSharedPreferences.getBoolean("key_record_audio", true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder.isAudioEnabled(z);
        }
        defaultSharedPreferences.getString("key_audio_source", null);
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder.setAudioBitrate(128000);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder.setAudioSamplingRate(44100);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder.isAudioEnabled(this.isAudioEnabled);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.hbRecorder.setVideoEncoder("H264");
        }
        String string = defaultSharedPreferences.getString("key_video_encoder", null);
        if (string != null) {
            switch (string.hashCode()) {
                case 48:
                    if (string.equals("0")) {
                        c5 = 0;
                        break;
                    }
                    c5 = 65535;
                    break;
                case 49:
                    if (string.equals("1")) {
                        c5 = 1;
                        break;
                    }
                    c5 = 65535;
                    break;
                case 50:
                    if (string.equals("2")) {
                        c5 = 2;
                        break;
                    }
                    c5 = 65535;
                    break;
                case 51:
                    if (string.equals("3")) {
                        c5 = 3;
                        break;
                    }
                    c5 = 65535;
                    break;
                case 52:
                    if (string.equals("4")) {
                        c5 = 4;
                        break;
                    }
                    c5 = 65535;
                    break;
                case 53:
                    if (string.equals("5")) {
                        c5 = 5;
                        break;
                    }
                    c5 = 65535;
                    break;
                default:
                    c5 = 65535;
                    break;
            }
            if (c5 != 0) {
                if (c5 != 1) {
                    if (c5 != 2) {
                        if (c5 != 3) {
                            if (c5 != 4) {
                                if (c5 == 5 && Build.VERSION.SDK_INT >= 21) {
                                    this.hbRecorder.setVideoEncoder("VP8");
                                }
                            } else if (Build.VERSION.SDK_INT >= 21) {
                                this.hbRecorder.setVideoEncoder("MPEG_4_SP");
                            }
                        } else if (Build.VERSION.SDK_INT >= 21) {
                            this.hbRecorder.setVideoEncoder("HEVC");
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoEncoder("H263");
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    this.hbRecorder.setVideoEncoder("H264");
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.hbRecorder.setVideoEncoder("H264");
            }
        }
        String string2 = defaultSharedPreferences.getString("key_video_resolution", null);
        if (string2 != null) {
            switch (string2.hashCode()) {
                case 48:
                    if (string2.equals("0")) {
                        c4 = 0;
                        break;
                    }
                    c4 = 65535;
                    break;
                case 49:
                    if (string2.equals("1")) {
                        c4 = 1;
                        break;
                    }
                    c4 = 65535;
                    break;
                case 50:
                    if (string2.equals("2")) {
                        c4 = 2;
                        break;
                    }
                    c4 = 65535;
                    break;
                case 51:
                    if (string2.equals("3")) {
                        c4 = 3;
                        break;
                    }
                    c4 = 65535;
                    break;
                case 52:
                    if (string2.equals("4")) {
                        c4 = 4;
                        break;
                    }
                    c4 = 65535;
                    break;
                default:
                    c4 = 65535;
                    break;
            }
            if (c4 != 0) {
                if (c4 != 1) {
                    if (c4 != 2) {
                        if (c4 != 3) {
                            if (c4 == 4 && Build.VERSION.SDK_INT >= 21) {
                                this.hbRecorder.setScreenDimensions(1920, 1080);
                            }
                        } else if (Build.VERSION.SDK_INT >= 21) {
                            this.hbRecorder.setScreenDimensions(1280, 720);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setScreenDimensions(854, 480);
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    this.hbRecorder.setScreenDimensions(640, 360);
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.hbRecorder.setScreenDimensions(426, 240);
            }
        }
        String string3 = defaultSharedPreferences.getString("key_video_fps", null);
        if (string3 != null) {
            switch (string3.hashCode()) {
                case 48:
                    if (string3.equals("0")) {
                        c3 = 0;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 49:
                    if (string3.equals("1")) {
                        c3 = 1;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 50:
                    if (string3.equals("2")) {
                        c3 = 2;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 51:
                    if (string3.equals("3")) {
                        c3 = 3;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 52:
                    if (string3.equals("4")) {
                        c3 = 4;
                        break;
                    }
                    c3 = 65535;
                    break;
                case 53:
                    if (string3.equals("5")) {
                        c3 = 5;
                        break;
                    }
                    c3 = 65535;
                    break;
                default:
                    c3 = 65535;
                    break;
            }
            if (c3 != 0) {
                if (c3 != 1) {
                    if (c3 != 2) {
                        if (c3 != 3) {
                            if (c3 != 4) {
                                if (c3 == 5 && Build.VERSION.SDK_INT >= 21) {
                                    this.hbRecorder.setVideoFrameRate(24);
                                }
                            } else if (Build.VERSION.SDK_INT >= 21) {
                                this.hbRecorder.setVideoFrameRate(25);
                            }
                        } else if (Build.VERSION.SDK_INT >= 21) {
                            this.hbRecorder.setVideoFrameRate(30);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoFrameRate(48);
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    this.hbRecorder.setVideoFrameRate(50);
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.hbRecorder.setVideoFrameRate(60);
            }
        }
        String string4 = defaultSharedPreferences.getString("key_video_bitrate", null);
        if (string4 != null) {
            switch (string4.hashCode()) {
                case 49:
                    if (string4.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 50:
                    if (string4.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 51:
                    if (string4.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 52:
                    if (string4.equals("4")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 53:
                    if (string4.equals("5")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 54:
                    if (string4.equals("6")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 55:
                    if (string4.equals("7")) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 56:
                    if (string4.equals("8")) {
                        c2 = 7;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(12000000);
                        break;
                    }
                    break;
                case 1:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(8000000);
                        break;
                    }
                    break;
                case 2:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(7500000);
                        break;
                    }
                    break;
                case 3:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(5000000);
                        break;
                    }
                    break;
                case 4:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(4000000);
                        break;
                    }
                    break;
                case 5:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(2500000);
                        break;
                    }
                    break;
                case 6:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(1500000);
                        break;
                    }
                    break;
                case 7:
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setVideoBitrate(1000000);
                        break;
                    }
                    break;
            }
        }
        String string5 = defaultSharedPreferences.getString("key_output_format", null);
        if (string5 != null) {
            switch (string5.hashCode()) {
                case 48:
                    if (string5.equals("0")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 49:
                    if (string5.equals("1")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 50:
                    if (string5.equals("2")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 51:
                    if (string5.equals("3")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c == 3 && Build.VERSION.SDK_INT >= 21) {
                            this.hbRecorder.setOutputFormat("WEBM");
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        this.hbRecorder.setOutputFormat("THREE_GPP");
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    this.hbRecorder.setOutputFormat("MPEG_4");
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.hbRecorder.setOutputFormat("DEFAULT");
            }
        }
    }


    public void quickSettings() {
        this.hbRecorder.setAudioBitrate(128000);
        this.hbRecorder.setAudioSamplingRate(44100);
        this.hbRecorder.recordHDVideo(this.wasHDSelected);
        this.hbRecorder.isAudioEnabled(this.isAudioEnabled);
        this.hbRecorder.setNotificationSmallIcon(drawable2ByteArray(R.drawable.logo));
        this.hbRecorder.setNotificationTitle(getString(R.string.stop_recording_notification_title));
        this.hbRecorder.setNotificationDescription(getString(R.string.stop_recording_notification_message));
    }


    public boolean checkSelfPermission(String str, int i) {
        if (ContextCompat.checkSelfPermission(this, str) == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{str}, i);
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 22) {
            if (i == 23) {
                if (iArr[0] == 0) {
                    this.hasPermissions = true;
                    if (Build.VERSION.SDK_INT >= 21) {
                        startRecordingScreen();
                        return;
                    }
                    return;
                }
                this.hasPermissions = false;
                showLongToast("No permissicon for android.permission.WRITE_EXTERNAL_STORAGE");
            }
        } else if (iArr[0] == 0) {
            checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE", 23);
        } else {
            this.hasPermissions = false;
            showLongToast("No permission for android.permission.RECORD_AUDIO");
        }
    }

    public void updateResources() {
        Locale locale = new Locale(App.getInstance().getStoreStringValue("pref_selected_language"));
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        createConfigurationContext(configuration);
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    public void setOutputPath() {
        filename = generateFileName();
        if (Build.VERSION.SDK_INT >= 29) {
            this.counter = 0;
            this.resolver = getContentResolver();
            contentValues = new ContentValues();
            if (arrayB == 0) {
                contentValues.put("relative_path", "Movies/LED TEXT");
            } else {
                contentValues.put("relative_path", "Movies/LED TEXT/Banner/");
            }
            this.contentValues.put("title", filename);
            this.contentValues.put("_display_name", filename);
            this.contentValues.put("mime_type", "video/mp4");
            this.mUri = this.resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.contentValues);
            this.hbRecorder.setFileName(filename);
            this.hbRecorder.setOutputUri(this.mUri);
            this.path = this.mUri.toString();
            return;
        }
        this.counter = 1;
        HistoyFolder();
        BannerFolder();
        if (arrayB == 0) {
            path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/LED TEXT/" + filename + ".mp4";

        } else {
            path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/LED TEXT/Banner/" + filename + ".mp4";
        }
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (Build.VERSION.SDK_INT >= 21 && i == SCREEN_RECORD_REQUEST_CODE && i2 == -1) {
            setOutputPath();
            this.hbRecorder.startScreenRecording(intent, i2);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (Build.VERSION.SDK_INT >= 21) {
                        hbRecorder.stopScreenRecording();

                    }
                    Intent intent = new Intent(Led_MakeVideoActrivity.this, Led_PreviewVideoActivity.class);
                    if (arrayB == 0) {
                        intent.putExtra("vedio", path);
                    } else {
                        intent.putExtra("vedio", path);

                    }
                    startActivity(intent);
                    finish();
                }
            }, (long) 3000);
        }
    }

    public String generateFileName() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())).replace(" ", "");
    }

    public void showLongToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    public byte[] drawable2ByteArray(int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void left_to_right() {
        animator = ValueAnimator.ofFloat(new float[]{0.0f, 2.0f});
        animator.setRepeatCount(-1);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int round = Math.round(textView.getPaint().measureText(textView.getText().toString()));
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.width = round;
                textView.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                textView.setTranslationX(f2);
                textView.setTranslationX(f2 - f);
            }
        });
        animator.start();
    }

    public void right_to_left() {
        animator = ValueAnimator.ofFloat(new float[]{2.0f, 0.0f});
        animator.setRepeatCount(-1);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int round = Math.round(textView.getPaint().measureText(textView.getText().toString()));
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.width = round;
                textView.setLayoutParams(layoutParams);
                getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                float f = (float) round;
                float f2 = floatValue * f;
                textView.setTranslationX(f2);
                textView.setTranslationX(f2 - f);
            }
        });
        animator.start();
    }

    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= 21 && !this.hbRecorder.isBusyRecording()) {
            this.hbRecorder.stopScreenRecording();
            finish();
            super.onBackPressed();
        }
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            getWindow().getDecorView().setSystemUiVisibility(8);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        }
    }
}