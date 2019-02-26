package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.widget.ImageView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class AsyncImageTask{

    public static Map<String, Bitmap> imageCache = new HashMap<String, Bitmap>();

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private final WeakReference<ImageView> imageViewReference;
    private Bitmap placeholder;
    private Boolean isRounded;

    public AsyncImageTask(ImageView imageView, Bitmap placeholder, Boolean isRounded) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.placeholder = placeholder;
        this.isRounded = isRounded;
    }

    public AsyncImageTask(ImageView imageView, Bitmap placeholder) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.placeholder = placeholder;
        this.isRounded = false;
    }

    public AsyncImageTask(ImageView imageView, Boolean isRounded) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.placeholder = getPlaceholder(isRounded);
        this.isRounded = isRounded;
    }

    public AsyncImageTask(ImageView imageView) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.placeholder = getPlaceholder(false);
        this.isRounded = false;
    }

    public void execute(final String url) {

        if (imageCache.containsKey(url)){
            System.out.println("Hereee");
            ImageView imageView = imageViewReference.get();
            if (isRounded){
                imageView.setImageBitmap(getCircleBitmap(imageCache.get(url)));
            }else{
                imageView.setImageBitmap(imageCache.get(url));
            }
            return;
        }

        if (imageViewReference != null && placeholder != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(placeholder);
            }
        }

        StorageReference imageRef = storage.getReferenceFromUrl(url);
        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (imageViewReference != null && bitmap != null) {
                    ImageView imageView = imageViewReference.get();
                    imageCache.put(url, bitmap);
                    if (imageView != null) {
                        if (isRounded){
                            imageView.setImageBitmap(getCircleBitmap(bitmap));
                        }else{
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                }
            }
        });
    }

    private Bitmap getCircleBitmap(Bitmap bm) {

        int sice = Math.min((bm.getWidth()), (bm.getHeight()));

        Bitmap bitmap = ThumbnailUtils.extractThumbnail(bm, sice, sice);

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final int color = 0xffff0000;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 4);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    private Bitmap getPlaceholder(Boolean isRounded){
        if (isRounded){
            Bitmap placeholder = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(placeholder);
            int color = Color.argb(255, 83, 83, 83);
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawCircle(50, 50, 50, paint);
            return placeholder;
        }else{
            Rect rect = new Rect(0, 0, 1, 1);
            Bitmap placeholder = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(placeholder);
            int color = Color.argb(255, 83, 83, 83);
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rect, paint);
            return placeholder;
        }

    }

}