

package net.appic.hack.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.appic.hack.R;
import net.appic.hack.provider.FeedData.FeedColumns;
import net.appic.hack.utils.UiUtils;

public class FeedsCursorAdapter extends CursorLoaderExpandableListAdapter {

    private int mIsGroupPos = -1;
    private int mNamePos = -1;
    private int mIdPos = -1;
    private int mLinkPos = -1;
    private int mIconPos = -1;

    public FeedsCursorAdapter(Activity activity, Uri groupUri) {
        super(activity, groupUri, R.layout.item_feed_list, R.layout.item_feed_list);
    }

    @Override
    protected void onCursorLoaded(Context context, Cursor cursor) {
        getCursorPositions(cursor);
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor) {
        view.findViewById(R.id.indicator).setVisibility(View.INVISIBLE);

        TextView textView = ((TextView) view.findViewById(android.R.id.text1));

        final long feedId = cursor.getLong(mIdPos);
        Bitmap bitmap = UiUtils.getFaviconBitmap(feedId, cursor, mIconPos);

        if (bitmap != null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(context.getResources(), bitmap), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        textView.setText((cursor.isNull(mNamePos) ? cursor.getString(mLinkPos) : cursor.getString(mNamePos)));
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean isExpanded) {
        ImageView indicatorImage = (ImageView) view.findViewById(R.id.indicator);

        if (cursor.getInt(mIsGroupPos) == 1) {
            indicatorImage.setVisibility(View.VISIBLE);

            TextView textView = ((TextView) view.findViewById(android.R.id.text1));
            textView.setEnabled(true);
            textView.setText(cursor.getString(mNamePos));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            textView.setText(cursor.getString(mNamePos));

            if (isExpanded)
                indicatorImage.setImageResource(R.drawable.group_expanded);
            else
                indicatorImage.setImageResource(R.drawable.group_collapsed);
        } else {
            bindChildView(view, context, cursor);
            indicatorImage.setVisibility(View.GONE);
        }
    }

    @Override
    protected Uri getChildrenUri(Cursor groupCursor) {
        return FeedColumns.FEEDS_FOR_GROUPS_CONTENT_URI(groupCursor.getLong(mIdPos));
    }

    @Override
    public void notifyDataSetChanged() {
        getCursorPositions(null);
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged(Cursor data) {
        getCursorPositions(data);
    }

    @Override
    public void notifyDataSetInvalidated() {
        getCursorPositions(null);
        super.notifyDataSetInvalidated();
    }

    private synchronized void getCursorPositions(Cursor cursor) {
        if (cursor != null && mIsGroupPos == -1) {
            mIsGroupPos = cursor.getColumnIndex(FeedColumns.IS_GROUP);
            mNamePos = cursor.getColumnIndex(FeedColumns.NAME);
            mIdPos = cursor.getColumnIndex(FeedColumns._ID);
            mLinkPos = cursor.getColumnIndex(FeedColumns.URL);
            mIconPos = cursor.getColumnIndex(FeedColumns.ICON);
        }
    }
}
