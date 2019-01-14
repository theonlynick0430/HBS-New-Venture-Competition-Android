package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.lang.ref.WeakReference;

public class AsyncImageTask{

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private final WeakReference<ImageView> imageViewReference;
    private Bitmap placeholder;

    public AsyncImageTask(ImageView imageView, Bitmap placeholder) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.placeholder = placeholder;
    }

    public AsyncImageTask(ImageView imageView) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);


        Rect rect = new Rect(0, 0, 1, 1);
        Bitmap placeholder = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(placeholder);
        int color = Color.argb(255, 83, 83, 83);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
        this.placeholder = placeholder;
    }

    public void execute(String url) {
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
                    if (imageView != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

}