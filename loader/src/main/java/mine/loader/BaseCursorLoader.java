package mine.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class BaseCursorLoader extends CursorLoader {

    public BaseCursorLoader(Context context) {
        super(context);
        System.out.println("*---" + getClass().getSimpleName() + ".BaseCursorLoader1  ---*");

    }


    public BaseCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        System.out.println("*---" + getClass().getSimpleName() + ".BaseCursorLoader1  ---*");
    }


    @Override
    public Cursor loadInBackground() {
        System.out.println("*---" + getClass().getSimpleName() + ".loadInBackground  ---*");
        return super.loadInBackground();
    }

    @Override
    public void cancelLoadInBackground() {
        System.out.println("*---" + getClass().getSimpleName() + ".cancelLoadInBackground  ---*");
        super.cancelLoadInBackground();
    }

    @Override
    public void deliverResult(Cursor cursor) {
        System.out.println("*---" + getClass().getSimpleName() + ".deliverResult  ---*");
        super.deliverResult(cursor);
    }

    @Override
    protected void onStartLoading() {
        System.out.println("*---" + getClass().getSimpleName() + ".onStartLoading  ---*");
        super.onStartLoading();
    }

    @Override
    protected void onStopLoading() {
        System.out.println("*---" + getClass().getSimpleName() + ".onStopLoading  ---*");
        super.onStopLoading();
    }

    @Override
    public void onCanceled(Cursor cursor) {
        System.out.println("*---" + getClass().getSimpleName() + ".onCanceled  ---*");
        super.onCanceled(cursor);
    }

    @Override
    protected void onReset() {
        System.out.println("*---" + getClass().getSimpleName() + ".onReset  ---*");
        super.onReset();
    }

    @Override
    public Uri getUri() {
        System.out.println("*---" + getClass().getSimpleName() + ".getUri  ---*");
        return super.getUri();
    }

    @Override
    public void setUri(Uri uri) {
        System.out.println("*---" + getClass().getSimpleName() + ".setUri  ---*");
        super.setUri(uri);
    }

    @Override
    public String[] getProjection() {
        System.out.println("*---" + getClass().getSimpleName() + ".getProjection  ---*");
        return super.getProjection();
    }

    @Override
    public void setProjection(String[] projection) {
        System.out.println("*---" + getClass().getSimpleName() + ".setProjection  ---*");
        super.setProjection(projection);
    }

    @Override
    public String getSelection() {
        System.out.println("*---" + getClass().getSimpleName() + ".getSelection  ---*");
        return super.getSelection();
    }

    @Override
    public void setSelection(String selection) {
        System.out.println("*---" + getClass().getSimpleName() + ".setSelection  ---*");
        super.setSelection(selection);
    }

    @Override
    public String[] getSelectionArgs() {
        System.out.println("*---" + getClass().getSimpleName() + ".getSelectionArgs  ---*");
        return super.getSelectionArgs();
    }

    @Override
    public void setSelectionArgs(String[] selectionArgs) {
        System.out.println("*---" + getClass().getSimpleName() + ".setSelectionArgs  ---*");
        super.setSelectionArgs(selectionArgs);
    }

    @Override
    public String getSortOrder() {
        System.out.println("*---" + getClass().getSimpleName() + ".getSortOrder  ---*");
        return super.getSortOrder();
    }

    @Override
    public void setSortOrder(String sortOrder) {
        System.out.println("*---" + getClass().getSimpleName() + ".setSortOrder  ---*");
        super.setSortOrder(sortOrder);
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        System.out.println("*---" + getClass().getSimpleName() + ".dump  ---*");
        super.dump(prefix, fd, writer, args);
    }
}
