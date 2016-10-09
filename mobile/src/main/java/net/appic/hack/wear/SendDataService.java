

package net.appic.hack.wear;

import android.database.Cursor;
import android.os.SystemClock;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import net.appic.hack.provider.FeedData;
import net.appic.hack.utils.Dog;

public class SendDataService extends WearableListenerService {

    private static final String COUNT_PATH = "/count";
    private static final String DATE_KEY = "date";
    private static final String COUNT_KEY = "count";

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        Dog.d("onMessageReceived " + messageEvent);

        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(COUNT_PATH);
        putDataMapRequest.getDataMap().putLong(DATE_KEY, SystemClock.elapsedRealtime());

        long nbUnread = 0;
        Cursor unread = getContentResolver().query(FeedData.EntryColumns.CONTENT_URI, new String[]{FeedData.ALL_UNREAD_NUMBER}, null, null, null);
        if (unread != null) {
            if (unread.moveToFirst()) {
                nbUnread = unread.getLong(0);
            }
            unread.close();
        }
        putDataMapRequest.getDataMap().putLong(COUNT_KEY, nbUnread);
        PutDataRequest request = putDataMapRequest.asPutDataRequest();

        mGoogleApiClient.blockingConnect();

        if (!mGoogleApiClient.isConnected()) {
            Dog.e("Cannot send data, not connected");
            return;
        }

        Dog.d("Generating DataItem: " + request);
        Wearable.DataApi.putDataItem(mGoogleApiClient, request)
                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                    @Override
                    public void onResult(DataApi.DataItemResult dataItemResult) {
                        if (!dataItemResult.getStatus().isSuccess()) {
                            Dog.e("ERROR: failed to putDataItem, status code: " + dataItemResult.getStatus().getStatusCode());
                        }
                    }
                });
    }
}
